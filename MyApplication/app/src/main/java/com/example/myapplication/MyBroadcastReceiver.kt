package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val newCounterValue = intent.getIntExtra("counter_value", 0)
        Toast.makeText(context, "Counter updated: $newCounterValue", Toast.LENGTH_SHORT).show()
    }
}
