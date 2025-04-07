package com.azrael.iqarena.api

import com.azrael.iqarena.model.LoginRequest
import com.azrael.iqarena.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApi {
    @POST("api/usuarios")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<Usuario>

    @POST("api/login")
    suspend fun loginUsuario(@Body loginRequest: LoginRequest): Response<Usuario>
}
