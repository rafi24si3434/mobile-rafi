package com.example.unluckyyyapps.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unluckyyyapps.data.dao.PengajuanHomestayDao
import com.example.unluckyyyapps.data.dao.PariwisataDao
import com.example.unluckyyyapps.data.entity.PengajuanHomestay
import com.example.unluckyyyapps.List.Pariwisata

@Database(
    entities = [PengajuanHomestay::class, Pariwisata::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pengajuanHomestayDao(): PengajuanHomestayDao
    abstract fun pariwisataDao(): PariwisataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bina_desa_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
