package com.azrael.iqarena.api

import com.azrael.iqarena.model.TemaPersonalizado
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TemaApi {
    @POST("api/temas")
    suspend fun createTema(@Body tema: TemaPersonalizado): Response<TemaPersonalizado>

    @GET("api/temas/{id}")
    suspend fun getTema(@Path("id") id: Long): Response<TemaPersonalizado>
}
