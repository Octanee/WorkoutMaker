package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles

class CreateMacrocycleFragmentDragDropAdapter(dataSet: List<MesocycleAndMesocycleTypeWithMicrocycles>) :
    DragDropSwipeAdapter<MesocycleAndMesocycleTypeWithMicrocycles, CreateMacrocycleFragmentDragDropAdapter.ViewHolder>(
        dataSet
    ) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.itemMesocycleName)
        val typeName: TextView = itemView.findViewById(R.id.itemMesocycleTypeName)
        val number: TextView = itemView.findViewById(R.id.itemMesocycleNumberTextView)
        val dragIcon: ImageView = itemView.findViewById(R.id.itemMesocycleDragIcon)

        fun bind(item: MesocycleAndMesocycleTypeWithMicrocycles, position: Int) {
            name.text = item.mesocycle.mesocycleName
            typeName.text = item.mesocycleType.mesocycleTypeName
            number.text = (position + 1).toString()
        }
    }

    override fun getViewHolder(itemView: View) = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: MesocycleAndMesocycleTypeWithMicrocycles,
        viewHolder: CreateMacrocycleFragmentDragDropAdapter.ViewHolder,
        position: Int
    ): View {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(
        item: MesocycleAndMesocycleTypeWithMicrocycles,
        viewHolder: CreateMacrocycleFragmentDragDropAdapter.ViewHolder,
        position: Int
    ) {
        viewHolder.bind(item, position)
    }

    fun updateDataSet(newDataSet: List<MesocycleAndMesocycleTypeWithMicrocycles>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}