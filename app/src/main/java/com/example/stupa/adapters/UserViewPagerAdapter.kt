package com.example.stupa.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.stupa.activities.HomeActivity
import com.example.stupa.fragments.UserListFragment
import com.example.stupa.room.User

class UserViewPagerAdapter(
    var fragmentManager: FragmentManager,
    var lifecycle: Lifecycle,
    var userList: ArrayList<User>
): FragmentStateAdapter(fragmentManager,lifecycle)
{
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return UserListFragment(userList)
    }
}