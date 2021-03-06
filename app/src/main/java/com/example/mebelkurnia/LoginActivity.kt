package com.example.mebelkurnia

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mebelkurnia.databinding.ActivityLoginBinding
import com.example.mebelkurnia.network.LoginRequest
import com.example.mebelkurnia.network.ResponLogin
import com.example.mebelkurnia.network.RetrofitApp
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var pref: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = SharedPreferencesManager(this)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener {
            val inten = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(inten)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            val bodyUsername = username.toRequestBody(username.toMediaTypeOrNull())
            val bodyPassword = password.toRequestBody(password.toMediaTypeOrNull())

            RetrofitApp().userService().login(bodyUsername, bodyPassword).enqueue(object: Callback<ResponLogin>{
                override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                    if (response.isSuccessful) {
                        if (response.body()?.data != null){
                            pref.setStringPreference("userId", response.body()?.data?.id ?: "0")
                            pref.setStringPreference("username", username)
                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "halo " + response.body()?.data, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.d("RegisterActivity", "Kenapa ${response.body()?.msg}")
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