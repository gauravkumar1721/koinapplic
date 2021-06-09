package com.example.myapplication2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.databinding.ItemLayoutBinding

class UserAdaptor(private val Users: List<Users>):
    RecyclerView.Adapter <UserAdaptor.UsersViewHolder>(){

    override fun getItemCount(): Int {
        return Users.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
            R.layout.item_layout,parent,false))

    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bindUsers(Users[position])
    }

    class UsersViewHolder(private val singleItemBinding: ItemLayoutBinding):
        RecyclerView.ViewHolder(singleItemBinding.root){
        fun bindUsers(Users:Users) {
            singleItemBinding.textview.setText(Users.id.toString())

        }
    }

}