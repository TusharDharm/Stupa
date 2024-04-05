package com.example.stupa.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.stupa.databinding.ActivityLoginBinding
import com.example.stupa.util.SharedPreferenceManager
import com.example.stupa.util.showToast
import com.example.stupa.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.IO)
    lateinit var binding: ActivityLoginBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }

        /*val dao = UserDatabase.getDatabase(applicationContext).userDao()
        val repository = UserRepository(dao)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(repository)).get(UserViewModel::class.java)*/

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnSignIn.setOnClickListener {
            validation()
        }
    }

    fun validation(){
        if(binding.txtEmail.text?.isEmpty() == true){
            showToast(this@LoginActivity,"Email should not be empty.")
        }else if(binding.txtPassword.text?.isEmpty() == true){
            showToast(this@LoginActivity,"Password should not be empty.")
        }else{
            signIn()
        }
    }

    fun signIn(){
        scope.launch {
            var user = userViewModel.getUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
            if(user.isNotEmpty()){
                withContext(Dispatchers.Main) {
                    clearFields()
                }
                SharedPreferenceManager(this@LoginActivity).email = user[0].email
                startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                finish()
            }else{
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Email or password is incorrect.", Toast.LENGTH_SHORT).show()
                }
            }
//                Log.d("FETCHDATA", "onCreate: ${user.get(0).mobile}")
        }
    }
    fun clearFields(){
        binding.txtEmail.text?.clear()
        binding.txtPassword.text?.clear()
    }

}