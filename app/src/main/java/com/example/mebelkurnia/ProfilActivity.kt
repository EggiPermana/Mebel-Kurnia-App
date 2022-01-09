package com.example.mebelkurnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mebelkurnia.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        binding.btnBackFrofil.setOnClickListener {
            val inten = Intent(this, DashboardActivity::class.java)
            startActivity(inten)
        }
    }
}