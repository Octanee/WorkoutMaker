package com.octaneee.workoutmaker.logic.utility

import androidx.lifecycle.MutableLiveData
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun DragDropSwipeRecyclerView.disableDragDirection() {
    this.disableDragDirection(DragDropSwipeRecyclerView.ListOrientation.DirectionFlag.UP)
    this.disableDragDirection(DragDropSwipeRecyclerView.ListOrientation.DirectionFlag.DOWN)
}