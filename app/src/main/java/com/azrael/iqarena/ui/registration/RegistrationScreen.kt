package com.azrael.iqarena.ui.registration

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.azrael.iqarena.R
import com.azrael.iqarena.model.Usuario
import com.azrael.iqarena.viewmodel.UsuarioViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

const val GOOGLE_CLIENT_ID = "763814888903-fq859jaasaagh2cu6hl6adba6raunlnp.apps.googleusercontent.com"

@Composable
fun RegistrationScreen(
    usuarioViewModel: UsuarioViewModel,
    onRegistrationSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val usuarioState = usuarioViewModel.usuario.collectAsState()

    LaunchedEffect(usuarioState.value) {
        if (usuarioState.value != null) {
            mensaje = "Registro exitoso"
            onRegistrationSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro de Usuario", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
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
                // Se crea el usuario en orden: id = null, nombre, email, contrasena, puntosXp = 0, fechaRegistro = null, avatar = null.
                usuarioViewModel.registrarUsuario(
                    Usuario(null, nombre, email, contrasena, 0, null, null)
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Botón integrado para el inicio de sesión con Google sin navegar a otra pantalla
        GoogleSignInButton(onSignInSuccess = onRegistrationSuccess, usuarioViewModel = usuarioViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = mensaje)
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onNavigateToLogin) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}

@Composable
fun GoogleSignInButton(onSignInSuccess: () -> Unit, usuarioViewModel: UsuarioViewModel) {
    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(Exception::class.java)
                val idToken = account?.idToken
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            val nombreGoogle = account?.displayName ?: ""
                            val emailGoogle = account?.email ?: ""
                            usuarioViewModel.registrarUsuarioGoogle(nombreGoogle, emailGoogle)
                            onSignInSuccess()
                        } else {
                            Log.e("GoogleSignIn", "Error en autenticación: ${authTask.exception}")
                        }
                    }
            } catch (e: Exception) {
                Log.e("GoogleSignIn", "Error al obtener cuenta de Google", e)
            }
        }
    }

    Button(
        onClick = {
            val signInIntent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Registrarse con Google")
    }
}
