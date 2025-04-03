package com.azrael.iqarena.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrael.iqarena.model.Pregunta
import com.azrael.iqarena.repository.PreguntaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PreguntaViewModel : ViewModel() {
    private val repository = PreguntaRepository()

    private val _pregunta = MutableStateFlow<Pregunta?>(null)
    val pregunta: StateFlow<Pregunta?> = _pregunta

    fun fetchPregunta() {
        viewModelScope.launch {
            val response = repository.obtenerPreguntaRandom()
            _pregunta.value = if (response.isSuccessful) response.body() else null
        }
    }
}
