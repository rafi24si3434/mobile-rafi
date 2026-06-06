package com.example.unluckyyyapps.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unluckyyyapps.databinding.FragmentPariwisataBinding

class PariwisataFragment : Fragment() {

    private var _binding: FragmentPariwisataBinding? = null
    private val binding get() = _binding!!

    private val wisataList = listOf(
        Pariwisata("Pantai Kampung Laut", "Pantai indah dengan sunset terbaik.", "https://picsum.photos/500?1"),
        Pariwisata("Hutan Mangrove", "Wisata edukasi alam dan tracking mangrove.", "https://picsum.photos/500?2"),
        Pariwisata("Kuliner Seafood Melayu", "Pusat kuliner seafood khas masyarakat pesisir.", "https://picsum.photos/500?3"),
        Pariwisata("Pelabuhan Wisata", "Spot fotografi dan wisata kapal nelayan.", "https://picsum.photos/500?4")
    )

    // ✅ WAJIB: inflate binding di sini, bukan di constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPariwisataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PariwisataRecyclerAdapter(wisataList) { wisata ->
            Toast.makeText(requireContext(), wisata.nama, Toast.LENGTH_SHORT).show()
        }

        // ✅ GridLayoutManager 2 kolom
        binding.rvPariwisata.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPariwisata.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}