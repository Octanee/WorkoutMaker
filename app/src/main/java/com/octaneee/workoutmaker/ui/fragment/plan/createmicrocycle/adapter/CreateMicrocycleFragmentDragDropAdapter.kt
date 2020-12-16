package com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.TrainingWithSetAndExercises


class CreateMicrocycleFragmentDragDropAdapter(
    dataSet: List<TrainingWithSetAndExercises>,
) : DragDropSwipeAdapter<TrainingWithSetAndExercises, CreateMicrocycleFragmentDragDropAdapter.ViewHolder>(
    dataSet
) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val dragIcon: ImageView = itemView.findViewById(R.id.itemTrainingDragIcon)
        val number: TextView = itemView.findViewById(R.id.itemTrainingNumberTextView)
        val name: TextView = itemView.findViewById(R.id.itemTrainingNameTextView)

        fun bind(item: TrainingWithSetAndExercises, position: Int) {
            number.text = (position + 1).toString()
            name.text = item.training.trainingName
        }
    }

    override fun getViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: TrainingWithSetAndExercises,
        viewHolder: ViewHolder,
        position: Int
    ): View {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(
        item: TrainingWithSetAndExercises,
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bind(item, position)
    }

    fun updateDataSet(newDataSet: List<TrainingWithSetAndExercises>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}