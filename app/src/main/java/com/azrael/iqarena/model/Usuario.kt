package com.azrael.iqarena.model

data class Usuario(
    val id: Long? = null,
    val nombre: String,
    val email: String,
    val contrasena: String,
    val puntosXp: Int = 0,
    val fechaRegistro: String? = null,
    val avatar: Avatar? = null
)
