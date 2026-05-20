package com.example.unluckyyyapps.List

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.databinding.FragmentListBinding
import com.google.android.material.tabs.TabLayoutMediator

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentListBinding.bind(view)

        val adapter = ListTabsAdapter(this)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->

            when (position) {

                0 -> {
                    tab.text = "Pariwisata"
                    tab.setIcon(android.R.drawable.ic_menu_compass)
                }

                1 -> {
                    tab.text = "Homestay"
                    tab.setIcon(android.R.drawable.ic_menu_myplaces)

                    val badge = tab.orCreateBadge
                    badge.isVisible = true
                    badge.number = 2
                }
            }

        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}