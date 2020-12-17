package com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.entity.Muscle
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import com.octaneee.workoutmaker.other.DrawableHelper
import com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype.ExerciseListSortTypeFragment
import com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype.ExerciseListSortTypeFragmentDirections

class ExerciseListSortTypeFragmentRecyclerViewAdapter(private var dataSet: List<*>) :
    RecyclerView.Adapter<ExerciseListSortTypeFragmentRecyclerViewAdapter.ViewHolder>() {

    private var sortType: ExerciseListSortTypeFragment.SortType =
        ExerciseListSortTypeFragment.SortType.Muscle

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView =
            itemView.findViewById(R.id.itemExerciseListSortTypeImageView)
        private val textView: TextView =
            itemView.findViewById(R.id.itemExerciseListSortTypeTextView)

        fun bind(name: String, drawable: String) {
            textView.text = name
            imageView.setImageDrawable(DrawableHelper.getDrawable(drawable, itemView.context))
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val action = when (sortType) {
                    ExerciseListSortTypeFragment.SortType.Equipment -> {
                        val item = dataSet[position] as Equipment
                        ExerciseListSortTypeFragmentDirections.actionExerciseListSortTypeFragmentToExerciseListFragment(
                            null,
                            item,
                            null
                        )
                    }
                    ExerciseListSortTypeFragment.SortType.ExerciseType -> {
                        val item = dataSet[position] as ExerciseType
                        ExerciseListSortTypeFragmentDirections.actionExerciseListSortTypeFragmentToExerciseListFragment(
                            null,
                            null,
                            item
                        )
                    }
                    ExerciseListSortTypeFragment.SortType.Muscle -> {
                        val item = dataSet[position] as Muscle
                        ExerciseListSortTypeFragmentDirections.actionExerciseListSortTypeFragmentToExerciseListFragment(
                            item,
                            null,
                            null
                        )
                    }
                }
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_list_sort_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (sortType) {
            ExerciseListSortTypeFragment.SortType.Equipment -> {
                val item = dataSet[position] as Equipment
                holder.bind(item.equipmentName, item.drawable)
            }
            ExerciseListSortTypeFragment.SortType.ExerciseType -> {
                val item = dataSet[position] as ExerciseType
                holder.bind(item.exerciseTypeName, item.drawable)
            }
            ExerciseListSortTypeFragment.SortType.Muscle -> {
                val item = dataSet[position] as Muscle
                holder.bind(item.muscleName, item.drawable)
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateDataSet(
        newSortType: ExerciseListSortTypeFragment.SortType,
        newDateSet: List<BaseEntity>
    ) {
        sortType = newSortType
        dataSet = newDateSet
        notifyDataSetChanged()
    }

}