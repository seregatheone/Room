package com.example.room.fragments.list

import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.model.Student

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var studentList = emptyList<Student>()

    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        setTextForItem(holder,R.id.id_real,currentItem.id.toString())
        setTextForItem(holder,R.id.name,currentItem.name)
        setTextForItem(holder,R.id.surname,currentItem.surname)
        setTextForItem(holder,R.id.course,currentItem.course.toString())

        holder.itemView.findViewById<RelativeLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    private fun setTextForItem(holder: MyViewHolder,id:Int,text: String){
        holder.itemView.findViewById<TextView>(id).text = text
    }
    fun setData(student: List<Student>){
        this.studentList = student
        notifyDataSetChanged()
    }
}