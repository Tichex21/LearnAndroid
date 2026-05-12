package com.sachinshah.practical.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sachinshah.practical.R
import com.sachinshah.practical.databinding.RowUsersBinding
import com.sachinshah.practical.room.entity.UserModel


class DbUserAdapter(
    val update: (UserModel, Int) -> Unit,
    val delete: (UserModel, Int) -> Unit,
) : ListAdapter<UserModel, DbUserAdapter.UserItemHolder>(DiffUtil()) {

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): UserItemHolder {
        val inflater = LayoutInflater.from(p0.context)
        val binding = DataBindingUtil.inflate<RowUsersBinding>(
            inflater, R.layout.row_users, p0, false
        )
        return UserItemHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserItemHolder,
        position: Int
    ) {
        val user = getItem(position)
        holder.bind(user)
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(
            p0: UserModel, p1: UserModel
        ): Boolean {
            return p0.uid == p1.uid
        }

        override fun areContentsTheSame(
            p0: UserModel, p1: UserModel
        ): Boolean {
            return p0 == p1
        }

    }

    inner class UserItemHolder(
        private val binding: RowUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserModel) {
            val pos=adapterPosition
            binding.userModel = user

            binding.btnDelete.setOnClickListener {
                if (pos != RecyclerView.NO_POSITION)
                delete.invoke(user, pos)
            }

            binding.btnEdit.setOnClickListener {
                if (pos != RecyclerView.NO_POSITION)
                update.invoke(user, pos)
            }
        }
    }
}

