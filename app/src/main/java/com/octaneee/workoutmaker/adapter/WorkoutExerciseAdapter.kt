package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.WorkoutExercise
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_workout_exercise.view.*

class WorkoutExerciseAdapter(private val itemListener: ItemListener) :
    RecyclerView.Adapter<WorkoutExerciseAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<WorkoutExercise> = listOf()

    inner class ViewHolder(itemView: View, itemListener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {

        private val number = itemView.itemWorkoutExerciseNumberTextView
        private val name = itemView.itemWorkoutExerciseNameTextView
        private val numberOfSeries = itemView.itemWorkoutExerciseNumberOfSeriesTextView

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: WorkoutExercise) {
            number.text = (adapterPosition + 1).toString()
            name.text = item.exercise.exerciseName
            numberOfSeries.text = item.series.size.toString()
        }
    }

    fun updateDataSet(newDataSet: List<WorkoutExercise>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutExerciseAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_exercise, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: WorkoutExerciseAdapter.ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) =
        itemListener.onItemMove(fromPosition, toPosition)

    override fun onItemSwipedLeft(position: Int) = itemListener.onItemSwipedLeft(position)

    override fun onItemSwipeRight(position: Int) = itemListener.onItemSwipeRight(position)
}