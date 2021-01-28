package com.octaneee.workoutmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.Series
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.item_create_set.view.*

class SetListBottomSheetAdapter(
    private val itemListener: SetListItemListener,
) : RecyclerView.Adapter<SetListBottomSheetAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    private var dataSet: List<Series> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_create_set, parent, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View, val listener: SetListItemListener) :
        RecyclerView.ViewHolder(itemView) {

        private val number = itemView.itemCreateSetItemNumberTextView
        private val minimum = itemView.itemCreateSetItemMinimumEditText
        private val maximum = itemView.itemCreateSetItemMaximumEditText
        private val restTime = itemView.itemCreateSetItemRestTimeEditText

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: Series) {
            number.text = (adapterPosition + 1).toString()
            minimum.setText(item.minimum?.toString() ?: "")
            maximum.setText(item.maximum?.toString() ?: "")
            restTime.setText(item.restTime?.toString() ?: "")

            setupMinimum()
            setupMaximum()
            setupRestTime()
        }

        private fun setupMinimum() {
            minimum.addTextChangedListener {
                val sequence = it.toString()
                val value = if (sequence.isNotEmpty()) sequence.toInt() else null
                listener.onSetListItemMinimumChange(adapterPosition, value)
            }
        }

        private fun setupMaximum() {
            maximum.addTextChangedListener {
                val sequence = it.toString()
                val value = if (sequence.isNotEmpty()) sequence.toInt() else null
                listener.onSetListItemMaximumChange(adapterPosition, value)
            }
        }

        private fun setupRestTime() {
            restTime.addTextChangedListener {
                val sequence = it.toString()
                val value = if (sequence.isNotEmpty()) sequence.toInt() else null
                listener.onSetListItemRestTimeChange(adapterPosition, value)
            }
        }
    }

    fun updateDataSet(newDataSet: List<Series>) {
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

    interface SetListItemListener : ItemListener {
        fun onSetListItemMinimumChange(position: Int, value: Int?)
        fun onSetListItemMaximumChange(position: Int, value: Int?)
        fun onSetListItemRestTimeChange(position: Int, value: Int?)
    }
}