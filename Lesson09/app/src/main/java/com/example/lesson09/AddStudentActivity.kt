package com.example.lesson09
// AddStudentActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson09.sampledata.User
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val database = DatabaseInstance.getDatabase(this)
        val dao = database.schoolDao()

        btnSaveStudent.setOnClickListener {
            val name = etStudentName.text.toString()
            val surname = etStudentSurname.text.toString()
            val age = etStudentAge.text.toString().toInt()
            val groupId = etGroupId.text.toString().toInt()

            CoroutineScope(Dispatchers.IO).launch {
                dao.insertStudent(User(name = name, surname = surname, age = age, groupId = groupId))
            }
        }
    }
}
