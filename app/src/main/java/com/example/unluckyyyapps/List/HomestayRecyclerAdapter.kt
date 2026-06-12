package com.example.unluckyyyapps.List

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unluckyyyapps.data.entity.PengajuanHomestay
import com.example.unluckyyyapps.databinding.ItemHomestayBinding
import java.text.NumberFormat
import java.util.Locale

class HomestayRecyclerAdapter(
    private val onClick: (PengajuanHomestay) -> Unit
) : RecyclerView.Adapter<HomestayRecyclerAdapter.ViewHolder>() {

    private val homestayList = mutableListOf<PengajuanHomestay>()

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
            tvNamaHomestay.text = homestay.namaHomestay
            tvLokasi.text = homestay.alamat

            // Format Harga
            val formattedHarga = try {
                val hargaNum = homestay.hargaPerMalam.toDouble()
                val formatRupiah = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                formatRupiah.format(hargaNum).replace(",00", "")
            } catch (e: Exception) {
                "Rp ${homestay.hargaPerMalam}"
            }
            tvHarga.text = "$formattedHarga / malam"

            // Load a stable random image based on the id so it looks distinct
            val gambarUrl = "https://picsum.photos/400?random=${homestay.id}"
            Glide.with(root.context)
                .load(gambarUrl)
                .into(ivGambarHomestay)

            root.setOnClickListener {
                onClick(homestay)
            }
        }
    }

    override fun getItemCount(): Int = homestayList.size

    fun submitList(list: List<PengajuanHomestay>) {
        homestayList.clear()
        homestayList.addAll(list)
        notifyDataSetChanged()
    }
}