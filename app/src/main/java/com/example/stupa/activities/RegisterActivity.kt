package com.example.stupa.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stupa.R
import com.example.stupa.databinding.ActivityRegisterBinding
import com.example.stupa.repository.UserRepository
import com.example.stupa.room.User
import com.example.stupa.room.UserDatabase
import com.example.stupa.util.showToast
import com.example.stupa.viewmodel.UserViewModel
import com.example.stupa.viewmodel.UserViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*val dao = UserDatabase.getDatabase(applicationContext).userDao()
        val repository = UserRepository(dao)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(repository)).get(UserViewModel::class.java)*/

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        /*userViewModel.getUsers().observe(this, Observer {
//            binding.quotes = it.toString()

            Log.d("SAVEDDATA", "onCreate: $it")
        })*/
        //code to insert user
        binding.btnSignUp.setOnClickListener {
            validation()
        }

        binding.goToLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        }
    }

    fun validation(){
        if(binding.txtUsername.text?.isEmpty() == true){
            showToast(this@RegisterActivity,"Name should not be empty.")
        }else if(binding.txtPhone.text?.isEmpty() == true){
            showToast(this@RegisterActivity,"Phone Number should not be empty.")
        }else if(binding.txtPhone.text?.length!! <10){
            showToast(this@RegisterActivity,"Phone Number must have 10 digits.")
        }else if(binding.txtEmail.text?.isEmpty() == true){
            showToast(this@RegisterActivity,"Email should not be empty.")
        }else if(binding.txtPassword.text?.isEmpty() == true){
            showToast(this@RegisterActivity,"Password should not be empty.")
        }else{
            signup()
        }
    }

    fun signup(){
        val user = User(0,
            binding.txtUsername.text.toString(),
            binding.txtPhone.text.toString(),
            binding.ccp.defaultCountryName.toString(),
            binding.txtEmail.text.toString(),
            binding.txtPassword.text.toString(),
        )
        userViewModel.insertUser(user)
        showToast(this@RegisterActivity,"Data saved Successfully in DB. please Login to continue")
        clearFields()
    }

    fun clearFields(){
        binding.txtUsername.text?.clear()
        binding.txtPhone.text?.clear()
        binding.txtEmail.text?.clear()
        binding.txtPassword.text?.clear()
    }
}