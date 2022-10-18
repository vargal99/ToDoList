package com.example.android.to_do_list_app.fragments.update

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
import com.example.android.to_do_list_app.databinding.FragmentUpdateBinding
import com.example.android.to_do_list_app.model.Task
import com.example.android.to_do_list_app.model.viewmodel.TaskViewModel


class UpdateFragment : Fragment() {


    private val mTaskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentUpdateBinding.inflate(inflater)

        val args = UpdateFragmentArgs.fromBundle(requireArguments())



        binding.apply {
            updateTask.setText(args.currentTask.listItem)

            binding.updateButton.setOnClickListener {
                if (TextUtils.isEmpty((updateTask.text))) {
                    Toast.makeText(requireContext(), "It's empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val updateTitle = updateTask.text.toString()

                val updateTask = Task(args.currentTask.id, updateTitle, false)
                mTaskViewModel.updateTask(updateTask)

                Toast.makeText(requireContext(), "Update Succesfully!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_taskFragment)
            }


        }

        return binding.root
    }





}