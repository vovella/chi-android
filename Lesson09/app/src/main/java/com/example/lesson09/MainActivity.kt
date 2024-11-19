package com.example.lesson09

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnAddGroup.setOnClickListener {
            startActivity(Intent(this, AddGroupActivity::class.java))
        }

        btnAddStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }

        btnShowStudents.setOnClickListener {
            startActivity(Intent(this, ShowStudentsActivity::class.java))
        }

    }

}