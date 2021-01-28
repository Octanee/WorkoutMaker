package com.octaneee.workoutmaker.other.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.addItem(item: T) {
    val oldValue = this.value ?: mutableListOf()
    oldValue.add(item)
    this.value = oldValue
}

fun <T> MutableLiveData<MutableList<T>>.addItemAt(index: Int, item: T) {
    val oldValue = this.value ?: mutableListOf()
    oldValue.add(index, item)
    this.value = oldValue
}

fun <T> MutableLiveData<MutableList<T>>.addAll(list: MutableList<T>) {
    this.value = list
    this.notifyDataSetChanged()
}

fun <T> MutableLiveData<MutableList<T>>.removeItemAt(index: Int) {
    if (!this.value.isNullOrEmpty()) {
        val oldValue = this.value
        oldValue?.removeAt(index)
        this.value = oldValue
    } else {
        this.value = mutableListOf()
    }
}

fun <T> MutableLiveData<MutableList<T>>.removeItem(item: T) {
    if (!this.value.isNullOrEmpty()) {
        val oldValue = this.value
        oldValue?.remove(item)
        this.value = oldValue
    } else {
        this.value = mutableListOf()
    }
}

fun <T> MutableLiveData<T>.notifyDataSetChanged() {
    this.value = this.value
}
