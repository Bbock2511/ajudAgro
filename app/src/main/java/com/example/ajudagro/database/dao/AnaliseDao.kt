package com.example.ajudagro.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ajudagro.database.models.Analise

@Dao
interface AnaliseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inserir(analise: Analise)

    @Delete
    fun deletarAnalise(analise: Analise)

    @Query("SELECT * FROM analises")
    fun todasAnalises(): LiveData<List<Analise>>

    @Update
    fun atualizar(analise: Analise)

    @Query("SELECT COUNT(Nome) FROM analises")
    fun contagemTotalAnalises(): Long
}