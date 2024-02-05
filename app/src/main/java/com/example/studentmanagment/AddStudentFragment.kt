package com.example.studentmanagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagment.adapters.StudentAdapter
import com.example.studentmanagment.database.MainActivityData
import com.example.studentmanagment.database.Student
import com.example.studentmanagment.database.StudentDatabase
import com.example.studentmanagment.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStudentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: StudentAdapter
    private lateinit var viewModel: MainActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)
        val repository = StudentRepository(StudentDatabase.getInstance(requireContext()))

        // Use viewLifecycleOwner instead of this
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )
            .get(MainActivityData::class.java)

        // Find the button in the inflated view
        val btnAddStudent: Button = view.findViewById(R.id.add)

        // Add a click listener to the button
        btnAddStudent.setOnClickListener {
            displayAlert(repository)

        }

        return view


    }

    private fun displayAlert(repository: StudentRepository) {
        val builder = AlertDialog.Builder(requireActivity())

        builder.setTitle("Enter Student Details")

        val view = layoutInflater.inflate(R.layout.alertviewbox, null)
        builder.setView(view)

        val name: EditText = view.findViewById(R.id.studentName)
        val idNumber: EditText = view.findViewById(R.id.studentID)
        val faculty: Spinner = view.findViewById(R.id.faculty)


        builder.setPositiveButton("OK") { _, _ ->
            val sName = name.text.toString()
            val sId = idNumber.text.toString()
            val sFaculty = faculty.selectedItem.toString()

            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Student(sName, sId, sFaculty))
                val data = repository.getAllStudent()
                withContext(Dispatchers.Main) {
                    viewModel.setData(data)
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}