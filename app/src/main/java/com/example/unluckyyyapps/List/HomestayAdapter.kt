package com.example.unluckyyyapps.List

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.unluckyyyapps.R // Pastikan import R sesuai package kamu

class HomestayAdapter(context: Context, private val homestayList: List<Homestay>) :
    ArrayAdapter<Homestay>(context, R.layout.item_homestay, homestayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate layout item_homestay.xml
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_homestay, parent, false)

        // Ambil data posisi saat ini
        val homestay = homestayList[position]

        // Hubungkan dengan ID di item_homestay.xml
        val tvNama = view.findViewById<TextView>(R.id.tvNamaHomestay)
        val tvLokasi = view.findViewById<TextView>(R.id.tvLokasi)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val ivGambar = view.findViewById<ImageView>(R.id.ivGambarHomestay)

        // Set data teks
        tvNama.text = homestay.nama
        tvLokasi.text = homestay.lokasi
        tvHarga.text = homestay.harga

        // Set gambar menggunakan Glide
        Glide.with(context)
            .load(homestay.gambarUrl)
            .placeholder(android.R.drawable.ic_menu_gallery) // Gambar sementara jika internet lambat
            .into(ivGambar)

        return view
    }
}