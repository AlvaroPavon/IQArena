package com.azrael.iqarena.ui.dashboard

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    onStartGame: () -> Unit,
    onViewProfile: () -> Unit,
    onCreateTema: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a IQ ARENA - Dashboard",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onStartGame,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Partida")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onViewProfile,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Perfil")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onCreateTema,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Tema")
        }
    }
}
