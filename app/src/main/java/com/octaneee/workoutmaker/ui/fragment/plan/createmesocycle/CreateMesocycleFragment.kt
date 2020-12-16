package com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.adapter.CreateMesocycleFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.viewmodel.CreateMesocycleFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_mesocycle.*
import kotlinx.android.synthetic.main.fragment_create_mesocycle.view.*


class CreateMesocycleFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: CreateMesocycleFragmentViewModel by viewModels()
    private lateinit var adapter: CreateMesocycleFragmentDragDropAdapter

    companion object {
        const val TAG = "CreateMesocycleFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_mesocycle, container, false)

        setUpViewModel()
        setUpPickMesocycleTypeButton(view.createMesocycleFragmentPickMesocycleTypeButton)
        setUpNewTrainingFAB(view.createMesocycleFragmentNewTrainingFAB)
        setUpRecyclerView(view.createMesocycleFragmentRecyclerView)
        setUpNameEditText(view.createMesocycleFragmentNameEditText)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_mesocycle_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createMesocycleFragmentMenuSave -> menuSave()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuSave() {
        if (validateDate()) {
            mainActivityViewModel.macrocycleWithMesocycles!!.mesocycles.remove(mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles)
            mainActivityViewModel.macrocycleWithMesocycles!!.mesocycles.add(viewModel.mesocycleAndMesocycleTypeWithMicrocycles)
            mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles = null
            val action =
                CreateMesocycleFragmentDirections.actionCreateMesocycleFragmentToCreateMacrocycleFragment()
            findNavController().navigate(action)
        }
    }

    private fun validateDate(): Boolean {
        return checkName() //&& checkType() && checkMicrocycle()
    }

    private fun checkMicrocycle(): Boolean {
        return if (viewModel.mesocycleAndMesocycleTypeWithMicrocycles.microcycleWithTrainings.isEmpty()) {
            Toast.makeText(context, "Add microcycle", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkType(): Boolean {
        return if (viewModel.mesocycleAndMesocycleTypeWithMicrocycles.mesocycleType.mesocycleTypeName.isEmpty()) {
            Toast.makeText(context, "Pick mesocycle type", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkName(): Boolean {
        return if (viewModel.mesocycleAndMesocycleTypeWithMicrocycles.mesocycle.mesocycleName.isEmpty()) {
            Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun setUpRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        adapter = CreateMesocycleFragmentDragDropAdapter(
            viewModel.mesocycleAndMesocycleTypeWithMicrocycles.microcycleWithTrainings,
        )

        with(recyclerView) {
            adapter = this@CreateMesocycleFragment.adapter
            layoutManager = LinearLayoutManager(context)
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener()
            longPressToStartDragging = true
        }
    }

    private fun onItemSwipeListener() =
        object : OnItemSwipeListener<MicrocycleWithTrainings> {
            override fun onItemSwiped(
                position: Int,
                direction: OnItemSwipeListener.SwipeDirection,
                item: MicrocycleWithTrainings
            ): Boolean {
                return when (direction) {
                    OnItemSwipeListener.SwipeDirection.RIGHT_TO_LEFT -> {
                        swipeRightToLeft(item)
                    }
                    OnItemSwipeListener.SwipeDirection.LEFT_TO_RIGHT -> {
                        swipeLeftToRight(item)
                    }
                    else -> {
                        false
                    }
                }
            }
        }

    private fun swipeLeftToRight(item: MicrocycleWithTrainings): Boolean {
        mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles =
            viewModel.mesocycleAndMesocycleTypeWithMicrocycles
        mainActivityViewModel.microcycleWithTrainings = item

        val action =
            CreateMesocycleFragmentDirections.actionCreateMesocycleFragmentToCreateMicrocycleFragment()
        findNavController().navigate(action)
        return true
    }

    private fun swipeRightToLeft(item: MicrocycleWithTrainings): Boolean {
        viewModel.mesocycleAndMesocycleTypeWithMicrocycles.microcycleWithTrainings.remove(item)
        updateAdapterDataSet(viewModel.mesocycleAndMesocycleTypeWithMicrocycles.microcycleWithTrainings)
        return false
    }

    private fun updateAdapterDataSet(newDataSet: List<MicrocycleWithTrainings>) {
        adapter.updateDataSet(newDataSet)
    }

    private fun setUpNameEditText(editText: EditText) {
        editText.setText(viewModel.mesocycleAndMesocycleTypeWithMicrocycles.mesocycle.mesocycleName)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.mesocycleAndMesocycleTypeWithMicrocycles.mesocycle.mesocycleName =
                    "$sequence"
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setUpViewModel() {
        viewModel.mesocycleAndMesocycleTypeWithMicrocycles =
            if (mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles == null) {
                MesocycleAndMesocycleTypeWithMicrocycles(
                    Mesocycle("", 0, 0),
                    MesocycleType("")
                )
            } else {
                mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles!!
            }
    }

    private fun setUpNewTrainingFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {
            mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles =
                viewModel.mesocycleAndMesocycleTypeWithMicrocycles
            val action =
                CreateMesocycleFragmentDirections.actionCreateMesocycleFragmentToCreateMicrocycleFragment()
            findNavController().navigate(action)
        }
    }

    private fun setUpPickMesocycleTypeButton(button: Button) {
        button.setOnClickListener {
            viewModel.mesocycleTypeList.observe(viewLifecycleOwner, {
                val builder = MaterialAlertDialogBuilder(requireContext())

                val nameList = mutableListOf<String>()
                for (mesocycleType in it) {
                    nameList.add(mesocycleType.mesocycleTypeName)
                }

                with(builder) {
                    setTitle("Pick mesocycle type:")
                    setSingleChoiceItems(nameList.toTypedArray(), -1) { _, _ -> }
                    setPositiveButton("Submit") { dialog, _ ->
                        val position = (dialog as AlertDialog).listView.checkedItemPosition
                        if (position != -1) {
                            val selectedItem = viewModel.mesocycleTypeList.value!![position]
                            createMesocycleFragmentMesocycleTypeName.text =
                                selectedItem.mesocycleTypeName
                            viewModel.mesocycleAndMesocycleTypeWithMicrocycles.mesocycleType =
                                selectedItem
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
            })
        }
    }

}