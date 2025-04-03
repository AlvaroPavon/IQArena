package com.azrael.iqarena.repository

import com.azrael.iqarena.api.PreguntaApi
import com.azrael.iqarena.model.Pregunta
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PreguntaRepository {
    private val preguntaApi: PreguntaApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PreguntaApi::class.java)
    }

    suspend fun obtenerPreguntaRandom(): Response<Pregunta> {
        return preguntaApi.obtenerPreguntaRandom()
    }
}
