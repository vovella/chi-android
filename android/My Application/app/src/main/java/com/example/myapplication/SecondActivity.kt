package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private var counter: Int = 0
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counter = intent.getIntExtra("counter_value", 0);
        updateCounterText(counter)

        binding.goToMainActivity.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java).putExtra("counter_value", counter))
        }

        binding.increaseButtonSecond.setOnClickListener {
            counter++
            //binding.counterTextSecond.text = "Counter: $counter"
            //this.counterText=counter
            //updateCounterText(binding)
            // Передача значення в сервіс (можна додати сервіс для цього)

            // Передача значення в сервіс (можна додати сервіс для цього)
            val serviceIntent = Intent(this, CounterService::class.java)
            // Передаємо значення лічильника в сервіс
            serviceIntent.putExtra("counter_value", counter)
            // Запускаємо сервіс
            startService(serviceIntent)

            //val broadcastIntent = Intent("com.example.UPDATE_COUNTER")
            //broadcastIntent.putExtra("counter_value", counter)
            //sendBroadcast(broadcastIntent)
        }
    }

    private fun updateCounterText(counter: Int) {
        binding.counterTextSecond.text = "Counter: ${counter.toString()}"
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