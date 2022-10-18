package com.example.android.to_do_list_app.fragments.task


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.to_do_list_app.R
import com.example.android.to_do_list_app.databinding.ItemRowBinding
import com.example.android.to_do_list_app.model.Task


class TaskAdapter(val clickListener: TaskClickListener) : ListAdapter<Task, TaskAdapter.ViewHolder>(TaskDiffCallback) {

    companion object TaskDiffCallback : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }

    class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task, clickListener: TaskClickListener){
            binding.taskEntry = task
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    private fun toggleStrikeThrough(taskTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            taskTitle.paintFlags = taskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            taskTitle.paintFlags = taskTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
        val text = holder.itemView.findViewById<TextView>(R.id.taskTitle)
        val box = holder.itemView.findViewById<CheckBox>(R.id.cbDone)

        toggleStrikeThrough(text, current.isChecked)
        box.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(text, isChecked)
            current.isChecked = !current.isChecked
        }
    }
}

class TaskClickListener(val clickListener: (task: Task) -> Unit){
    fun onClick(task: Task) = clickListener(task)
}