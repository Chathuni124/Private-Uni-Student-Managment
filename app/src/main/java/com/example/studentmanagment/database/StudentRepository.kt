package com.example.studentmanagment.database

class StudentRepository(
    private val db: StudentDatabase
){
    suspend fun insert(student: Student) = db.getStudentDao().insert(student)
    suspend fun delete(student: Student) = db.getStudentDao().delete(student)
    fun getAllStudent():List<Student> = db.getStudentDao().getAllStudent()
}