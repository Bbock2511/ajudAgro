package com.example.ajudagro.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ajudagro.database.AppDatabase
import com.example.ajudagro.database.models.Analise
import com.example.ajudagro.database.repository.AnaliseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnaliseViewModel(application: Application) : AndroidViewModel(application) {

    val lerAnalises: LiveData<List<Analise>>
    private val repository: AnaliseRepository

    init {
        val analiseDao = AppDatabase.getDatabase(application).analiseDao()
        repository = AnaliseRepository(analiseDao)
        lerAnalises = repository.lerAnalises
    }

    fun inserirAnalise(analise: Analise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.inserirAnalise(analise)
        }
    }

    fun deletarAnalise(analise: Analise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletarAnalise(analise)
        }
    }

    fun atualizarAnalise(analise: Analise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.atualizarAnalise(analise)
        }
    }
}