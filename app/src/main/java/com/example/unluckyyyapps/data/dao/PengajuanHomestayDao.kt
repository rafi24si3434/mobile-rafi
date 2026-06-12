package com.example.unluckyyyapps.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.unluckyyyapps.data.entity.PengajuanHomestay

@Dao
interface PengajuanHomestayDao {

    @Insert
    suspend fun insert(data: PengajuanHomestay)

    @Delete
    suspend fun delete(data: PengajuanHomestay)

    @Query("SELECT * FROM pengajuan_homestay")
    fun getAllData(): LiveData<List<PengajuanHomestay>>
}
