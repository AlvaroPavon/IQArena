package com.azrael.iqarena.model

data class Usuario(
    val id: Long? = null,
    val nombre: String,
    val email: String,
    val contrasena: String,
    val puntosXp: Int = 0,
    val fechaRegistro: Long? = null, // Por ejemplo, System.currentTimeMillis()
    val avatar: Avatar? = null
)
