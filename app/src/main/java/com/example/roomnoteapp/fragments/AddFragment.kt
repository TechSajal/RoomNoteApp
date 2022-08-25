package com.example.roomnoteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.roomnoteapp.R
import com.example.roomnoteapp.data.UserViewModel

class AddFragment : Fragment() {
private lateinit var mUserViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.findViewById<ImageView>(R.id.etdelete_one).setOnClickListener {
            insertDataToDataBase()
        }
      return view
    }

    private fun insertDataToDataBase() {
        val nodetitle:
    }


}