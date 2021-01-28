package com.octaneee.workoutmaker.ui.bottom_sheet.note

import androidx.lifecycle.ViewModel

class NoteBottomSheetViewModel : ViewModel() {
    var edit: Boolean = false
    var noteText: String = ""
}