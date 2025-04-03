package com.azrael.iqarena.model

data class TemaPersonalizado(
    val id: Long? = null,
    val nombre: String,
    val descripcion: String,
    val usuarioCreadorId: Long? = null,
    val fechaCreacion: String? = null,
    val esPublico: Boolean = true
)
