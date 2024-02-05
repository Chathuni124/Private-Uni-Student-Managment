package com.example.studentmanagment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val studentlistFragment = StudentlistFragment()
    private val addStudentFragment = AddStudentFragment()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button: Button = findViewById(R.id.addStudent)
        val button2: Button = findViewById(R.id.studentList)

        button.setOnClickListener {
            AddStudent()
        }

        button2.setOnClickListener {
            Studentlist()
        }
    }

    private fun AddStudent() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if (fragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, addStudentFragment).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, addStudentFragment).commit()
        }
    }

    private fun Studentlist() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if (fragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, studentlistFragment).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, studentlistFragment).commit()
        }
    }
}

