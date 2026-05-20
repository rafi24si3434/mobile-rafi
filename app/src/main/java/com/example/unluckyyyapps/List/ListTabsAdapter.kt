package com.example.unluckyyyapps.List

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ListTabsAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position) {

            0 -> PariwisataFragment()

            1 -> HomestayFragment()

            else -> PariwisataFragment()
        }
    }
}