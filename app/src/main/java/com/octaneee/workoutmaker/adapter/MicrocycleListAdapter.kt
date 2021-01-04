package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.Microcycle
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_microcycle.view.*

class MicrocycleListAdapter(
    private val itemListener: ItemListener,
) : RecyclerView.Adapter<MicrocycleListAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<Microcycle> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_microcycle, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View, itemListener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.itemMicrocycleNameTextView
        val number: TextView = itemView.itemMicrocycleNumberTextView
        val numberOfDays: TextView = itemView.itemMicrocycleNumberOfDaysTextView

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: Microcycle) {
            name.text = item.microcycleName
            number.text = (adapterPosition + 1).toString()
            numberOfDays.text = item.numberOfDays.toString()
        }
    }

    fun updateDataSet(newDataSet: List<Microcycle>) {
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