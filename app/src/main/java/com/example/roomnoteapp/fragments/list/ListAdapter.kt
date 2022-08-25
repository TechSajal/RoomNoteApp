package com.example.roomnoteapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomnoteapp.R
import com.example.roomnoteapp.data.User

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    private var userlist = emptyList<User>()

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_add,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentitem = userlist[position]
//        holder.itemView.
    }

    override fun getItemCount(): Int {
        return userlist.size
    }
}