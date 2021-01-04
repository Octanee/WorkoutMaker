package com.octaneee.workoutmaker.util

interface ItemListener {

    fun onItemClick(position: Int)
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemSwipedLeft(position: Int)
    fun onItemSwipeRight(position: Int)
}