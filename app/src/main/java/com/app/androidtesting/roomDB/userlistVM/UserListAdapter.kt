package com.app.androidtesting.roomDB.userlistVM

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.androidtesting.R
import com.app.androidtesting.databinding.UsersListRowItemsBinding


class UserListAdapter(private val itemList: List<UserListData>) : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: UsersListRowItemsBinding = DataBindingUtil.inflate(inflater, R.layout.users_list_row_items, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = itemList.size

    inner class MyViewHolder(private val binding: UsersListRowItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemViewModel: UserListData) {
            binding.userList = itemViewModel
            binding.executePendingBindings()
        }
    }
}

