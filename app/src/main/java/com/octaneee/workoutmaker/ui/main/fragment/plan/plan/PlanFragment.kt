package com.octaneee.workoutmaker.ui.main.fragment.plan.plan

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_plan.*

@AndroidEntryPoint
class PlanFragment : Fragment(R.layout.fragment_plan) {

    private val viewModel: PlanViewModel by viewModels()
    private lateinit var editMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMacrocycle()

        planFragmentShowButton.setOnClickListener {
            findNavController().navigate(R.id.action_planFragment_to_macrocycleListFragment)
        }
    }

    private fun setupMacrocycle() {
        viewModel.currentMacrocycleId.observe(viewLifecycleOwner) {
            planFragmentNoMacrocycleConstraintLayout.visibility = View.VISIBLE
            it?.let {
                viewModel.setupCurrentMacrocycle(it)
                viewModel.currentMacrocycle?.observe(viewLifecycleOwner) { macrocycle ->
                    macrocycle?.let {
                        planFragmentNoMacrocycleConstraintLayout.visibility = View.GONE
                        planFragmentCurrentMacrocycleConstraintLayout.visibility =
                            View.VISIBLE
                        planFragmentCurrentMacrocycleNameTextView.text =
                            macrocycle.macrocycleName
                        editMenuItem.isVisible = true
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.plan_fragment_menu, menu)
        menu.forEach {
            if (it.itemId == R.id.planFragmentMenuEdit) {
                editMenuItem = it
                editMenuItem.isVisible = false
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.planFragmentMenuEdit -> menuEdit()
            R.id.planFragmentMenuChange -> menuChange()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuEdit() {
        val action =
            PlanFragmentDirections.actionPlanFragmentToMesocycleListFragment(viewModel.currentMacrocycle!!.value!!.macrocycleId)
        findNavController().navigate(action)
    }

    private fun menuChange() {
        findNavController().navigate(R.id.action_planFragment_to_macrocycleListFragment)
    }
}

