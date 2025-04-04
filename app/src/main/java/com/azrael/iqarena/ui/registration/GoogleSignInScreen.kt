package com.azrael.iqarena.ui.registration

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.azrael.iqarena.viewmodel.UsuarioViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.azrael.iqarena.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GoogleSignInScreen(onSignInSuccess: () -> Unit, usuarioViewModel: UsuarioViewModel) {
    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()

    // Configuración de Google Sign-In: asegúrate de tener el default_web_client_id en strings.xml
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.web_client))
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
                            // Obtén los datos del usuario de Google
                            val nombre = account?.displayName ?: ""
                            val email = account?.email ?: ""
                            // Registra el usuario en la base de datos a través de la API
                            usuarioViewModel.registrarUsuarioGoogle(nombre, email)
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

    Button(onClick = {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }) {
        Text(text = "Iniciar sesión con Google")
    }
}
