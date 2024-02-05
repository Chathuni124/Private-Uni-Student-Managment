package com.example.studentmanagment

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class   StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val sbtn: CheckBox = view.findViewById(R.id.checkBox)
    val sName: TextView = view.findViewById(R.id.sName)
    val sfaculty: TextView = view.findViewById(R.id.sfaculty)
    val sDelete: ImageView = view.findViewById(R.id.delete)
}