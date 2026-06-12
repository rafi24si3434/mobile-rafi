package com.example.unluckyyyapps.surat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.databinding.FragmentPengajuanBinding
import com.google.android.material.tabs.TabLayoutMediator

class PengajuanFragment : Fragment(R.layout.fragment_pengajuan) {

    private var _binding: FragmentPengajuanBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPengajuanBinding.bind(view)

        val adapter = PengajuanTabsAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Homestay"
                    tab.setIcon(android.R.drawable.ic_menu_myplaces)
                }
                1 -> {
                    tab.text = "Wisata"
                    tab.setIcon(android.R.drawable.ic_menu_compass)
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
