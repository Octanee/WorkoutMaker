package com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.viewmodel.CreateMesocycleFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_mesocycle.*


class CreateMesocycleFragment : Fragment() {

    private val viewModel: CreateMesocycleFragmentViewModel by viewModels()

    private lateinit var mesocycleTypeList: List<MesocycleType>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_mesocycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mesocycleTypeList.observe(viewLifecycleOwner, Observer {
            mesocycleTypeList = it
        })

        setUpCreateMesocycleFragmentPickMesocycleTypeButton()
        setUpCreateMesocycleFragmentNewTrainingFAB()
    }

    private fun setUpCreateMesocycleFragmentNewTrainingFAB() {
        createMesocycleFragmentNewTrainingFAB.setOnClickListener {
            findNavController().navigate(R.id.action_createMesocycleFragment_to_createTrainingFragment)
        }
    }

    private fun setUpCreateMesocycleFragmentPickMesocycleTypeButton() {
        createMesocycleFragmentPickMesocycleTypeButton.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(requireContext())

            val nameList = mutableListOf<String>()
            for (mesocycleType in mesocycleTypeList) {
                nameList.add(mesocycleType.name)
            }

            with(builder) {
                setTitle("Pick mesocycle type:")
                setSingleChoiceItems(nameList.toTypedArray(), -1) { _, _ -> }
                setPositiveButton("Submit") { dialog, _ ->
                    val position = (dialog as AlertDialog).listView.checkedItemPosition
                    if (position != -1) {
                        val selectedItem = mesocycleTypeList[position]
                        createMesocycleFragmentMesocycleTypeName.text = selectedItem.name
                        viewModel.mesocycleType = selectedItem
                    }
                }
                setNegativeButton("Cancel", null)
                setCancelable(false)
            }

            val dialog = builder.create()
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
            dialog.listView.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = position != -1
                }

        }
    }

}