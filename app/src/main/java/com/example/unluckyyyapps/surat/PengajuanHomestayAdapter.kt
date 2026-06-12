package com.example.unluckyyyapps.surat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unluckyyyapps.data.entity.PengajuanHomestay
import com.example.unluckyyyapps.databinding.ItemPengajuanHomestayBinding
import java.text.NumberFormat
import java.util.Locale

class PengajuanHomestayAdapter(
    private val onDeleteClick: (PengajuanHomestay) -> Unit
) : RecyclerView.Adapter<PengajuanHomestayAdapter.HomestayViewHolder>() {

    private val listHomestay = mutableListOf<PengajuanHomestay>()

    inner class HomestayViewHolder(private val binding: ItemPengajuanHomestayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(homestay: PengajuanHomestay) {
            binding.tvNamaHomestay.text = homestay.namaHomestay
            binding.tvAlamat.text = "Lokasi: ${homestay.alamat}"
            binding.tvPendaftar.text = "Oleh: ${homestay.namaPendaftar}"
            binding.tvTanggal.text = homestay.tanggal

            // Format Harga
            val formattedHarga = try {
                val hargaNum = homestay.hargaPerMalam.toDouble()
                val formatRupiah = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                formatRupiah.format(hargaNum).replace(",00", "")
            } catch (e: Exception) {
                "Rp ${homestay.hargaPerMalam}"
            }
            binding.tvHarga.text = "Harga: $formattedHarga / malam"

            binding.btnDelete.setOnClickListener {
                onDeleteClick(homestay)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomestayViewHolder {
        val binding = ItemPengajuanHomestayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomestayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomestayViewHolder, position: Int) {
        holder.bind(listHomestay[position])
    }

    override fun getItemCount(): Int = listHomestay.size

    fun submitList(list: List<PengajuanHomestay>) {
        listHomestay.clear()
        listHomestay.addAll(list)
        notifyDataSetChanged()
    }
}
