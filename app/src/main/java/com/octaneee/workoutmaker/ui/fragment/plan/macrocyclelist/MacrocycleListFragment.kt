package com.octaneee.workoutmaker.ui.fragment.plan.macrocyclelist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R


class MacrocycleListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_macrocycle_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.macrocycle_list_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.macrocycleListFragmentMenuAdd -> menuAdd()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun menuAdd() {
        findNavController().navigate(R.id.action_macrocycleListFragment_to_createMacrocycleFragment)
    }
}