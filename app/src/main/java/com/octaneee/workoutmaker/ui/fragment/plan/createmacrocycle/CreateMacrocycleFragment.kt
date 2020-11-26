package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.adapter.DragDropAdapter

class CreateMacrocycleFragment : Fragment() {

    private lateinit var dragDropAdapter: DragDropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setUpRecyclerView()

        return inflater.inflate(R.layout.fragment_create_macrocycle, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_macrocycle_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createMacrocycleFragmentMenuSave -> menuSave()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun menuSave() {
        Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        //dragDropAdapter = DragDropAdapter()
    }
}