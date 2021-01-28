package com.octaneee.workoutmaker.ui.main.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.octaneee.workoutmaker.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val stringId = resources.getIdentifier("save", "string", requireContext().packageName)
        val string = resources.getString(stringId)
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}