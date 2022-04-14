package com.example.reminder.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.reminder.database.NoteEntity
import com.example.reminder.databinding.DialogBottomSheetBinding
import com.example.reminder.ui.main.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TEXT_BUTTON_EDIT = "Edit"

@AndroidEntryPoint
class MyBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogBottomSheetBinding
    private val viewModel: NotesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.myNote.value != NoteEntity(0, "", "", "")) {

            binding.srcForPicture.setText(viewModel.myNote.value?.imageViewSrc)
            Glide.with(this).load(viewModel.myNote.value?.imageViewSrc).into(binding.picture)
            binding.textForHeader.setText(viewModel.myNote.value?.header)
            binding.textForText.setText(viewModel.myNote.value?.noteText)
            binding.addButton.setText(TEXT_BUTTON_EDIT)

            binding.addButton.setOnClickListener {

                viewModel.myNote.value = NoteEntity(
                    0,
                    binding.srcForPicture.text.toString(),
                    binding.textForHeader.text.toString(),
                    binding.textForText.text.toString()
                )
                viewModel.onItemClick()
                dismiss()
            }

        } else {
            binding.addButton.setOnClickListener {

                viewModel.saveNote(
                    binding.srcForPicture.text.toString(),
                    binding.textForHeader.text.toString(),
                    binding.textForText.text.toString()
                )
                dismiss()
            }
        }

    }
}