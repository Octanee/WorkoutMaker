package com.octaneee.workoutmaker.ui.bottom_sheet.note

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.bottom_sheet_note.*

class NoteBottomSheetFragment(private val listener: NoteBottomSheetFragmentListener) :
    BottomSheetDialogFragment() {

    private val viewModel: NoteBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        setupEditText()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.onNoteBottomSheetFragmentDismiss()
    }

    fun setup(note: String, isEdit: Boolean = false) {
        viewModel.noteText = note
        viewModel.edit = isEdit
    }

    private fun setupEditText() {
        val editText = bottomSheetNoteEditText
        val text = Editable.Factory().newEditable(viewModel.noteText)
        editText.text = text
        editText.addTextChangedListener {
            viewModel.noteText = it?.toString() ?: ""
        }
    }

    private fun setupButton() {
        val button = bottomSheetNoteSaveButton
        button.setOnClickListener {
            listener.onNoteBottomSheetFragmentSaveButtonClick(viewModel.noteText, viewModel.edit)
        }
    }

    interface NoteBottomSheetFragmentListener {
        fun onNoteBottomSheetFragmentSaveButtonClick(sequence: String, isEdit: Boolean)
        fun onNoteBottomSheetFragmentDismiss()
    }
}