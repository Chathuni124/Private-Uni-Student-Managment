package com.example.studentmanagment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.validation.RegisFormData
import com.example.studentmanagment.database.UserRepository
import com.example.studentmanagment.database.User_database
import com.example.studentmanagment.database.Users
import com.example.studentmanagment.validation.regisValidation.RegisValidationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class registration : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var id : EditText
    lateinit var email: EditText
    lateinit var pwd : EditText
    lateinit var signup : Button
    lateinit var backtolog : Button
    private var count = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val userRepo = UserRepository(User_database.getInstance(this))

        username = findViewById(R.id.username)
        id = findViewById(R.id.id)
        email = findViewById(R.id.Email)
        pwd = findViewById(R.id.signpwd)
        signup = findViewById(R.id.signupbtnsp)
        backtolog = findViewById(R.id.backtologbtn)

        backtolog.setOnClickListener(){
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        signup.setOnClickListener(){
            val userName = username.text.toString()
            val userId =id.text.toString()
            val userEmail = email.text.toString()
            val userPWD = pwd.text.toString()
            val regisForamData = RegisFormData(
                userName,
                userId,
                userEmail,
                userPWD
            )

            val userNameValidation = regisForamData.validateUsername()
            val idValidation = regisForamData.validateId()
            val emailValidation = regisForamData.validateEmail()
            val passwordValidation = regisForamData.validatePassword()

            when(userNameValidation){
                is RegisValidationResult.Valid -> {
                    count++
                }
                is RegisValidationResult.Invalid ->{
                    username.error = userNameValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    username.error = userNameValidation.errorMessage
                }
            }

            when(idValidation){
                is RegisValidationResult.Valid -> {
                    count++
                }
                is RegisValidationResult.Invalid ->{
                    id.error = idValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    id.error = idValidation.errorMessage
                }
            }



            when(emailValidation){
                is RegisValidationResult.Valid -> {
                    count++
                }
                is RegisValidationResult.Invalid ->{
                    email.error = emailValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    email.error = emailValidation.errorMessage
                }
            }

            when(passwordValidation){
                is RegisValidationResult.Valid -> {
                    count++
                }
                is RegisValidationResult.Invalid ->{
                    pwd.error = passwordValidation.errorMessage
                }
                is RegisValidationResult.Empty ->{
                    pwd.error = passwordValidation.errorMessage
                }
            }

            if(count==4){
                Toast.makeText(this,"Registration succesful", Toast.LENGTH_LONG).show()
                CoroutineScope(Dispatchers.IO).launch {
                    userRepo.insert(Users(userName,userId,userEmail,userPWD))
                }
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}