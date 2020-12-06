package com.octaneee.workoutmaker.ui.fragment.body

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.logic.utility.DrawableHelper

class BodyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_body, container, false)

        val manFigureFront = view.findViewById<ImageView>(R.id.bodyFragmentManFigureFront)
        val manFigureBack = view.findViewById<ImageView>(R.id.bodyFragmentManFigureBack)
        setImageView(manFigureFront, manFigureBack)

        view.findViewById<FloatingActionButton>(R.id.bodyFragmentManFigureRotateFAB)
            .setOnClickListener {
                rotateFigure(manFigureFront, manFigureBack)
            }

        return view
    }

    private fun rotateFigure(manFigureFront: ImageView?, manFigureBack: ImageView?) {
        manFigureBack?.isVisible = !manFigureBack?.isVisible!!
        manFigureFront?.isVisible = !manFigureFront?.isVisible!!
    }

    private fun setImageView(manFigureFront: ImageView?, manFigureBack: ImageView?) {
        setDrawableToImage(
            manFigureFront,
            DrawableHelper.getLayerDrawable(DrawableHelper.ManFigure.FRONT, requireContext()),
            true
        )

        setDrawableToImage(
            manFigureBack,
            DrawableHelper.getLayerDrawable(DrawableHelper.ManFigure.BACK, requireContext()),
            false
        )
    }

    private fun setDrawableToImage(
        imageView: ImageView?,
        layerDrawable: LayerDrawable?,
        visible: Boolean
    ) {
        imageView?.setImageDrawable(layerDrawable)
        imageView?.isVisible = visible
    }
}