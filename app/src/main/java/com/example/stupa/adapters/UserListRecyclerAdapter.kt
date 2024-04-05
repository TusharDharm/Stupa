package com.example.stupa.adapters



import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stupa.databinding.UserItemRowBinding
import com.example.stupa.room.User

class UserListRecyclerAdapter(
    val context: Context,
    val userList: ArrayList<User>,
) : RecyclerView.Adapter<UserListRecyclerAdapter.UserListViewHolder>() {

    inner class UserListViewHolder(val binding: UserItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.name.text = user.name
            binding.mobile.text = user.mobile
            binding.country.text = user.country
            binding.email.text = user.email
            binding.password.text = user.password
            Log.d("USERNAME", "bind: ${user.name}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = UserItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(userList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}