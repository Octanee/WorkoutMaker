package com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings
import com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.CreateMesocycleFragmentDirections
import com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.viewmodel.CreateMesocycleFragmentViewModel

class CreateMesocycleFragmentDragDropAdapter(
    dataSet: List<MicrocycleWithTrainings>,
    val viewModel: CreateMesocycleFragmentViewModel
) :
    DragDropSwipeAdapter<MicrocycleWithTrainings, CreateMesocycleFragmentDragDropAdapter.ViewHolder>(
        dataSet
    ) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val dragIcon: ImageView = itemView.findViewById(R.id.itemTrainingDragIcon)
        val textView: TextView = itemView.findViewById(R.id.itemTrainingTextView)
        val number: TextView = itemView.findViewById(R.id.itemTrainingNumberTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = dataSet[position]

                val action =
                    CreateMesocycleFragmentDirections.actionCreateMesocycleFragmentToCreateMicrocycleFragment(
                        viewModel.macrocycleWithMesocycles,
                        viewModel.mesocycleAndMesocycleTypeWithMicrocycles,
                        item
                    )
            }
        }

        fun bind(item: MicrocycleWithTrainings) {

        }
    }

    override fun getViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: MicrocycleWithTrainings,
        viewHolder: ViewHolder,
        position: Int
    ): View {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(
        item: MicrocycleWithTrainings,
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bind(item)
    }
}