package com.octaneee.workoutmaker.ui.bottom_sheet.exercise_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.Equipment
import com.octaneee.workoutmaker.model.entity.ExerciseType
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.other.ExerciseOrderType
import kotlinx.android.synthetic.main.bottom_sheet_exercise_filter.*

class ExerciseFilterBottomSheetFragment(private val listener: ExerciseFilterBottomSheetFragmentListener) :
    BottomSheetDialogFragment() {

    private val viewModel: ExerciseFilterBottomSheetViewModel by viewModels()

    private lateinit var radioGroup: RadioGroup
    private lateinit var muscleSpinner: Spinner
    private lateinit var equipmentSpinner: Spinner
    private lateinit var exerciseTypeSpinner: Spinner

    private lateinit var muscleSpinnerAdapter: ArrayAdapter<Muscle>
    private lateinit var equipmentSpinnerAdapter: ArrayAdapter<Equipment>
    private lateinit var exerciseTypeSpinnerAdapter: ArrayAdapter<ExerciseType>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_exercise_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        setupRadioGroup()
        setupMuscleSpinner()
        setupExerciseTypeSpinner()
        setupEquipmentSpinner()

        setupValue()
    }

    private fun setupValue() {
        val checkedItem = when (viewModel.orderType) {
            ExerciseOrderType.ORDER_BY_MUSCLE -> R.id.bottomSheetExerciseFilterMuscleRadioButton
            ExerciseOrderType.ORDER_BY_EXERCISE_TYPE -> R.id.bottomSheetExerciseFilterExerciseTypeRadioButton
            ExerciseOrderType.ORDER_BY_EQUIPMENT -> R.id.bottomSheetExerciseFilterEquipmentRadioButton
        }
        radioGroup.check(checkedItem)

        viewModel.muscle?.let {
            muscleSpinner.setSelection(viewModel.muscleDataSet.indexOf(it) + 1)
        }
        viewModel.equipment?.let {
            equipmentSpinner.setSelection(viewModel.equipmentDataSet.indexOf(it) + 1)
        }
        viewModel.exerciseType?.let {
            exerciseTypeSpinner.setSelection(viewModel.exerciseTypeDataSet.indexOf(it) + 1)
        }
    }

    fun setup(
        orderType: ExerciseOrderType,
        muscle: Muscle?,
        equipment: Equipment?,
        exerciseType: ExerciseType?
    ) {
        viewModel.orderType = orderType
        viewModel.muscle = muscle
        viewModel.equipment = equipment
        viewModel.exerciseType = exerciseType
    }

    fun updateMuscleDataSet(dataSet: List<Muscle>) {
        val list = dataSet.toMutableList()
        list.add(0, Muscle("All", ""))
        viewModel.muscleDataSet = list
    }

    fun updateEquipmentDataSet(dataSet: List<Equipment>) {
        val list = dataSet.toMutableList()
        list.add(0, Equipment("All", ""))
        viewModel.equipmentDataSet = list
    }

    fun updateExerciseTypeDataSet(dataSet: List<ExerciseType>) {
        val list = dataSet.toMutableList()
        list.add(0, ExerciseType("All", ""))
        viewModel.exerciseTypeDataSet = list
    }

    private fun setupEquipmentSpinner() {
        equipmentSpinner = bottomSheetExerciseFilterEquipmentSpinner
        equipmentSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            viewModel.equipmentDataSet
        )
        equipmentSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)

        equipmentSpinner.adapter = equipmentSpinnerAdapter
        equipmentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = if (position > 0) viewModel.equipmentDataSet[position] else null
                listener.onExerciseFilterBottomSheetFragmentEquipmentSpinnerItemClick(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupExerciseTypeSpinner() {
        exerciseTypeSpinner = bottomSheetExerciseFilterExerciseTypeSpinner
        exerciseTypeSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            viewModel.exerciseTypeDataSet
        )
        exerciseTypeSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)

        exerciseTypeSpinner.adapter = exerciseTypeSpinnerAdapter
        exerciseTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = if (position > 0) viewModel.exerciseTypeDataSet[position] else null
                listener.onExerciseFilterBottomSheetFragmentExerciseTypeSpinnerItemClick(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupMuscleSpinner() {
        muscleSpinner = bottomSheetExerciseFilterMuscleSpinner
        muscleSpinnerAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.item_spinner,
                viewModel.muscleDataSet
            )
        muscleSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)

        muscleSpinner.adapter = muscleSpinnerAdapter
        muscleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = if (position > 0) viewModel.muscleDataSet[position] else null
                listener.onExerciseFilterBottomSheetFragmentMuscleSpinnerItemClick(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupRadioGroup() {
        radioGroup = bottomSheetExerciseFilterRadioGroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val orderType = when (checkedId) {
                R.id.bottomSheetExerciseFilterMuscleRadioButton -> ExerciseOrderType.ORDER_BY_MUSCLE
                R.id.bottomSheetExerciseFilterExerciseTypeRadioButton -> ExerciseOrderType.ORDER_BY_EXERCISE_TYPE
                else -> ExerciseOrderType.ORDER_BY_EQUIPMENT
            }
            listener.onExerciseFilterBottomSheetFragmentCheckedChange(orderType)
        }
    }

    private fun setupButton() {
        bottomSheetExerciseFilterApplyButton.setOnClickListener {
            listener.onExerciseFilterBottomSheetFragmentApplyButtonClick()
        }
    }

    interface ExerciseFilterBottomSheetFragmentListener {
        fun onExerciseFilterBottomSheetFragmentApplyButtonClick()
        fun onExerciseFilterBottomSheetFragmentCheckedChange(orderType: ExerciseOrderType)
        fun onExerciseFilterBottomSheetFragmentMuscleSpinnerItemClick(muscle: Muscle?)
        fun onExerciseFilterBottomSheetFragmentExerciseTypeSpinnerItemClick(exerciseType: ExerciseType?)
        fun onExerciseFilterBottomSheetFragmentEquipmentSpinnerItemClick(equipment: Equipment?)
    }
}