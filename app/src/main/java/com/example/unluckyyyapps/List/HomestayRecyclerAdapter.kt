package com.example.unluckyyyapps.List

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unluckyyyapps.databinding.ItemHomestayBinding

class HomestayRecyclerAdapter(
    private val homestayList: List<Homestay>,
    private val onClick: (Homestay) -> Unit
) : RecyclerView.Adapter<HomestayRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemHomestayBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemHomestayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val homestay = homestayList[position]

        with(holder.binding) {

            tvNamaHomestay.text = homestay.nama
            tvLokasi.text = homestay.lokasi
            tvHarga.text = homestay.harga

            Glide.with(root.context)
                .load(homestay.gambarUrl)
                .into(ivGambarHomestay)

            root.setOnClickListener {
                onClick(homestay)
            }
        }
    }

    override fun getItemCount(): Int {
        return homestayList.size
    }
}