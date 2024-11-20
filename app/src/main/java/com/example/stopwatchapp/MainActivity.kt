package com.example.stopwatchapp

//current project

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatchapp.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isRunning = false
    private var timeOffset: Long = 0L
    private var startTime: Long = 0L
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadState()

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isRunning) {
            startTimer()
            binding.buttonStartStop.text = "Stop"
        }

        binding.buttonStartStop.setOnClickListener {
            if (isRunning) {
                stopTimer()
            } else {
                startTimer()
            }
        }

        binding.buttonAddHour.setOnClickListener {
            timeOffset -= TimeUnit.HOURS.toMillis(1)
        }

        binding.buttonSubtractHour.setOnClickListener {
            timeOffset += TimeUnit.HOURS.toMillis(1)
        }

        binding.buttonSubtract8Hours.setOnClickListener {
            timeOffset += TimeUnit.HOURS.toMillis(8)
        }

        binding.buttonReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        startTime = SystemClock.elapsedRealtime()
        isRunning = true
        handler.post(updateTimeRunnable)
        binding.buttonStartStop.text = getString(R.string.stop)
        saveState()
    }


    private fun stopTimer() {
        timeOffset += SystemClock.elapsedRealtime() - startTime
        isRunning = false
        handler.removeCallbacks(updateTimeRunnable)
        binding.buttonStartStop.text = getString(R.string.start)
        saveState()
    }


    private fun resetTimer() {
        timeOffset = 0L
        startTime = SystemClock.elapsedRealtime()
        updateTimeDisplay(0L)
        saveState()
    }

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            val elapsedTime = SystemClock.elapsedRealtime() - startTime - timeOffset
            updateTimeDisplay(elapsedTime)
            handler.postDelayed(this, 1000)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTimeDisplay(elapsedTime: Long) {
        val totalSeconds = elapsedTime / 1000
        val days = totalSeconds / (24 * 3600)
        val hours = (totalSeconds % (24 * 3600)) / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        binding.textViewTime.text = "${days}d %02d:%02d:%02d".format(hours, minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRunning) {
            handler.removeCallbacks(updateTimeRunnable)
        }
        saveState()
    }

    private fun saveState() {
        val prefs = getSharedPreferences("stopwatch_prefs", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean("isRunning", isRunning)
            putLong("startTime", startTime)
            putLong("timeOffset", timeOffset)
            apply()
        }
    }

    private fun loadState() {
        val prefs = getSharedPreferences("stopwatch_prefs", Context.MODE_PRIVATE)
        isRunning = prefs.getBoolean("isRunning", false)
        startTime = prefs.getLong("startTime", SystemClock.elapsedRealtime())
        timeOffset = prefs.getLong("timeOffset", 0L)
    }
}
