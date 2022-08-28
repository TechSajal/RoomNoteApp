package com.example.roomnoteapp.fragments.list

import android.app.FragmentManager
import android.graphics.Color
import android.system.Os.remove
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomnoteapp.R
import com.example.roomnoteapp.Model.User
import com.example.roomnoteapp.ViewModel.UserViewModel
import com.example.roomnoteapp.fragments.ListFragmentDirections

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    private var userlist = emptyList<User>()
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
          val currentitem = userlist[position]
          holder.itemView.findViewById<TextView>(R.id.tvTitle).text = currentitem.noteTitle
          holder.itemView.findViewById<TextView>(R.id.tvsubtitle).text = currentitem.notesubtitle
         holder.itemView.findViewById<TextView>(R.id.tvNote).text = currentitem.note
         when(currentitem.preference){
             1 -> {
               holder.itemView.findViewById<LinearLayout>(R.id.linear).setBackgroundColor(Color.parseColor("#4CAF50"))
             }
             2 -> {
                 holder.itemView.findViewById<LinearLayout>(R.id.linear).setBackgroundColor(Color.parseColor("#FFCA28"))
             }
             3 -> {
                 holder.itemView.findViewById<LinearLayout>(R.id.linear).setBackgroundColor(Color.parseColor("#EF5350"))
             }
         }
        holder.itemView.findViewById<LinearLayout>(R.id.layoutNote).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentitem)
            holder.itemView.findNavController().navigate(action)
              }

    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    fun SetData(user: List<User>){
        this.userlist = user
        notifyDataSetChanged()
    }
}