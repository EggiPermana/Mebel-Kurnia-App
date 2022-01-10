package com.example.mebelkurnia.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mebelkurnia.CheckoutActivity
import com.example.mebelkurnia.ConstValue.IMAGE_BASE_URL
import com.example.mebelkurnia.databinding.ItemDataFurniturBinding
import com.example.mebelkurnia.network.GetFurniturModel
import com.example.mebelkurnia.network.GetFurnitureResponse

class DataFurniturAdapter(
    private val listData: List<GetFurniturModel>,
    private val mContext: Context
) : RecyclerView.Adapter<DataFurniturAdapter.DataHolder>() {

    inner class DataHolder(val binding: ItemDataFurniturBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //fun pemersatu antara dari data ke id item
        fun bind(dataResponse: GetFurniturModel) {
            Glide
                .with(itemView)
                .load(IMAGE_BASE_URL + dataResponse.images)
                .into(binding.imgFurniture)
            binding.tvNamaBarang.text = dataResponse.name
            binding.tvHarga.text = dataResponse.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val binding =
            ItemDataFurniturBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataHolder(binding)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, CheckoutActivity::class.java)
            intent.apply {
                putExtra(CheckoutActivity().EXTRA_NAME, data.name)
                putExtra(CheckoutActivity().EXTRA_PRICE, data.price)
                putExtra(CheckoutActivity().EXTRA_IMAGES, data.images)
                putExtra(CheckoutActivity().EXTRA_ID, data.id)
            }
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}