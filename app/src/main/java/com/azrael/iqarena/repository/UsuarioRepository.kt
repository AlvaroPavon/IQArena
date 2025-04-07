package com.azrael.iqarena.repository

import com.azrael.iqarena.api.RetrofitInstance
import com.azrael.iqarena.model.LoginRequest
import com.azrael.iqarena.model.Usuario
import retrofit2.Response

class UsuarioRepository {
    suspend fun registrarUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitInstance.api.registrarUsuario(usuario)
    }

    suspend fun loginUsuario(loginRequest: LoginRequest): Response<Usuario> {
        return RetrofitInstance.api.loginUsuario(loginRequest)
    }
}
