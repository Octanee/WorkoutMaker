package com.octaneee.workoutmaker.ui.fragment.plan.createexercise.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Note

class CreateExerciseFragmentDragDropAdapter(dataSet: List<Note>) :
    DragDropSwipeAdapter<Note, CreateExerciseFragmentDragDropAdapter.ViewHolder>(dataSet) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val itemNumber: TextView = itemView.findViewById(R.id.itemNoteNumberTextView)
        val itemNote: TextView = itemView.findViewById(R.id.itemNoteTextView)
        val dragIcon: ImageView = itemView.findViewById(R.id.itemNoteDragIcon)
    }

    override fun getViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: Note,
        viewHolder: ViewHolder,
        position: Int
    ): View {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(item: Note, viewHolder: ViewHolder, position: Int) {
        viewHolder.itemNumber.text = (position + 1).toString()
        viewHolder.itemNote.text = item.note
    }

    fun updateDataSet(newDataSet: List<Note>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}