package com.example.roomnoteapp.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.roomnoteapp.Model.User
import com.example.roomnoteapp.R
import com.example.roomnoteapp.ViewModel.UserViewModel

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var priogreen: ImageView
    private lateinit var prioyellow: ImageView
    private lateinit var priored: ImageView
    private var priority:Int = 0
    private lateinit var colorvi:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val  view = inflater.inflate(R.layout.fragment_update, container, false)
       mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        priogreen = view.findViewById(R.id.upgreen)
        prioyellow = view.findViewById(R.id.upyellow)
        priored = view.findViewById(R.id.upred)
        colorvi = view.findViewById(R.id.upcolorView)
        view.findViewById<ImageView>(R.id.uparrowback).setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        view.findViewById<EditText>(R.id.upNoteTitle).setText(args.currentuser.noteTitle)
        view.findViewById<EditText>(R.id.upNoteSubTitle).setText(args.currentuser.notesubtitle)
        view.findViewById<EditText>(R.id.upNote).setText(args.currentuser.note)
        view.findViewById<ImageView>(R.id.updelete_one).setOnClickListener {
            deleteuser()
        }
        when(args.currentuser.preference){
            1 -> {
                colorvi.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_greencircle))
                priogreen.setImageResource(R.drawable.ic_baseline_done_24)
                priority =1
            }
            2 -> {
                colorvi.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_yellowcircle))
                prioyellow.setImageResource(R.drawable.ic_baseline_done_24)
                priority =2
            }
            3 -> {
                colorvi.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_redcircle))
                priored.setImageResource(R.drawable.ic_baseline_done_24)
                priority =3
            }
        }
        priority()
        view.findViewById<ImageView>(R.id.doneupdate).setOnClickListener {
            updateitem()
        }

        return view
    }
    private fun updateitem(){
        val title =    view?.findViewById<EditText>(R.id.upNoteTitle)?.text.toString()
        val subtitle = view?.findViewById<EditText>(R.id.upNoteSubTitle)?.text.toString()
        val note =     view?.findViewById<EditText>(R.id.upNote)?.text.toString()
        if (inputcheck(title,subtitle,note)){
            val updateduser = User(args.currentuser.id,title,subtitle,note,priority)
            mUserViewModel.updateuser(updateduser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }
    private fun inputcheck(notetitle:String,notesubtitle:String,note:String):Boolean{
        return !(TextUtils.isEmpty(notetitle) || TextUtils.isEmpty(notesubtitle) || TextUtils.isEmpty(note))
    }
    private  fun priority(){
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

    fun deleteuser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->
            mUserViewModel.deleteuser(args.currentuser)
            Toast.makeText(requireContext(),"Successfull Deleted",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _,_ ->}
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete ${args.currentuser.noteTitle }?")
        builder.create().show()
    }

}