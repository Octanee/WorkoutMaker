package com.octaneee.workoutmaker.logic.utility

import androidx.lifecycle.MutableLiveData
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import kotlin.random.Random

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun <T> List<T>.random(): T? = if (size > 0) get(Random.nextInt(size)) else null

fun DragDropSwipeRecyclerView.disableDragDirection() {
    this.disableDragDirection(DragDropSwipeRecyclerView.ListOrientation.DirectionFlag.UP)
    this.disableDragDirection(DragDropSwipeRecyclerView.ListOrientation.DirectionFlag.DOWN)
}