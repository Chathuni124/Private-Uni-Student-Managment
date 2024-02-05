package com.example.studentmanagment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagment.R
import com.example.studentmanagment.StudentViewHolder
import com.example.studentmanagment.database.MainActivityData
import com.example.studentmanagment.database.Student
import com.example.studentmanagment.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentAdapter (
    private val items: List<Student>,
    private val repository: StudentRepository,
    private val viewModel: MainActivityData
    ) : RecyclerView.Adapter<StudentViewHolder>() {

        var context: Context? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
            context = parent.context

            return StudentViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.sbtn.text = items[position].name
            holder.sName.text = items[position].faculty
            holder.sfaculty.text = items[position].idNumber
            holder.sDelete.setOnClickListener {
                val isChecked = holder.sbtn.isChecked

                if (isChecked) {
                    CoroutineScope(Dispatchers.IO).launch {
                        repository.delete(items[position])
                        val data = repository.getAllStudent()
                        withContext(Dispatchers.Main) {
                            viewModel.setData(data)
                        }
                    }
                } else
                    Toast.makeText(context, "Select student to delete", Toast.LENGTH_LONG).show()
            }
        }
    }
