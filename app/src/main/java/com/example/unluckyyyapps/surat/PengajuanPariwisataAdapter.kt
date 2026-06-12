package com.example.unluckyyyapps.surat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unluckyyyapps.List.Pariwisata
import com.example.unluckyyyapps.databinding.ItemPengajuanPariwisataBinding

class PengajuanPariwisataAdapter(
    private val onDeleteClick: (Pariwisata) -> Unit
) : RecyclerView.Adapter<PengajuanPariwisataAdapter.PariwisataViewHolder>() {

    private val listPariwisata = mutableListOf<Pariwisata>()

    inner class PariwisataViewHolder(private val binding: ItemPengajuanPariwisataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wisata: Pariwisata) {
            binding.tvNamaWisata.text = wisata.nama
            binding.tvDeskripsi.text = wisata.deskripsi
            binding.tvGambarUrl.text = wisata.gambarUrl

            binding.btnDelete.setOnClickListener {
                onDeleteClick(wisata)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PariwisataViewHolder {
        val binding = ItemPengajuanPariwisataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PariwisataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PariwisataViewHolder, position: Int) {
        holder.bind(listPariwisata[position])
    }

    override fun getItemCount(): Int = listPariwisata.size

    fun submitList(list: List<Pariwisata>) {
        listPariwisata.clear()
        listPariwisata.addAll(list)
        notifyDataSetChanged()
    }
}
