package com.azrael.iqarena.ui.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azrael.iqarena.viewmodel.UsuarioViewModel

@Composable
fun CompleteGoogleRegistrationScreen(
    usuarioViewModel: UsuarioViewModel,
    onCompleteSuccess: () -> Unit
) {
    val usuarioState = usuarioViewModel.usuario.collectAsState().value
    var nombre by remember { mutableStateOf(usuarioState?.nombre ?: "") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Completa tu registro", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (nombre.isNotBlank() && contrasena.isNotBlank()) {
                    usuarioViewModel.completeGoogleRegistration(nombre, contrasena) {
                        onCompleteSuccess()
                    }
                } else {
                    mensaje = "Rellena todos los campos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Completar Registro")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(mensaje.isNotEmpty()){
            Text(mensaje)
        }
    }
}
