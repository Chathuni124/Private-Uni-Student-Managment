package com.example.studentmanagment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    var name: String?,
    var idNumber : String?,
    var faculty: String?,

    ){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}



