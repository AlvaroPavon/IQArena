package com.azrael.iqarena.api

import com.azrael.iqarena.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApi {
    @POST("api/usuarios")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<Usuario>

    @GET("api/usuarios/{id}")
    suspend fun obtenerUsuario(@Path("id") id: Long): Response<Usuario>
}
