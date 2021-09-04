package com.example.room.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "student_table")
data class Student (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val surname:String,
    val course : Int
) : Parcelable