package com.example.unluckyyyapps.List

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.data.database.AppDatabase
import com.example.unluckyyyapps.data.entity.PengajuanHomestay
import com.example.unluckyyyapps.databinding.FragmentHomestayBinding
import kotlinx.coroutines.launch

class HomestayFragment : Fragment(R.layout.fragment_homestay) {

    private var _binding: FragmentHomestayBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase
    private lateinit var adapter: HomestayRecyclerAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomestayBinding.bind(view)

        database = AppDatabase.getDatabase(requireContext())

        adapter = HomestayRecyclerAdapter { homestay ->
            Toast.makeText(
                requireContext(),
                homestay.namaHomestay,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvHomestay.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHomestay.adapter = adapter

        // Observe Data from Room Database
        database.pengajuanHomestayDao().getAllData().observe(viewLifecycleOwner) { list ->
            if (list.isEmpty()) {
                // Preload default homestay data asynchronously if database is empty
                lifecycleScope.launch {
                    val defaultList = listOf(
                        PengajuanHomestay(
                            namaPendaftar = "Pengelola",
                            namaHomestay = "Homestay Kampung Laut",
                            alamat = "Kuala Tungkal",
                            hargaPerMalam = "250000",
                            tanggal = "10 Jun 2026"
                        ),
                        PengajuanHomestay(
                            namaPendaftar = "Pengelola",
                            namaHomestay = "Penginapan Melayu Indah",
                            alamat = "Desa Wisata Tanjung",
                            hargaPerMalam = "300000",
                            tanggal = "10 Jun 2026"
                        ),
                        PengajuanHomestay(
                            namaPendaftar = "Pengelola",
                            namaHomestay = "Homestay Asri Desa",
                            alamat = "Kuala Tungkal",
                            hargaPerMalam = "275000",
                            tanggal = "10 Jun 2026"
                        )
                    )
                    for (item in defaultList) {
                        database.pengajuanHomestayDao().insert(item)
                    }
                }
            } else {
                adapter.submitList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}