package com.azrael.iqarena.ui.tema

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azrael.iqarena.model.TemaPersonalizado
import com.azrael.iqarena.viewmodel.TemaViewModel

@Composable
fun CreateTemaScreen(
    temaViewModel: TemaViewModel,
    onTemaCreated: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var esPublico by remember { mutableStateOf(true) }
    var mensaje by remember { mutableStateOf("") }

    val temaState = temaViewModel.tema.collectAsState()

    LaunchedEffect(temaState.value) {
        if (temaState.value != null) {
            mensaje = "Tema creado exitosamente"
            onTemaCreated()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear Tema Personalizado",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del Tema") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = esPublico,
                onCheckedChange = { esPublico = it }
            )
            Text(text = "Tema Público")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Simulamos que el usuario actual tiene id 1; en producción, se tomaría el id del usuario logueado.
                val nuevoTema = TemaPersonalizado(
                    nombre = nombre,
                    descripcion = descripcion,
                    usuarioCreadorId = 1L,
                    fechaCreacion = null, // Se puede generar en el back-end
                    esPublico = esPublico
                )
                temaViewModel.createTema(nuevoTema)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Tema")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (mensaje.isNotEmpty()) {
            Text(text = mensaje)
        }
    }
}
