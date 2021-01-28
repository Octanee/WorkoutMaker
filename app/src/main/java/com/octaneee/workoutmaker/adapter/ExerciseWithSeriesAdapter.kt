package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.relations.ExerciseWithSeries
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_workout_exercise.view.*

class ExerciseWithSeriesAdapter(private val itemListener: ItemListener) :
    RecyclerView.Adapter<ExerciseWithSeriesAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<ExerciseWithSeries> = listOf()

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

        fun bind(item: ExerciseWithSeries) {
            number.text = (adapterPosition + 1).toString()
            name.text = item.exercise.exerciseName
            numberOfSeries.text = item.seriesList.size.toString()
        }
    }

    fun updateDataSet(newDataSet: List<ExerciseWithSeries>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_exercise, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) =
        itemListener.onItemMove(fromPosition, toPosition)

    override fun onItemSwipedLeft(position: Int) = itemListener.onItemSwipedLeft(position)

    override fun onItemSwipeRight(position: Int) = itemListener.onItemSwipeRight(position)
}