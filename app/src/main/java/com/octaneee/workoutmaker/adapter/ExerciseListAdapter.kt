package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_exercise.view.*
import java.util.*

class ExerciseListAdapter(
    private val itemListener: ItemListener,
) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>(), ItemTouchHelperAdapter, Filterable {

    private var dataSet: MutableList<Exercise> = mutableListOf()
    private var fullDataSet: MutableList<Exercise> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View, itemListener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.itemExerciseNameTextView

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: Exercise) {
            name.text = item.exerciseName
        }
    }

    fun updateDataSet(newDataSet: List<Exercise>) {
        dataSet = newDataSet as MutableList<Exercise>
        fullDataSet.clear()
        fullDataSet.addAll(dataSet)
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

    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Exercise> = mutableListOf()
            if (constraint.isEmpty()) {
                filteredList.addAll(fullDataSet)
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' }
                for (item in fullDataSet) {
                    if (item.exerciseName.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            dataSet.clear()
            val list = results.values as Collection<Exercise>
            dataSet.addAll(list)
            notifyDataSetChanged()
        }
    }
}