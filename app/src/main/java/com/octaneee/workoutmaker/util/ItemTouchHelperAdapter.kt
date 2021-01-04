package com.octaneee.workoutmaker.util

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemSwipedLeft(position: Int)

    fun onItemSwipeRight(position: Int)
}