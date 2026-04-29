package com.example.unluckyyyapps.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.Home.pertemuan_2.SecondActivity
import com.example.unluckyyyapps.Home.pertemuan_3.ThirdActivity
import com.example.unluckyyyapps.Home.pertemuan_4.FourthActivity
import com.example.unluckyyyapps.Home.pertemuan_5.FifthActivity
import com.example.unluckyyyapps.Home.pertemuan_7.SeventhActivity
import com.example.unluckyyyapps.R
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.btnPertemuan2).setOnClickListener {
            startActivity(
                Intent(requireContext(), SecondActivity::class.java)
            )
        }

        view.findViewById<View>(R.id.btnPertemuan3).setOnClickListener {
            startActivity(
                Intent(requireContext(), ThirdActivity::class.java)
            )
        }

        view.findViewById<View>(R.id.btnPertemuan4).setOnClickListener {
            startActivity(
                Intent(requireContext(), FourthActivity::class.java)
            )
        }

        view.findViewById<View>(R.id.btnPertemuan5).setOnClickListener {
            startActivity(
                Intent(requireContext(), FifthActivity::class.java)
            )
        }

        view.findViewById<View>(R.id.btnPertemuan7).setOnClickListener {
            startActivity(
                Intent(requireContext(), SeventhActivity::class.java)
            )
        }
    }
}