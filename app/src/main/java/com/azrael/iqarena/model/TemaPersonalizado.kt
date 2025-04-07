package com.azrael.iqarena.model

data class TemaPersonalizado(
    val id: Long? = null,
    val nombre: String,
    val descripcion: String,
    val usuarioCreadorId: Long? = null, // Permite nulo para mayor seguridad
    val fechaCreacion: Long? = null,
    val esPublico: Boolean = true
)
