package com.example.room.fragments.update

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.databinding.FragmentAddBinding
import com.example.room.databinding.FragmentListBinding
import com.example.room.databinding.FragmentUpdateBinding
import com.example.room.model.Student
import com.example.room.viewmodel.StudentViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mStudentViewModel: StudentViewModel
    //binding
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)
        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //use binded objects
        binding.updateTextTextPersonSurname.setText(args.currentStudent.surname)
        binding.updateTextTextPersonName.setText(args.currentStudent.name)
        binding.updateTextNumberDecimal.setText(args.currentStudent.course.toString())

        binding.buttonUpdate.setOnClickListener {
            updateDataToDatabase(binding)
        }

        //add delete button
        setHasOptionsMenu(true)
    }
    //updating data in database
    private fun updateDataToDatabase(binding: FragmentUpdateBinding) {
        val firstName = binding.updateTextTextPersonName.text.toString()
        val surname = binding.updateTextTextPersonSurname.text.toString()
        var course = binding.updateTextNumberDecimal.text.toString()
        if (inputCheck(firstName,surname, course)){
            val updatedStudent = Student(args.currentStudent.id,firstName,surname,course.toInt())
            mStudentViewModel.updateStudent(updatedStudent)
            Toast.makeText(requireContext(),"Student has been added",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Fill out all fields",Toast.LENGTH_SHORT).show()
        }

    }

    //check input data
    private fun inputCheck(firstName : String, surname:String, course:String):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(surname) && TextUtils.isEmpty(course) && TextUtils.isDigitsOnly(course))
    }
    //delete button
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu){
            deleteStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteStudent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mStudentViewModel.deleteStudent(args.currentStudent)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentStudent.name}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentStudent.name}")
        builder.setMessage("Are you sure want to delete ${args.currentStudent.name}")
        builder.create().show()
    }
}