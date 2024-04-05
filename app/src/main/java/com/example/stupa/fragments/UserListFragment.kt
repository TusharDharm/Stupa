package com.example.stupa.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stupa.R
import com.example.stupa.adapters.UserListRecyclerAdapter
import com.example.stupa.databinding.FragmentUserListBinding
import com.example.stupa.room.User
import java.text.FieldPosition


class UserListFragment(val userlist: ArrayList<User>) : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    lateinit var userAdapter: UserListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(layoutInflater)
        Log.d("USERLIST1", "bind: ${userlist.size}")
        userAdapter = activity?.let { UserListRecyclerAdapter(it, userlist) }!!
        binding.userRecycler.adapter = userAdapter
        userAdapter.notifyDataSetChanged()
        return binding.root
    }
}