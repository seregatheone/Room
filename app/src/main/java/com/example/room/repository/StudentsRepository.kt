package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.database.StudentDao
import com.example.room.model.Student

class StudentsRepository (private val studentDao : StudentDao){
    val readAllData:LiveData<List<Student>> = studentDao.readAllData()

    suspend fun addNewStudent(student: Student){
        studentDao.addStudent(student)
    }

    suspend fun updateStudent(student: Student){
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        studentDao.deleteStudent(student)
    }

    suspend fun deleteAll(){
        studentDao.deleteAll()
    }
}