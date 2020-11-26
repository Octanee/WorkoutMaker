package com.octaneee.workoutmaker.ui.fragment.plan

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.activity.main.MainActivity
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.viewmodel.PlanFragmentViewModel
import kotlinx.android.synthetic.main.fragment_plan.*

class PlanFragment : Fragment() {

    private val viewModel: PlanFragmentViewModel by viewModels()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainActivityViewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivityViewModel.userAndMicrocycle.observe(viewLifecycleOwner, Observer {
            val userAndMacrocycle = it
            if (userAndMacrocycle.macrocycle == null) {
                setNoMacrocycleConstraintLayout()
            } else {
                viewModel.macrocycle = userAndMacrocycle.macrocycle
            }
        })

        return inflater.inflate(R.layout.fragment_plan, container, false)
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

    private fun setNoMacrocycleConstraintLayout() {
        planFragmentCreateMacrocycleButton.setOnClickListener {
            findNavController().navigate(R.id.action_planFragment_to_macrocycleListFragment)
        }
        planFragmentNoMacrocycleConstraintLayout.visibility = View.VISIBLE
    }

    private fun menuEdit() {
        //TODO Dodac edycje macrocycle
        Toast.makeText(activity, "Edit", Toast.LENGTH_SHORT).show()
    }

    private fun menuChange() {
        findNavController().navigate(R.id.action_planFragment_to_macrocycleListFragment)
    }

    private fun menuDelete() {
        val builder = AlertDialog.Builder(activity)
        with(builder) {
            setTitle("Delete Macrocycle")
            setMessage("Are you sure you want to delete this macrocycle?")
            setPositiveButton("Yes") { dialog, which ->
                viewModel.deleteMacrocycle(viewModel.macrocycle)
            }
            setNegativeButton("No") { dialog, which ->

            }
            setIcon(R.drawable.ic_delete)
            show()
        }

    }


}

