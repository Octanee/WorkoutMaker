package com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings

class CreateMesocycleFragmentDragDropAdapter(dataSet: List<MicrocycleWithTrainings>) :
    DragDropSwipeAdapter<MicrocycleWithTrainings, CreateMesocycleFragmentDragDropAdapter.ViewHolder>(
        dataSet
    ) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val dragIcon: ImageView = itemView.findViewById(R.id.itemMicrocycleDragIcon)
        val name: TextView = itemView.findViewById(R.id.itemMicrocycleNameTextView)
        val number: TextView = itemView.findViewById(R.id.itemMicrocycleNumberTextView)
        val numberOfDays: TextView = itemView.findViewById(R.id.itemMicrocycleNumberOfDaysTextView)

        fun bind(item: MicrocycleWithTrainings, position: Int) {
            name.text = item.microcycle.microcycleName
            numberOfDays.text = item.microcycle.numberOfDays.toString()
            number.text = (position + 1).toString()
        }
    }

    override fun getViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: MicrocycleWithTrainings,
        viewHolder: ViewHolder,
        position: Int
    ): View {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(
        item: MicrocycleWithTrainings,
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bind(item, position)
    }

    fun updateDataSet(newDataSet: List<MicrocycleWithTrainings>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}