package com.example.unluckyyyapps.List

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pariwisata")
data class Pariwisata(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val deskripsi: String,
    val gambarUrl: String
)