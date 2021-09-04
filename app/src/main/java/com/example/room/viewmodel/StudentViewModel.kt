package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.database.StudentDatabase
import com.example.room.repository.StudentsRepository
import com.example.room.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application)  {

    val readAllData: LiveData<List<Student>>
    private val repository: StudentsRepository

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentsRepository(studentDao)
        readAllData = repository.readAllData

    }
    //add new user into db
    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNewStudent(student)
        }
    }

    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteStudent(student)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}