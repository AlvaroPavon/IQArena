package com.azrael.iqarena.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Nota: En el emulador, "10.0.2.2" se usa para referirse a localhost de tu máquina.
    private const val BASE_URL = "http://10.0.2.2:8080/"
    val api: UsuarioApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioApi::class.java)
    }
}
