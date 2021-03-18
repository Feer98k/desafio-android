package com.picpay.desafio.android.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presenter.adapter.viewholder.UserListItemViewHolder

class UserListAdapter(
    val usersList: MutableList<User> = mutableListOf()
) : RecyclerView.Adapter<UserListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size

    fun refresh(users: List<User>) {
        notifyItemRangeRemoved(0, this.usersList.size)
        this.usersList.clear()
        this.usersList.addAll(users)
        notifyItemRangeInserted(0, this.usersList.size)
    }
}