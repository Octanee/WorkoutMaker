package com.octaneee.workoutmaker.logic.other

import androidx.lifecycle.LiveData

class ListLiveData<T> : LiveData<ListHolder<T>>() {

    val size: Int
        get() {
            return value?.size() ?: -1
        }

    fun add(item: T, position: Int = value?.size() ?: 0) {
        value?.add(position, item)
        value = value
    }

    fun removeAt(position: Int) {
        value?.removeAt(position)
        value = value
    }

    fun change(position: Int, item: T) {
        value?.change(position, item)
        value = value
    }

    operator fun get(position: Int): T? {
        return value?.list?.get(position)
    }
}

