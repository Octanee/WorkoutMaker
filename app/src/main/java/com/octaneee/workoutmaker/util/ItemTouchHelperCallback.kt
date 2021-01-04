package com.octaneee.workoutmaker.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(
    private val adapter: ItemTouchHelperAdapter,
    dragDirs: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    swipeDirs: Int = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
    private val rightBackgroundColor: Int = Color.BLUE,
    private val leftBackgroundColor: Int = Color.RED
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (direction) {
            ItemTouchHelper.LEFT -> adapter.onItemSwipedLeft(viewHolder.adapterPosition)
            ItemTouchHelper.RIGHT -> adapter.onItemSwipeRight(viewHolder.adapterPosition)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView
            val paint = Paint()

            when {
                dX > 0f -> paint.color = rightBackgroundColor
                dX == 0f -> paint.color = Color.TRANSPARENT
                dX < 0f -> paint.color = leftBackgroundColor
            }

            val offset = 5f

            c.drawRoundRect(
                itemView.left.toFloat() + offset,
                itemView.top.toFloat(),
                itemView.right.toFloat() - offset,
                itemView.bottom.toFloat(),
                15f,
                15f,
                paint
            )
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}























