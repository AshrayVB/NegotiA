package com.example.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.local.AppDatabase
import com.example.data.local.NegotiationEntity
import com.example.data.repository.NegotiationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "negotiation-database"
    ).build()

    private val repository = NegotiationRepository(db.negotiationDao())

    val negotiations: StateFlow<List<NegotiationEntity>> = repository.allNegotiations
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _currentAnalysis = MutableStateFlow<String>("")
    val currentAnalysis = _currentAnalysis.asStateFlow()

    private val _isAnalyzing = MutableStateFlow(false)
    val isAnalyzing = _isAnalyzing.asStateFlow()

    fun analyzeAndSave(type: String, details: String, title: String) {
        viewModelScope.launch {
            _isAnalyzing.value = true
            val analysis = repository.analyzeNegotiation(type, details)
            _currentAnalysis.value = analysis
            repository.insert(
                NegotiationEntity(
                    title = title,
                    type = type,
                    analysisResult = analysis
                )
            )
            _isAnalyzing.value = false
        }
    }
    
    fun clearAnalysis() {
        _currentAnalysis.value = ""
    }
}
