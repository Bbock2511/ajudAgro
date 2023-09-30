package com.example.ajudagro.database.repository

import androidx.lifecycle.LiveData
import com.example.ajudagro.database.dao.AnaliseDao
import com.example.ajudagro.database.models.Analise

class AnaliseRepository(private val analiseDao: AnaliseDao) {

    val lerAnalises: LiveData<List<Analise>> = analiseDao.todasAnalises()

    fun inserirAnalise(analise: Analise){
        analiseDao.inserir(analise)
    }

    fun deletarAnalise(analise: Analise){
        analiseDao.deletarAnalise(analise)
    }

    fun atualizarAnalise(analise: Analise){
        analiseDao.atualizar(analise)
    }
}