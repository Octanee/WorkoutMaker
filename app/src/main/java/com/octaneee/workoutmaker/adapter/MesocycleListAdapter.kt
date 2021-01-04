package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.relations.MesocycleAndMesocycleType
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_mesocycle.view.*

class MesocycleListAdapter(
    private val itemListener: ItemListener,
) : RecyclerView.Adapter<MesocycleListAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<MesocycleAndMesocycleType> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_mesocycle, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View, itemListener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.itemMesocycleNameTextView
        private val number: TextView = itemView.itemMesocycleNumberTextView
        private val type: TextView = itemView.itemMesocycleTypeNameTextView

        init {
            itemView.setOnClickListener {
                itemListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: MesocycleAndMesocycleType) {
            name.text = item.mesocycle.mesocycleName
            number.text = (adapterPosition + 1).toString()
            type.text = item.mesocycleType.mesocycleTypeName
        }
    }

    fun updateDataSet(newDataSet: List<MesocycleAndMesocycleType>) {
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