package com.example.room.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.model.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Query("SELECT * FROM student_table ORDER by id ASC")
    fun readAllData():LiveData<List<Student>>

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()
}