package com.example.mebelkurnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.mebelkurnia.databinding.ActivityCheckoutBinding
import com.example.mebelkurnia.network.CheckoutBody
import com.example.mebelkurnia.network.ResponRegister
import com.example.mebelkurnia.network.RetrofitApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    val EXTRA_NAME = "extra.name"
    val EXTRA_IMAGES = "extra.images"
    val EXTRA_PRICE = "extra.price"
    val EXTRA_ID = "extra.id"

    private lateinit var name: String
    private lateinit var images: String
    private lateinit var price: String
    private lateinit var id : String
    private lateinit var pref: SharedPreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = SharedPreferencesManager(this)


        binding.btnBackCheckout.setOnClickListener {
            val inten = Intent(this, DashboardActivity::class.java)
            startActivity(inten)
        }


        name = intent.getStringExtra(EXTRA_NAME) ?: ""
        images = intent.getStringExtra(EXTRA_IMAGES) ?: ""
        price = intent.getStringExtra(EXTRA_PRICE) ?: ""
        id = intent.getStringExtra(EXTRA_ID) ?: ""
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

        binding.btnCheckout.setOnClickListener {
            val idUser = pref.getUserId ?: "0"
            checkoutProduct(idUser = idUser.toInt(), idFurniture = id.toInt(), quantity)
        }
    }

    private fun checkoutProduct(idUser: Int,idFurniture: Int, quantity: Int) {
        RetrofitApp().userService().checkoutFurniture(idUser, idFurniture, quantity).enqueue(object : Callback<ResponRegister> {
            override fun onResponse(
                call: Call<ResponRegister>,
                response: Response<ResponRegister>
            ) {
                if (response.isSuccessful){
                    if (response.body()?.msg?.isNotEmpty() == false){
                        val intent = Intent(this@CheckoutActivity, SuksesActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.d("CheckoutActivity",response.body()?.msg ?: "error")
                    }
                }
            }
            override fun onFailure(call: Call<ResponRegister>, t: Throwable) {
                Log.d("CheckoutActivity",t.message ?: "failure")
            }
        })
    }
}