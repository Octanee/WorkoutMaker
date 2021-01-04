package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.relations.ExerciseWithSets
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_training_exercise.view.*

class SetListAdapter(
    private val itemListener: ItemListener,
) : RecyclerView.Adapter<SetListAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<ExerciseWithSets> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_training_exercise, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View, itemListener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {

        private val number = itemView.itemSetNumberTextView
        private val name = itemView.itemSetExerciseNameTextView
        private val setsNumber = itemView.itemSetNumberOfSetsTextView

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: ExerciseWithSets) {
            number.text = (adapterPosition + 1).toString()
            name.text = item.exercise.exerciseName
            setsNumber.text = item.setList.size.toString()
        }
    }

    fun updateDataSet(newDataSet: List<ExerciseWithSets>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        itemListener.onItemMove(fromPosition, toPosition)
    }

    override fun onItemSwipedLeft(position: Int) {
        itemListener.onItemSwipedLeft(position)
    }

    override fun onItemSwipeRight(position: Int) {
        itemListener.onItemSwipeRight(position)
    }
}