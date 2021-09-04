package com.example.room.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.model.Student
import com.example.room.viewmodel.StudentViewModel
import com.example.room.databinding.FragmentAddBinding


class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var mStudentViewModel: StudentViewModel

    //binding
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAdd.setOnClickListener {
            insertDataToDatabase(binding)
        }
    }


    //dataAdding start
    private fun insertDataToDatabase(binding: FragmentAddBinding) {
        val firstName = binding.editTextTextPersonName.text.toString()
        val surname = binding.editTextTextPersonSurname.text.toString()
        val course = binding.editTextNumberDecimal.text.toString()
        if  (inputCheck(firstName,surname,course)){
            //create Student object
            val student : Student = Student(0,firstName,surname,course.toInt())
            mStudentViewModel.addStudent(student)
            Toast.makeText(requireContext(),"New Student has been added",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Fill out all fields",Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName : String, surname:String, course:String):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(surname) && TextUtils.isEmpty(course) && TextUtils.isDigitsOnly(course))
    }
    //dataAdding end
}