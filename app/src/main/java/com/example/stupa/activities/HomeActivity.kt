package com.example.stupa.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stupa.adapters.UserViewPagerAdapter
import com.example.stupa.databinding.ActivityHomeBinding
import com.example.stupa.repository.UserRepository
import com.example.stupa.room.User
import com.example.stupa.room.UserDatabase
import com.example.stupa.util.SharedPreferenceManager
import com.example.stupa.viewmodel.UserViewModel
import com.example.stupa.viewmodel.UserViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    var tabTitle = arrayOf("Tab1","Tab2","Tab3")
    lateinit var preference: SharedPreferenceManager
    lateinit var binding:ActivityHomeBinding
    lateinit var viewpagerAdapter: UserViewPagerAdapter
    lateinit var userViewModel: UserViewModel
    var userList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
       /* val dao = UserDatabase.getDatabase(applicationContext).userDao()
        val repository = UserRepository(dao)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(repository)).get(UserViewModel::class.java)*/

        preference = SharedPreferenceManager(this@HomeActivity)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.getUsers().observe(this, Observer {
            Log.d("SAVEDDATA", "onCreate: $it")
            userList = it as ArrayList<User>
            Log.d("USERLIST", "bind: ${userList.size}")
            viewpagerAdapter = UserViewPagerAdapter(
                supportFragmentManager,
                lifecycle,
                userList
            )
            binding.userViewPager.adapter = viewpagerAdapter
            TabLayoutMediator(binding.tablayout1, binding.userViewPager) { tab, position ->
                tab.text =tabTitle[position]
            }.attach()
        })

        binding.logout.setOnClickListener {
            preference.clearData()
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            finish()
        }
    }

}