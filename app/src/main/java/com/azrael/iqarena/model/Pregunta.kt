package com.azrael.iqarena.model

data class Pregunta(
    val id: Long? = null,
    val texto: String,
    val dificultad: String? = null,
    val fechaCreacion: Long? = null, // Se usa Long? para almacenar la fecha (epoch millis)
    val temaPersonalizado: TemaPersonalizado? = null
)
