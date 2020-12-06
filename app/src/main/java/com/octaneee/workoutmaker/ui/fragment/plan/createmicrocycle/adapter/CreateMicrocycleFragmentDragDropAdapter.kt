package com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.TrainingWithSets
import com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.CreateMicrocycleFragmentDirections
import com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.viewmodel.CreateMicrocycleFragmentViewModel

class CreateMicrocycleFragmentDragDropAdapter(
    dataSet: List<TrainingWithSets>,
    val viewModel: CreateMicrocycleFragmentViewModel
) : DragDropSwipeAdapter<TrainingWithSets, CreateMicrocycleFragmentDragDropAdapter.ViewHolder>(
    dataSet
) {

    inner class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val dragIcon: ImageView = itemView.findViewById(R.id.itemTrainingDragIcon)
        val number: TextView = itemView.findViewById(R.id.itemTrainingNumberTextView)
        val textView: TextView = itemView.findViewById(R.id.itemTrainingTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = dataSet[position]

                val action =
                    CreateMicrocycleFragmentDirections.actionCreateMicrocycleFragmentToCreateTrainingFragment(
                        viewModel.macrocycleWithMesocycles,
                        viewModel.mesocycleAndMesocycleTypeWithMicrocycles,
                        viewModel.microcycleWithTrainings,
                        item
                    )
                itemView.findNavController().navigate(action)
            }
        }

        fun bind(item: TrainingWithSets) {
            number.text = item.sets.size.toString()
            textView.text = item.training.name
        }
    }

    override fun getViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun getViewToTouchToStartDraggingItem(
        item: TrainingWithSets,
        viewHolder: ViewHolder,
        position: Int
    ): View {
        return viewHolder.dragIcon
    }

    override fun onBindViewHolder(item: TrainingWithSets, viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(item)
    }
}