package com.example.mebelkurnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mebelkurnia.databinding.ActivityRegisterBinding
import com.example.mebelkurnia.network.RegisterRequest
import com.example.mebelkurnia.network.ResponRegister
import com.example.mebelkurnia.network.RetrofitApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.sql.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBtnBack.setOnClickListener {
            val inten = Intent(this, LoginActivity::class.java)
            startActivity(inten)
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            RetrofitApp().userService().register(username, password, 6277).enqueue(object: Callback<ResponRegister>{
                override fun onResponse(
                    call: Call<ResponRegister>, response: Response<ResponRegister>) {
                    if (response.isSuccessful) {
                        onBackPressed()
                    } else {
                        Log.d("RegisterActivity", "Kenapa ${response.body()?.msg}")
                    }
                }

                override fun onFailure(call: Call<ResponRegister>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity ,"error ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.d("RegisterActivity","${t.message}")
                }

            })
        }
    }
}