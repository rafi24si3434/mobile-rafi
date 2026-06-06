package com.example.unluckyyyapps.List

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.databinding.FragmentHomestayBinding

class HomestayFragment : Fragment(R.layout.fragment_homestay) {

    private var _binding: FragmentHomestayBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomestayRecyclerAdapter

    private val homestayList = listOf(

        Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        ),

        Homestay(
            "Penginapan Melayu Indah",
            "Desa Wisata Tanjung",
            "Rp 300.000 / malam",
            "https://picsum.photos/400?2"
        ),
        Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        ),Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        ),Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        ),Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        ),Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        ),Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        ),Homestay(
            "Homestay Kampung Laut",
            "Kuala Tungkal",
            "Rp 250.000 / malam",
            "https://picsum.photos/400?1"
        )

    )

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomestayBinding.bind(view)

        adapter = HomestayRecyclerAdapter(homestayList) { homestay ->

            Toast.makeText(
                requireContext(),
                homestay.nama,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvHomestay.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.rvHomestay.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}