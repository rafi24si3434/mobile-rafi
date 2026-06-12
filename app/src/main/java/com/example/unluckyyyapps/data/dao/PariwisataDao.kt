package com.example.unluckyyyapps.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unluckyyyapps.List.Pariwisata

@Dao
interface PariwisataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pariwisata: Pariwisata)

    @Delete
    suspend fun delete(pariwisata: Pariwisata)

    @Query("SELECT * FROM pariwisata ORDER BY id DESC")
    fun getAllData(): LiveData<List<Pariwisata>>
}
