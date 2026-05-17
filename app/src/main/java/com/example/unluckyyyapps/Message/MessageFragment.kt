package com.example.unluckyyyapps.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    // Properti ini valid di antara onCreateView dan onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout fragment_message.xml (yang sekarang isinya Profil Bina Desa)
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tidak perlu memanggil ID apa pun di sini karena desain XML kamu sudah fix
        // Tampilan profil Bina Desa akan langsung muncul!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Wajib mengosongkan binding agar tidak memory leak
        _binding = null
    }
}