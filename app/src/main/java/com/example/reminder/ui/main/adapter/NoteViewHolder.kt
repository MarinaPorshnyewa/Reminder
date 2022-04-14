package com.example.reminder.ui.main.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reminder.database.NoteEntity
import com.example.reminder.databinding.ItemNoteBinding


class NoteViewHolder(
    private val binding: ItemNoteBinding, private val context: Context
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(note: NoteEntity) {

        Glide.with(context).load(note.imageViewSrc).into(binding.imageRecyclerView)
        binding.headerRecyclerView.text = note.header
        binding.textRecyclerView.text = note.noteText
    }
}

