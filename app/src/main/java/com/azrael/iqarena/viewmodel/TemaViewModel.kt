package com.azrael.iqarena.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrael.iqarena.model.TemaPersonalizado
import com.azrael.iqarena.repository.TemaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TemaViewModel : ViewModel() {
    private val repository = TemaRepository()

    private val _tema = MutableStateFlow<TemaPersonalizado?>(null)
    val tema: StateFlow<TemaPersonalizado?> = _tema

    fun createTema(tema: TemaPersonalizado) {
        viewModelScope.launch {
            val response = repository.createTema(tema)
            _tema.value = if (response.isSuccessful) response.body() else null
        }
    }
}
