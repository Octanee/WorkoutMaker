package com.octaneee.workoutmaker.logic.other

import androidx.recyclerview.widget.RecyclerView

data class ListHolder<T>(val list: MutableList<T> = mutableListOf()) {
    var index: Int = -1
    private var updateType: UpdateType? = null

    fun add(position: Int, item: T) {
        list.add(position, item)
        index = position
        updateType = UpdateType.INSERT
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        index = position
        updateType = UpdateType.REMOVE
    }

    fun change(position: Int, item: T) {
        list[position] = item
        index = position
        updateType = UpdateType.CHANGE
    }

    fun size(): Int {
        return list.size
    }

    fun applyChange(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        updateType?.notifyChange(adapter, index)
    }

    private enum class UpdateType {
        INSERT {
            override fun notifyChange(
                adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                position: Int
            ) = adapter.notifyItemInserted(position)
        },
        REMOVE {
            override fun notifyChange(
                adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                position: Int
            ) = adapter.notifyItemRemoved(position)
        },
        CHANGE {
            override fun notifyChange(
                adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                position: Int
            ) = adapter.notifyItemChanged(position)
        };

        abstract fun notifyChange(
            adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
            position: Int
        )
    }
}