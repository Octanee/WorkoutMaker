package com.octaneee.workoutmaker.logic.utility

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Muscle


object DrawableHelper {

    private val manFigureFront = listOf(
        R.drawable.man_figure_front_abs,
        R.drawable.man_figure_front_adductor,
        R.drawable.man_figure_front_biceps,
        R.drawable.man_figure_front_calves,
        R.drawable.man_figure_front_chest,
        R.drawable.man_figure_front_chest,
        R.drawable.man_figure_front_forearms,
        R.drawable.man_figure_front_oblique,
        R.drawable.man_figure_front_quadriceps,
        R.drawable.man_figure_front_serratus,
        R.drawable.man_figure_front_shoulders,
        R.drawable.man_figure_front_traps,
        R.drawable.man_figure_front_not_muscle
    )

    private val manFigureBack = listOf(
        R.drawable.man_figure_back_calves,
        R.drawable.man_figure_back_forearms,
        R.drawable.man_figure_back_glutes,
        R.drawable.man_figure_back_hamstring,
        R.drawable.man_figure_back_lats,
        R.drawable.man_figure_back_low_traps,
        R.drawable.man_figure_back_oblique,
        R.drawable.man_figure_back_shoulders,
        R.drawable.man_figure_back_teres,
        R.drawable.man_figure_back_top_traps,
        R.drawable.man_figure_back_triceps,
        R.drawable.man_figure_back_not_muscle
    )

    private val colors = listOf(
        R.color.muscle_basic,
        R.color.muscle_fatigue_1,
        R.color.muscle_fatigue_2,
        R.color.muscle_fatigue_3,
        R.color.muscle_fatigue_4,
        R.color.muscle_fatigue_5,
    )

    enum class ManFigure {
        FRONT,
        BACK
    }

    fun getDrawableForMuscle(muscle: Muscle, context: Context): Drawable? {
        return ResourcesCompat.getDrawable(
            context.resources,
            context.resources.getIdentifier(muscle.drawable, "drawable", context.packageName),
            null
        )
    }

    fun getDrawable(name: String, context: Context): Drawable? {
        return ResourcesCompat.getDrawable(
            context.resources,
            context.resources.getIdentifier(name, "drawable", context.packageName),
            null
        )
    }

    fun getLayerDrawable(figure: ManFigure, context: Context): LayerDrawable {
        return when (figure) {
            ManFigure.FRONT -> createLayerDrawable(manFigureBack, context)
            ManFigure.BACK -> createLayerDrawable(manFigureFront, context)
        }
    }

    private fun createLayerDrawable(drawableList: List<Int>, context: Context): LayerDrawable {
        val layers = mutableListOf<Drawable>()

        drawableList.forEach {
            var color = getRandomColor()

            if (it == R.drawable.man_figure_back_not_muscle || it == R.drawable.man_figure_front_not_muscle) {
                color = colors[0]
            }
            val drawable = ResourcesCompat.getDrawable(context.resources, it, null)

            if (drawable != null) {
                DrawableCompat.setTint(drawable, context.resources.getColor(color, null))
                layers.add(drawable)
            }
        }

        return LayerDrawable(layers.toTypedArray())
    }

    private fun getRandomColor(): Int {
        return colors.random()!!
    }
}