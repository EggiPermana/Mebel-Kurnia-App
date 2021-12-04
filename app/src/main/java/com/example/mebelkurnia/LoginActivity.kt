package com.example.mebelkurnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mebelkurnia.databinding.ActivityLoginBinding
import com.example.mebelkurnia.network.LoginRequest
import com.example.mebelkurnia.network.ResponLogin
import com.example.mebelkurnia.network.RetrofitApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener {
            val inten = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(inten)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            val request = LoginRequest(username = username,password)

            RetrofitApp().userService().login(request).enqueue(object: Callback<ResponLogin>{
                override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                    if (response.isSuccessful) {
                        if (response.body()?.data == null){
                            Toast.makeText(this@LoginActivity,  response.body()?.msg, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@LoginActivity, "halo " + response.body()?.data, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, response.body()?.msg, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponLogin>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "error ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.d("LoginActivity","error ${t.message}")
                }
            })


        }
    }
}