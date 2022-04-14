package com.example.reminder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminder.R
import com.example.reminder.database.NoteEntity
import com.example.reminder.databinding.FragmentNotesBinding
import com.example.reminder.repository.NoteRepository
import com.example.reminder.ui.main.adapter.NoteAdapter
import com.example.reminder.ui.view.InformationBottomSheetFragment
import com.example.reminder.ui.view.MyBottomSheetFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding

    private val viewModel: NotesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotesBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showProgressBar = {
            binding.progressView.visibility = if (it) {
                View.VISIBLE
            } else View.GONE
        }

        viewModel.loadList()

        viewModel.dataNote.observe(requireActivity()) {
            binding.noteFromFragment.run {
                adapter = NoteAdapter(requireContext()) {

                    showPopUp(this, it)

                }
                layoutManager = LinearLayoutManager(requireContext())
            }

            (binding.noteFromFragment.adapter as NoteAdapter).setDataList(it)
        }

        binding.button.setOnClickListener {

            viewModel.clear()

            MyBottomSheetFragment().show(parentFragmentManager, "")
        }

        binding.clearAll.setOnClickListener {

            viewModel.clearAll()

        }
    }

    private fun showPopUp(view: View, note: NoteEntity) {

        val popup = PopupMenu(requireContext(), view)
        popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)

        popup.show()

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_menu_item -> {

                    viewModel.delete(note)

                }
                R.id.edit_menu_item -> {

                    viewModel.edit(note)

                    MyBottomSheetFragment().show(parentFragmentManager, "")

                    viewModel.onItemClick = {

                        viewModel.edit2(note)

                    }
                }
                R.id.show_menu_item -> {

                    viewModel.edit(note)
                    InformationBottomSheetFragment().show(parentFragmentManager, "")
                }
            }
            true

        }
    }
}