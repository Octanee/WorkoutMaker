package com.octaneee.workoutmaker.ui.fragment.plan.createtraining.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.SetAndExercise

class CreateTrainingFragmentDragDropAdapter(dataSet: List<SetAndExercise>) :
    DragDropSwipeAdapter<SetAndExercise, CreateTrainingFragmentDragDropAdapter.ViewHolder>(dataSet) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val dragIcon: ImageView = itemView.findViewById(R.id.itemSetDragIcon)
        val number: TextView = itemView.findViewById(R.id.itemSetNumberTextView)
        val exerciseName: TextView = itemView.findViewById(R.id.itemSetExerciseNameTextView)
        val setType: TextView = itemView.findViewById(R.id.itemSetSetTypeTextView)
        val minimum: TextView = itemView.findViewById(R.id.itemSetRepsMinimumTextView)
        val maximum: TextView = itemView.findViewById(R.id.itemSetRepsMaximumTextView)
        val repsDivider: TextView = itemView.findViewById(R.id.itemSetRepsDividerTextView)
        val restTime: TextView = itemView.findViewById(R.id.itemSetRestTimeTextView)

        fun bind(item: SetAndExercise, position: Int) {
            number.text = (position + 1).toString()
            exerciseName.text = item.exercise.exerciseName
            setType.text = item.setType.setTypeName
            minimum.text = item.set.minimum.toString()
            if (item.set.maximum == null) {
                repsDivider.visibility = View.GONE
                maximum.visibility = View.GONE
            } else {
                maximum.text = item.set.maximum.toString()
            }
            restTime.text = item.set.restTime?.toString() ?: "0"
        }
    }

    override fun getViewHolder(itemView: View): ViewHolder =
        ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: SetAndExercise,
        viewHolder: ViewHolder,
        position: Int
    ): View {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(
        item: SetAndExercise,
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bind(item, position)
    }

    fun updateDataSet(newDataSet: List<SetAndExercise>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}