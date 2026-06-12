package com.example.unluckyyyapps.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengajuan_homestay")
data class PengajuanHomestay(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val namaPendaftar: String,
    val namaHomestay: String,
    val alamat: String,
    val hargaPerMalam: String,
    val tanggal: String
)
