package com.azrael.iqarena.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrael.iqarena.model.LoginRequest
import com.azrael.iqarena.model.Usuario
import com.azrael.iqarena.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {
    private val repository = UsuarioRepository()

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    fun loginUsuario(email: String, contrasena: String) {
        val loginRequest = LoginRequest(email, contrasena)
        viewModelScope.launch {
            try {
                val response = repository.loginUsuario(loginRequest)
                if (response.isSuccessful) {
                    _usuario.value = response.body()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun registrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                val response = repository.registrarUsuario(usuario)
                if (response.isSuccessful) {
                    _usuario.value = response.body()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun registrarUsuarioGoogle(nombre: String, email: String) {
        // Registro inicial vía Google: se guarda el nombre y email, pero la contraseña queda vacía
        val usuarioGoogle = Usuario(
            id = null,
            nombre = nombre,
            email = email,
            contrasena = "", // Indica que el registro está incompleto
            puntosXp = 0,
            fechaRegistro = System.currentTimeMillis(),
            avatar = null
        )
        viewModelScope.launch {
            try {
                val response = repository.registrarUsuario(usuarioGoogle)
                if (response.isSuccessful) {
                    _usuario.value = response.body()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun completeGoogleRegistration(nombre: String, contrasena: String, onComplete: () -> Unit) {
        val currentUser = _usuario.value
        if (currentUser != null) {
            val updatedUser = currentUser.copy(
                nombre = nombre,
                contrasena = contrasena,
                fechaRegistro = System.currentTimeMillis()
            )
            viewModelScope.launch {
                // Aquí se debería llamar a un endpoint PUT para actualizar el usuario.
                // Por simplicidad, simulamos que la actualización es exitosa.
                _usuario.value = updatedUser
                onComplete()
            }
        }
    }
}
