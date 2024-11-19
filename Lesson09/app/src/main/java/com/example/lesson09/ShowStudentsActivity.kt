package com.example.lesson09

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_students.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowStudentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_students)

        val database = DatabaseInstance.getDatabase(this)
        val dao = database.schoolDao()

        CoroutineScope(Dispatchers.IO).launch {
            val students = dao.getAllStudents()
            val groups = dao.getAllGroups()
            withContext(Dispatchers.Main) {
                val info = students.joinToString("\n") { student ->
                    val groupName = groups.find { it.id == student.groupId }?.groupName ?: "No group"
                    "${student.name} ${student.surname}, Age: ${student.age}, Group: $groupName"
                }
                tvStudentsInfo.text = info
            }
        }
    }
}