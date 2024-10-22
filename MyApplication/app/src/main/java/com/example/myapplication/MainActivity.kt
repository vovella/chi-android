package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counter = intent.getIntExtra("counter_value", 0);
        updateCounterText(counter)

        binding.goToSecondActivity.setOnClickListener{
            startActivity(Intent(this, SecondActivity::class.java).putExtra("counter_value", counter))
        }

        binding.increaseButton.setOnClickListener {
            counter++
            val serviceIntent = Intent(this, CounterService::class.java)
            serviceIntent.putExtra("counter_value", counter)
            startService(serviceIntent)
        }
    }

    private fun updateCounterText(counter: Int) {
        binding.counterText.text = "Counter: ${counter.toString()}"
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("com.example.UPDATE_COUNTER")
        registerReceiver(counterReceiver, filter, Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(counterReceiver)
    }

    private val counterReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val newCounterValue = intent?.getIntExtra("counter_value", 0) ?: 0
            counter = newCounterValue
            updateCounterText(newCounterValue)
        }
    }
}


