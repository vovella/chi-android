package com.example.lesson09
// AddGroupActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_group.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_group)

        val database = DatabaseInstance.getDatabase(this)
        val dao = database.schoolDao()

        btnSaveGroup.setOnClickListener {
            val groupName = etGroupName.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                dao.insertGroup(Group(groupName = groupName))
            }
        }
    }
}
