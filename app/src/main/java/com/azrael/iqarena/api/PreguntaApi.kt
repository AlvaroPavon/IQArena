package com.azrael.iqarena.api

import com.azrael.iqarena.model.Pregunta
import retrofit2.Response
import retrofit2.http.GET

interface PreguntaApi {
    @GET("api/preguntas/random")
    suspend fun obtenerPreguntaRandom(): Response<Pregunta>
}
