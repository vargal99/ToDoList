package com.example.android.to_do_list_app.fragments.create

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.to_do_list_app.R
import com.example.android.to_do_list_app.model.Task
import com.example.android.to_do_list_app.databinding.FragmentCreateBinding
import com.example.android.to_do_list_app.model.viewmodel.TaskViewModel

class CreateFragment : Fragment() {

    private  val mTaskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val binding = FragmentCreateBinding.inflate(inflater)



        binding.apply {
            createButton.setOnClickListener {
                if (TextUtils.isEmpty((createTask.text))) {
                    Toast.makeText(requireContext(), "It's empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val title = createTask.text.toString()

                val task = Task(0, title, false)
                mTaskViewModel.addTask(task)

                Toast.makeText(requireContext(), "Added new task!", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_createFragment_to_taskFragment)
            }

            }
            return binding.root


    }


}