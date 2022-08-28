package com.example.roomnoteapp.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.ActionMenuView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomnoteapp.R
import com.example.roomnoteapp.ViewModel.UserViewModel
import com.example.roomnoteapp.fragments.list.ListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val adapter = ListAdapter()
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerViewlist)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        //user view modeld
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user->
            adapter.SetData(user)
        })
        view.findViewById<FloatingActionButton>(R.id.addnotes).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        view.findViewById<ActionMenuItemView>(R.id.app_delete).setOnClickListener {
                 deleteUser()
            }
        return view
    }


    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(),"All Note Successfully Deleted", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){ _,_ ->}
        builder.setTitle("Delete All")
        builder.setMessage("Are you sure you want to delete all notes?")
        builder.create().show()
    }

}