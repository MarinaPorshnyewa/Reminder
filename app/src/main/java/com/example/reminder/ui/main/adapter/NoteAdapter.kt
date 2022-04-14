package com.example.reminder.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.database.NoteEntity
import com.example.reminder.databinding.ItemNoteBinding
import javax.inject.Inject

class NoteAdapter(
    private val context: Context,
    val onItemClick: (item: NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>() {


    private var list = arrayListOf<NoteEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(context))
        return NoteViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {

            onItemClick(list[position])
        }
    }

    override fun getItemCount() = list.size

    fun setDataList(data: ArrayList<NoteEntity>) {

        list = data
        notifyDataSetChanged()
    }

}