package com.example.unluckyyyapps.surat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unluckyyyapps.List.FormPariwisataActivity
import com.example.unluckyyyapps.List.Pariwisata
import com.example.unluckyyyapps.data.database.AppDatabase
import com.example.unluckyyyapps.databinding.FragmentPengajuanPariwisataBinding
import kotlinx.coroutines.launch

class PengajuanPariwisataFragment : Fragment() {

    private var _binding: FragmentPengajuanPariwisataBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase
    private lateinit var adapter: PengajuanPariwisataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPengajuanPariwisataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getDatabase(requireContext())

        adapter = PengajuanPariwisataAdapter { wisata ->
            showDeleteConfirmationDialog(wisata)
        }

        binding.rvPariwisata.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPariwisata.adapter = adapter

        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), FormPariwisataActivity::class.java)
            startActivity(intent)
        }

        // Observe LiveData from Room Database
        database.pariwisataDao().getAllData().observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            if (list.isEmpty()) {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.rvPariwisata.visibility = View.GONE

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
                binding.layoutEmpty.visibility = View.GONE
                binding.rvPariwisata.visibility = View.VISIBLE
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
