package com.octaneee.workoutmaker.other.extension


fun <T> MutableList<T>.append(item: T): Int {
    add(size, item)
    return indexOf(item)
}

fun <T> MutableList<T>.addForPosition(item: T): Int {
    add(item)
    return indexOf(item)
}