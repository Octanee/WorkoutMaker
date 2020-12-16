package com.octaneee.workoutmaker.ui.fragment.plan.plan

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.plan.viewmodel.PlanFragmentViewModel
import kotlinx.android.synthetic.main.fragment_plan.view.*

class PlanFragment : Fragment() {

    private val viewModel: PlanFragmentViewModel by viewModels()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_plan, container, false)

        mainActivityViewModel.userAndMacrocycle.observe(viewLifecycleOwner, Observer {
            viewModel.macrocycleWithMesocycles = it.macrocycleWithMesocycles
            if (viewModel.macrocycleWithMesocycles != null) {
                setMacrocycleConstraintLayout(
                    view.planFragmentCurrentMacrocycleConstraintLayout,
                    view.planFragmentNoMacrocycleConstraintLayout,
                    view.planFragmentCurrentMacrocycleNameTextView
                )
            } else {
                setNoMacrocycleConstraintLayout(
                    view.planFragmentCurrentMacrocycleConstraintLayout,
                    view.planFragmentNoMacrocycleConstraintLayout,
                    view.planFragmentCreateMacrocycleButton
                )
            }
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.plan_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.planFragmentMenuEdit -> menuEdit()
            R.id.planFragmentMenuChange -> menuChange()
            R.id.planFragmentMenuDelete -> menuDelete()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMacrocycleConstraintLayout(
        macrocycleConstraintLayout: ConstraintLayout,
        noMacrocycleConstraintLayout: ConstraintLayout,
        nameTextView: TextView
    ) {
        macrocycleConstraintLayout.visibility = View.VISIBLE
        noMacrocycleConstraintLayout.visibility = View.GONE
        nameTextView.text = viewModel.macrocycleWithMesocycles!!.macrocycle.macrocycleName
    }

    private fun setNoMacrocycleConstraintLayout(
        macrocycleConstraintLayout: ConstraintLayout,
        noMacrocycleConstraintLayout: ConstraintLayout,
        button: Button
    ) {
        macrocycleConstraintLayout.visibility = View.GONE
        noMacrocycleConstraintLayout.visibility = View.VISIBLE
        button.setOnClickListener {
            val action = PlanFragmentDirections.actionPlanFragmentToMacrocycleListFragment()
            findNavController().navigate(action)
        }
    }

    private fun menuEdit() {
        if (viewModel.macrocycleWithMesocycles == null) {
            Toast.makeText(context, "Pick a macrocycle", Toast.LENGTH_LONG).show()
            return
        }
        mainActivityViewModel.macrocycleWithMesocycles = viewModel.macrocycleWithMesocycles
        val action =
            PlanFragmentDirections.actionPlanFragmentToCreateMacrocycleFragment()
        findNavController().navigate(action)

    }

    private fun menuChange() {
        val action = PlanFragmentDirections.actionPlanFragmentToMacrocycleListFragment()
        findNavController().navigate(action)
    }

    private fun menuDelete() {
        if (viewModel.macrocycleWithMesocycles == null) {
            Toast.makeText(context, "Pick a macrocycle", Toast.LENGTH_LONG).show()
            return
        }
        val builder = AlertDialog.Builder(activity)
        with(builder) {
            setTitle("Delete Macrocycle")
            setMessage("Are you sure you want to delete this macrocycle?")
            setPositiveButton("Yes") { _, _ ->
                viewModel.macrocycleWithMesocycles?.let { viewModel.deleteMacrocycle(it.macrocycle) }
            }
            setNegativeButton("No", null)
            setIcon(R.drawable.ic_delete)
            show()

        }
    }
}

