package com.example.unluckyyyapps.List

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.databinding.FragmentPariwisataBinding

class PariwisataFragment : Fragment(R.layout.fragment_pariwisata) {

    private var _binding: FragmentPariwisataBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PariwisataRecyclerAdapter

    private val wisataList = listOf(

        Pariwisata(
            "Pantai Kampung Laut",
            "Pantai indah dengan sunset terbaik.",
            "https://picsum.photos/500?1"
        ),

        Pariwisata(
            "Hutan Mangrove",
            "Wisata edukasi alam dan tracking mangrove.",
            "https://picsum.photos/500?2"
        ),

        Pariwisata(
            "Kuliner Seafood Melayu",
            "Pusat kuliner seafood khas masyarakat pesisir.",
            "https://picsum.photos/500?3"
        ),

        Pariwisata(
            "Pelabuhan Wisata",
            "Spot fotografi dan wisata kapal nelayan.",
            "https://picsum.photos/500?4"
        )
    )

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPariwisataBinding.bind(view)

        adapter = PariwisataRecyclerAdapter(
            wisataList
        ) { wisata ->

            Toast.makeText(
                requireContext(),
                wisata.nama,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvPariwisata.apply {

            layoutManager = GridLayoutManager(
                requireContext(),
                2
            )

            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}