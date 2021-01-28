package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_training.view.*

class TrainingListAdapter(
    private val itemListener: ItemListener,
) : RecyclerView.Adapter<TrainingListAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<Training> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_training, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View, itemListener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {

        val number = itemView.itemTrainingNumberTextView
        val name = itemView.itemTrainingNameTextView
        val numberOfDay = itemView.itemTrainingNumberOfDayTextView

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: Training) {
            number.text = (adapterPosition + 1).toString()
            name.text = item.trainingName
            numberOfDay.text = item.dayOfMicrocycle.toString()
        }
    }

    fun updateDataSet(newDataSet: List<Training>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) =
        itemListener.onItemMove(fromPosition, toPosition)


    override fun onItemSwipedLeft(position: Int) = itemListener.onItemSwipedLeft(position)


    override fun onItemSwipeRight(position: Int) = itemListener.onItemSwipeRight(position)

}