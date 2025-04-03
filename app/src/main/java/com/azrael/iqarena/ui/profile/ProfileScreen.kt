package com.azrael.iqarena.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azrael.iqarena.viewmodel.UsuarioViewModel

@Composable
fun ProfileScreen(
    usuarioViewModel: UsuarioViewModel,
    onBack: () -> Unit
) {
    val usuarioState = usuarioViewModel.usuario.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Perfil del Usuario",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        usuarioState.value?.let { usuario ->
            Text(
                text = "Nombre: ${usuario.nombre}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Email: ${usuario.email}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "XP: ${usuario.puntosXp}",
                style = MaterialTheme.typography.bodyMedium
            )
        } ?: run {
            Text(
                text = "No hay usuario registrado",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onBack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Dashboard")
        }
    }
}
