package com.azrael.iqarena.repository

import com.azrael.iqarena.api.TemaApi
import com.azrael.iqarena.model.TemaPersonalizado
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TemaRepository {
    private val temaApi: TemaApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TemaApi::class.java)
    }

    suspend fun createTema(tema: TemaPersonalizado): Response<TemaPersonalizado> {
        return temaApi.createTema(tema)
    }
}
