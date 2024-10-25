package com.example.lesson_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Switch
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.lesson_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var switch = findViewById<Switch>(R.id.switch1)
        var textView = findViewById<TextView>(R.id.textView)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                textView.setTextColor(ContextCompat.getColor(this, R.color.green));

            } else {
                textView.setTextColor(ContextCompat.getColor(this, R.color.red));
            }
        }


    }


}