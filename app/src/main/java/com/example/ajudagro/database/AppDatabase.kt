package com.example.ajudagro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.ajudagro.database.dao.AnaliseDao
import com.example.ajudagro.database.models.Analise

@Database(entities = [Analise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun analiseDao(): AnaliseDao

    companion object {

        private const val DATABASE_NAME = "Analises"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this){
                val instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}