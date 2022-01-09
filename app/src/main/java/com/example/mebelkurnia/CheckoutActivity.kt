package com.example.mebelkurnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.mebelkurnia.databinding.ActivityCheckoutBinding
import com.example.mebelkurnia.databinding.ActivityLoginBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    val EXTRA_NAME = "extra.name"
    val EXTRA_IMAGES = "extra.images"
    val EXTRA_PRICE = "extra.price"

    private lateinit var name: String
    private lateinit var images: String
    private lateinit var price: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackCheckout.setOnClickListener {
            val inten = Intent(this, DashboardActivity::class.java)
            startActivity(inten)
        }


        name = intent.getStringExtra(EXTRA_NAME) ?: ""
        images = intent.getStringExtra(EXTRA_IMAGES) ?: ""
        price = intent.getStringExtra(EXTRA_PRICE) ?: ""
        binding.tvTotal.text = "Rp $price"

        Glide.with(this)
            .load(ConstValue.IMAGE_BASE_URL + images)
            .into(binding.imvProduct)

        binding.tvName.text = name

        val realPrice = price.replace(".", "").toInt()
        Log.d("CheckoutActivity", "real Price is $realPrice")

        var quantity = 1
        binding.tvPrice.text = "Rp $realPrice"

        binding.btnPlus.setOnClickListener {
            quantity++
            binding.tvQuantity.text = quantity.toString()
            binding.tvTotal.text = "Rp ${(quantity * realPrice)}"
        }

        binding.btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvQuantity.text = quantity.toString()
                binding.tvTotal.text = "Rp ${(quantity * realPrice)}"
            }
            if (quantity == 1) {
                binding.tvTotal.text = "Rp $realPrice"
            }
        }
    }
}