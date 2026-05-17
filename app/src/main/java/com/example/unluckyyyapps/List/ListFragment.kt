package com.example.unluckyyyapps.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.R // Sesuaikan package R

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hubungkan dengan fragment_list.xml
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.listViewHomestay)

        // 1. Siapkan Dummy Data Homestay
        val dataHomestay = listOf(
            Homestay(
                "Omah Kayu Homestay",
                "📍 Desa Wisata Penglipuran",
                "Rp 250.000 / malam",
                "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=500&q=80"
            ),

            Homestay(
                "Pondok Alam Asri",
                "📍 Nglanggeran, Yogyakarta",
                "Rp 300.000 / malam",
                "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?w=500&q=80"
            ),

            Homestay(
                "Villa Hijau Daun",
                "📍 Lembang, Bandung",
                "Rp 450.000 / malam",
                "https://images.unsplash.com/photo-1494526585095-c41746248156?w=500&q=80"
            ),

            Homestay(
                "Saung Desa Sunrise",
                "📍 Dieng, Jawa Tengah",
                "Rp 200.000 / malam",
                "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688?w=500&q=80"
            ),

            Homestay(
                "Rumah Kayu Indah",
                "📍 Batu, Malang",
                "Rp 350.000 / malam",
                "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=500&q=80"
            ),

            Homestay(
                "Villa Puncak Damai",
                "📍 Puncak, Bogor",
                "Rp 500.000 / malam",
                "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?w=500&q=80"
            ),

            Homestay(
                "Cottage Danau Biru",
                "📍 Danau Toba, Sumatera",
                "Rp 275.000 / malam",
                "https://images.unsplash.com/photo-1449844908441-8829872d2607?w=500&q=80"
            ),

            Homestay(
                "Penginapan Bukit View",
                "📍 Bukittinggi, Padang",
                "Rp 320.000 / malam",
                "https://images.unsplash.com/photo-1464146072230-91cabc968266?w=500&q=80"
            ),

            Homestay(
                "Villa Pantai Indah",
                "📍 Anyer, Banten",
                "Rp 600.000 / malam",
                "https://images.unsplash.com/photo-1499793983690-e29da59ef1c2?w=500&q=80"
            ),

            Homestay(
                "Rumah Tradisional Jawa",
                "📍 Solo, Jawa Tengah",
                "Rp 230.000 / malam",
                "https://images.unsplash.com/photo-1484154218962-a197022b5858?w=500&q=80"
            )
        )

        // 2. Buat Adapter
        val adapter = HomestayAdapter(requireContext(), dataHomestay)

        // 3. Pasang Adapter ke ListView
        listView.adapter = adapter

        // 4. Tambahkan event klik pada item (OnItemClick)
        listView.setOnItemClickListener { _, _, position, _ ->
            val homestayTerpilih = dataHomestay[position]
            Toast.makeText(
                requireContext(),
                "Melihat detail: ${homestayTerpilih.nama}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}