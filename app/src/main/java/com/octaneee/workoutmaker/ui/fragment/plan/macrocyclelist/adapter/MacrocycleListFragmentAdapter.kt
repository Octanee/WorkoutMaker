package com.octaneee.workoutmaker.ui.fragment.plan.macrocyclelist.adapter

import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.macrocyclelist.MacrocycleListFragmentDirections

class MacrocycleListFragmentAdapter(
    dataSet: List<Macrocycle>,
    val mainActivityViewModel: MainActivityViewModel
) :
    DragDropSwipeAdapter<Macrocycle, MacrocycleListFragmentAdapter.ViewHolder>(dataSet) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val number: TextView = itemView.findViewById(R.id.itemMacrocycleNameTextView)
        val name: TextView = itemView.findViewById(R.id.itemMacrocycleNameTextView)

        init {
            itemView.setOnClickListener {
                val macrocycleId = dataSet[adapterPosition].macrocycleId
                // mainActivityViewModel.setCurrentMacrocycle(macrocycleId)
                val action =
                    MacrocycleListFragmentDirections.actionMacrocycleListFragmentToPlanFragment()
                itemView.findNavController().navigate(action)
            }
        }

        fun bind(item: Macrocycle, position: Int) {
            name.text = item.macrocycleName
            number.text = (position + 1).toString()
        }
    }

    override fun getViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: Macrocycle,
        viewHolder: ViewHolder,
        position: Int
    ): View? {
        return null
    }

    override fun onBindViewHolder(
        item: Macrocycle,
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bind(item, position)
    }

    fun updateDataSet(newDataSet: List<Macrocycle>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}