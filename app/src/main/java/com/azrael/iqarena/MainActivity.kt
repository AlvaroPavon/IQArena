package com.azrael.iqarena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.azrael.iqarena.ui.NavGraph
import com.azrael.iqarena.ui.theme.IqarenaTheme
import com.azrael.iqarena.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IqarenaTheme {
                val navController = rememberNavController()
                val usuarioViewModel: UsuarioViewModel = viewModel()
                NavGraph(
                    navController = navController,
                    usuarioViewModel = usuarioViewModel
                )
            }
        }
    }
}
