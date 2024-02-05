package com.example.studentmanagment.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityData: ViewModel() {

    private val _data = MutableLiveData<List<Student>>()

    val data: LiveData<List<Student>> = _data

    fun setData(data: List<Student>){
        _data.value = data
    }
}