package com.example.unluckyyyapps.Home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.unluckyyyapps.BinaDesa
import com.example.unluckyyyapps.BinaDesaWebView
import com.example.unluckyyyapps.R
import com.example.unluckyyyapps.SplashScreenActivity

// Perbaikan Import menyesuaikan struktur folder di project Anda
import com.example.unluckyyyapps.pertemuan_2.SecondActivity
import com.example.unluckyyyapps.pertemuan_3.ThirdActivity
import com.example.unluckyyyapps.pertemuan_4.FourthActivity
import com.example.unluckyyyapps.pertemuan_5.FifthActivity
import com.example.unluckyyyapps.pertemuan_7.SeventhActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // =========================
        // PERTEMUAN
        // =========================

        view.findViewById<View>(R.id.btnPertemuan2).setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        view.findViewById<View>(R.id.btnPertemuan3).setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        view.findViewById<View>(R.id.btnPertemuan4).setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        view.findViewById<View>(R.id.btnPertemuan5).setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        view.findViewById<View>(R.id.btnPertemuan7).setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        // =========================
        // TUGAS P6 - BINA DESA
        // =========================

        view.findViewById<View>(R.id.btnTugasP6).setOnClickListener {
            startActivity(Intent(requireContext(), BinaDesa::class.java))
        }

        view.findViewById<View>(R.id.btnWebTugasP6).setOnClickListener {
            startActivity(Intent(requireContext(), BinaDesaWebView::class.java))
        }

        // =========================
        // LOGOUT
        // =========================

        view.findViewById<View>(R.id.btnLogout).setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Yakin ingin keluar akun?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()

                    val sharedPref = requireContext().getSharedPreferences(
                        "user_pref",
                        android.content.Context.MODE_PRIVATE
                    )
                    sharedPref.edit().clear().apply()

                    val intent = Intent(requireContext(), SplashScreenActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}