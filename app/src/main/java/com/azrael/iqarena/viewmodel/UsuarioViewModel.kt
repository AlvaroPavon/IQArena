package com.azrael.iqarena.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrael.iqarena.model.Usuario
import com.azrael.iqarena.repository.UsuarioRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class UsuarioViewModel : ViewModel() {
    private val repository = UsuarioRepository()

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    fun registrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            val response = repository.registrarUsuario(usuario)
            _usuario.value = if (response.isSuccessful) response.body() else null
        }
    }

    // Función para registrar usuarios de Google
    @RequiresApi(Build.VERSION_CODES.O)
    fun registrarUsuarioGoogle(nombre: String, email: String) {
        // Como no tenemos contraseña, asignamos un valor vacío o un valor predeterminado
        val usuarioGoogle = Usuario(
            nombre = nombre,
            email = email,
            contrasena = "",  // Opcional: podrías asignar "google" o similar
            puntosXp = 0,
            // Asigna la fecha actual en formato ISO si lo deseas; aquí lo dejamos nulo para que lo genere el back-end
            fechaRegistro = LocalDate.now().toString(),
            avatar = null // Puedes asignar un avatar por defecto
        )
        viewModelScope.launch {
            val response = repository.registrarUsuario(usuarioGoogle)
            _usuario.value = if (response.isSuccessful) response.body() else null
        }
    }
}
