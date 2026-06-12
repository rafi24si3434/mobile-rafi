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
import com.example.unluckyyyapps.data.database.AppDatabase
import com.example.unluckyyyapps.data.entity.PengajuanHomestay
import com.example.unluckyyyapps.databinding.FragmentPengajuanHomestayBinding
import kotlinx.coroutines.launch

class PengajuanHomestayFragment : Fragment() {

    private var _binding: FragmentPengajuanHomestayBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase
    private lateinit var homestayAdapter: PengajuanHomestayAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPengajuanHomestayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getDatabase(requireContext())

        // Setup RecyclerView & Adapter
        homestayAdapter = PengajuanHomestayAdapter { homestay ->
            showDeleteConfirmationDialog(homestay)
        }

        binding.rvHomestay.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHomestay.adapter = homestayAdapter

        // Setup Floating Action Button to launch FormHomestayActivity
        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), FormHomestayActivity::class.java)
            startActivity(intent)
        }

        // Observe Data
        database.pengajuanHomestayDao().getAllData().observe(viewLifecycleOwner) { list ->
            homestayAdapter.submitList(list)
            if (list.isEmpty()) {
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.rvHomestay.visibility = View.GONE
            } else {
                binding.layoutEmpty.visibility = View.GONE
                binding.rvHomestay.visibility = View.VISIBLE
            }
        }
    }

    private fun showDeleteConfirmationDialog(homestay: PengajuanHomestay) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Pendaftaran")
            .setMessage("Apakah Anda yakin ingin menghapus pengajuan homestay ${homestay.namaHomestay}?")
            .setPositiveButton("Hapus") { dialog, _ ->
                dialog.dismiss()
                lifecycleScope.launch {
                    try {
                        database.pengajuanHomestayDao().delete(homestay)
                        Toast.makeText(requireContext(), "Pengajuan homestay berhasil dihapus", Toast.LENGTH_SHORT).show()
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
