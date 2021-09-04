package com.example.room.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.viewmodel.StudentViewModel
import com.example.room.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {
    //binding
    private var _binding:FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mStudentViewModel: StudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)

        //RecycleView
        val adapter = ListAdapter()
        //push the adapter
        val recycleView = binding.recycleView
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())


        //mStudentViewModel
        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer { student ->
            adapter.setData(student)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //delete button
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.menu.delete_menu){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mStudentViewModel.deleteAll()
            Toast.makeText(requireContext(), "All students have been successfully removed", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete all students")
        builder.setMessage("Are you sure want to delete them all")
        builder.create().show()
    }
}