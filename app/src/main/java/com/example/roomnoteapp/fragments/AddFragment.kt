package com.example.roomnoteapp.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomnoteapp.R
import com.example.roomnoteapp.data.User
import com.example.roomnoteapp.data.UserViewModel

class AddFragment : Fragment() {
    private lateinit var mUserViewModel:UserViewModel
    private var priority:Int = 1
    private lateinit var notetitle:EditText
    private lateinit var  notsubtitle:EditText
    private lateinit var note:EditText
    private lateinit var priogreen:ImageView
    private lateinit var prioyellow:ImageView
    private lateinit var priored:ImageView
    private lateinit var colorvi:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        notetitle = view.findViewById(R.id.etNoteTitle)
        notsubtitle = view.findViewById(R.id.etNoteSubTitle)
        note = view.findViewById(R.id.etNote)
        priogreen = view.findViewById(R.id.green)
        prioyellow = view.findViewById(R.id.yellow)
        priored = view.findViewById(R.id.red)
        priogreen.setImageResource(R.drawable.ic_baseline_done_24)
        colorvi = view.findViewById(R.id.colorView)
        priority()
        view.findViewById<ImageView>(R.id.doneadd).setOnClickListener {
            insertDataToDataBase()
        }
      return view
    }

    private fun insertDataToDataBase() {
        val et_nodetitle = notetitle.text.toString()
        val et_nodesubtitle = notsubtitle.text.toString()
        val et_note = note.text.toString()
        if (inputcheck(et_nodetitle,et_nodesubtitle,et_note)){
            val user = User(0,et_nodetitle,et_nodesubtitle,et_note,priority)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Note Added",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Plz Fill All The Fields",Toast.LENGTH_LONG).show()
        }

    }
    private fun inputcheck(notetitle:String,notesubtitle:String,note:String):Boolean{
        return !(TextUtils.isEmpty(notetitle) || TextUtils.isEmpty(notesubtitle) || TextUtils.isEmpty(note))
    }
    private fun priority(){
        priogreen.setOnClickListener {
            priority = 1
            priogreen.setImageResource(R.drawable.ic_baseline_done_24)
            colorvi.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_greencircle))
            prioyellow.setImageResource(0)
            priored.setImageResource(0)

        }
        prioyellow.setOnClickListener {
            priority = 2
            prioyellow.setImageResource(R.drawable.ic_baseline_done_24)
            colorvi.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_yellowcircle))
            priogreen.setImageResource(0)
            priored.setImageResource(0)
        }

        priored.setOnClickListener {
            priority =3
            priored.setImageResource(R.drawable.ic_baseline_done_24)
            colorvi.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_redcircle))
            priogreen.setImageResource(0)
            prioyellow.setImageResource(0)
        }
    }


}