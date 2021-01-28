package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.WorkoutSeries
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_workout_series.view.*

class ExerciseSeriesAdapter(private val itemListener: ItemListener) :
    RecyclerView.Adapter<ExerciseSeriesAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<WorkoutSeries> = listOf()

    inner class ViewHolder(itemView: View, itemListener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {

        private val number = itemView.itemWorkoutSeriesNumberTextView
        private val repsMin = itemView.itemWorkoutSeriesMinRepsTextView
        private val repsMax = itemView.itemWorkoutSeriesMaxRepsTextView
        private val weight = itemView.itemWorkoutSeriesWeightNumberTextView
        private val repsInReserve = itemView.itemWorkoutSeriesRepsInReserveNumberTextView
        private val restTime = itemView.itemWorkoutSeriesRestTimeTextView

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: WorkoutSeries) {
            number.text = (adapterPosition + 1).toString()
            repsMin.text = item.series.minimum.toString()
            repsMax.text = item.series.maximum.toString()
            weight.text = item.series.weight.toString()
            repsInReserve.text = item.series.repsInReserve.toString()
            restTime.text = item.series.restTime.toString()
        }
    }

    fun updateDataSet(newDataSet: List<WorkoutSeries>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_workout_series, parent, false)
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