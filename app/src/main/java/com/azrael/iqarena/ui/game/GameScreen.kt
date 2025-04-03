package com.azrael.iqarena.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azrael.iqarena.viewmodel.PreguntaViewModel

@Composable
fun GameScreen(preguntaViewModel: PreguntaViewModel) {
    val preguntaState = preguntaViewModel.pregunta.collectAsState()

    LaunchedEffect(Unit) {
        preguntaViewModel.fetchPregunta()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (preguntaState.value == null) {
            CircularProgressIndicator()
        } else {
            Text(
                text = "Pregunta:",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = preguntaState.value!!.texto,
                style = MaterialTheme.typography.bodyMedium
            )
            // Aquí se pueden agregar botones para opciones de respuesta y lógica de evaluación.
        }
    }
}
