package com.example.unluckyyyapps.Message // Sesuaikan dengan package Anda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.databinding.FragmentMessageBinding



class MessageFragment : Fragment() {

    // Menggunakan ViewBinding untuk menghindari findViewById
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Variabel untuk menampung data yang dikirim ke fragment
    private var title: String? = null
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            message = it.getString(ARG_MESSAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set data ke UI
        binding.tvMessageTitle.text = title ?: "Pemberitahuan"
        binding.tvMessageBody.text = message ?: "Tidak ada pesan."

        // Set aksi pada tombol
        binding.btnAction.setOnClickListener {
            // Logika ketika tombol diklik (misal: kembali ke halaman sebelumnya)
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Mencegah memory leak
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"

        /**
         * Gunakan metode factory ini untuk membuat instance baru
         * dari fragment yang sudah dibekali parameter.
         */
        @JvmStatic
        fun newInstance(title: String, message: String) =
            MessageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_MESSAGE, message)
                }
            }
    }
}