package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleType
import com.octaneee.workoutmaker.logic.utility.TAG

class DragDropAdapter(dataSet: List<MesocycleAndMesocycleType>) :
    DragDropSwipeAdapter<MesocycleAndMesocycleType, DragDropAdapter.ViewHolder>(dataSet) {

    private var list: List<MesocycleAndMesocycleType> = dataSet


    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemMesocycleName)
        val itemTypeName: TextView = itemView.findViewById(R.id.itemMesocycleTypeName)
        val dragIcon: ImageView = itemView.findViewById(R.id.itemMesocycleDragIcon)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "Click: ${dataSet[position].mesocycle.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getViewHolder(itemView: View) = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: MesocycleAndMesocycleType,
        viewHolder: DragDropAdapter.ViewHolder,
        position: Int
    ): View? {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(
        item: MesocycleAndMesocycleType,
        viewHolder: DragDropAdapter.ViewHolder,
        position: Int
    ) {
        viewHolder.itemName.text = dataSet[position].mesocycle.name
        viewHolder.itemTypeName.text = dataSet[position].mesocycleType.name
    }

    override fun onDragFinished(item: MesocycleAndMesocycleType, viewHolder: ViewHolder) {
        super.onDragFinished(item, viewHolder)
        Log.d(TAG, "onDragFinished: $dataSet")
    }

    fun setData(newList: List<MesocycleAndMesocycleType>) {
        list = newList
        notifyDataSetChanged()
    }
}