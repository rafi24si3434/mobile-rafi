package com.example.unluckyyyapps.surat

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PengajuanTabsAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PengajuanHomestayFragment()
            1 -> PengajuanPariwisataFragment()
            else -> PengajuanHomestayFragment()
        }
    }
}
