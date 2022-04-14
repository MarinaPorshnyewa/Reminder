package com.example.reminder.ui.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.reminder.databinding.InformationBottomSheetBinding
import com.example.reminder.ui.main.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class InformationBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: InformationBottomSheetBinding
    private val viewModel: NotesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InformationBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(viewModel.myNote.value?.imageViewSrc).into(binding.picture)
        binding.srcForPicture.text = "src picture: " + viewModel.myNote.value?.imageViewSrc
        binding.textForHeader.text = "Header: " + viewModel.myNote.value?.header
        binding.textForText.text = "Text: " + viewModel.myNote.value?.noteText

    }
}