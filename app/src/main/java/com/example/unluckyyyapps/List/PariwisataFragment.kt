package com.example.unluckyyyapps.List

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unluckyyyapps.data.database.AppDatabase
import com.example.unluckyyyapps.databinding.FragmentPariwisataBinding
import kotlinx.coroutines.launch

class PariwisataFragment : Fragment() {

    private var _binding: FragmentPariwisataBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase
    private lateinit var adapter: PariwisataRecyclerAdapter

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

        database = AppDatabase.getDatabase(requireContext())

        adapter = PariwisataRecyclerAdapter(
            onClick = { wisata ->
                Toast.makeText(requireContext(), wisata.nama, Toast.LENGTH_SHORT).show()
            },
            onLongClick = { wisata ->
                showDeleteConfirmationDialog(wisata)
            }
        )

        binding.rvPariwisata.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPariwisata.adapter = adapter

        // Setup Floating Action Button to launch FormPariwisataActivity
        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), FormPariwisataActivity::class.java)
            startActivity(intent)
        }

        // Observe Data from Room Database
        database.pariwisataDao().getAllData().observe(viewLifecycleOwner) { list ->
            if (list.isEmpty()) {
                // Preload default data asynchronously if database is empty
                lifecycleScope.launch {
                    val defaultList = listOf(
                        Pariwisata(nama = "Pantai Kampung Laut", deskripsi = "Pantai indah dengan sunset terbaik.", gambarUrl = "https://picsum.photos/500?1"),
                        Pariwisata(nama = "Hutan Mangrove", deskripsi = "Wisata edukasi alam dan tracking mangrove.", gambarUrl = "https://picsum.photos/500?2"),
                        Pariwisata(nama = "Kuliner Seafood Melayu", deskripsi = "Pusat kuliner seafood khas masyarakat pesisir.", gambarUrl = "https://picsum.photos/500?3"),
                        Pariwisata(nama = "Pelabuhan Wisata", deskripsi = "Spot fotografi dan wisata kapal nelayan.", gambarUrl = "https://picsum.photos/500?4")
                    )
                    for (item in defaultList) {
                        database.pariwisataDao().insert(item)
                    }
                }
            } else {
                adapter.submitList(list)
            }
        }
    }

    private fun showDeleteConfirmationDialog(wisata: Pariwisata) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Destinasi")
            .setMessage("Apakah Anda yakin ingin menghapus destinasi ${wisata.nama}?")
            .setPositiveButton("Hapus") { dialog, _ ->
                dialog.dismiss()
                lifecycleScope.launch {
                    try {
                        database.pariwisataDao().delete(wisata)
                        Toast.makeText(requireContext(), "Destinasi wisata berhasil dihapus", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Gagal menghapus: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}