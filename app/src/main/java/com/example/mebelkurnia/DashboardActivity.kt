package com.example.mebelkurnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mebelkurnia.adapter.DataFurniturAdapter
import com.example.mebelkurnia.databinding.ActivityDashboardBinding
import com.example.mebelkurnia.network.GetFurniturModel
import com.example.mebelkurnia.network.GetFurnitureResponse
import com.example.mebelkurnia.network.RetrofitApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: DataFurniturAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getApiResponse()

        binding.imgProfile.setOnClickListener {
            val inten = Intent(this, ProfilActivity::class.java)
            startActivity(inten)
        }
    }

    //memulai ambil data api
    private fun getApiResponse() {
        val dataService = RetrofitApp().userService().getFurniture()
        dataService.enqueue(object : Callback<GetFurnitureResponse> {
            override fun onResponse(
                call: Call<GetFurnitureResponse>,
                response: Response<GetFurnitureResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        initiateDataRecyclerView(it)
                    }
                } else {
                    Log.d("Dashboard", "Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetFurnitureResponse>, t: Throwable) {
                Log.d("Dashboard", "Error ${t.message}")
            }
        })
    }

    fun initiateDataRecyclerView(listDataFurniture: List<GetFurniturModel>) {
        adapter = DataFurniturAdapter(listDataFurniture, this)
        binding.rvCategory.adapter = adapter
        binding.rvCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}