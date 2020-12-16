package com.octaneee.workoutmaker.ui.fragment.plan.exerciselist.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.ExerciseHolder
import com.octaneee.workoutmaker.logic.utility.DrawableHelper

class ExerciseListFragmentDragDropAdapter(
    dataSet: List<ExerciseHolder>,
) :
    DragDropSwipeAdapter<ExerciseHolder, ExerciseListFragmentDragDropAdapter.ViewHolder>(dataSet) {

    var type: Int = PRIMARY_MUSCLE

    companion object {
        const val PRIMARY_MUSCLE = 0
        const val EXERCISE_TYPE = 1
        const val EQUIPMENT = 2
    }

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.itemExerciseListImageView)
        val name: TextView = itemView.findViewById(R.id.itemExerciseListNameTextView)
        val second: TextView = itemView.findViewById(R.id.itemExerciseListSecondTextView)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "Click: ${dataSet[position].exercise.exerciseName}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: ExerciseHolder,
        viewHolder: ViewHolder,
        position: Int
    ): View {
        return viewHolder.image
    }

    override fun onBindViewHolder(
        item: ExerciseHolder,
        viewHolder: ViewHolder,
        position: Int
    ) {
        val context = viewHolder.itemView.context
        when (type) {
            PRIMARY_MUSCLE -> bind(
                viewHolder,
                item.exercise.exerciseName,
                DrawableHelper.getDrawable("ic_gym", context)!!,
                item.exerciseType.exerciseTypeName
            )
            EXERCISE_TYPE -> bind(
                viewHolder,
                item.exercise.exerciseName,
                DrawableHelper.getDrawable(item.primaryMuscle.drawable, context)!!,
                item.equipment.equipmentName
            )
            EQUIPMENT -> bind(
                viewHolder,
                item.exercise.exerciseName,
                DrawableHelper.getDrawable(item.primaryMuscle.drawable, context)!!,
                item.exerciseType.exerciseTypeName
            )
        }
    }

    private fun bind(
        viewHolder: ViewHolder,
        exerciseName: String,
        bitmap: Bitmap,
        secondLine: String
    ) {
        viewHolder.name.text = exerciseName
        viewHolder.image.setImageBitmap(bitmap)
        viewHolder.second.text = secondLine
    }

    private fun bind(
        viewHolder: ViewHolder,
        exerciseName: String,
        drawable: LayerDrawable,
        secondLine: String
    ) {
        viewHolder.name.text = exerciseName
        viewHolder.image.setImageDrawable(drawable)
        viewHolder.second.text = secondLine
    }

    private fun bind(
        viewHolder: ViewHolder,
        exerciseName: String,
        drawable: Drawable,
        secondLine: String
    ) {
        viewHolder.name.text = exerciseName
        viewHolder.image.setImageDrawable(drawable)
        viewHolder.second.text = secondLine
    }

    fun updateDataSet(newDataSet: List<ExerciseHolder>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

}