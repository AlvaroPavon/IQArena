package com.azrael.iqarena.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.azrael.iqarena.ui.dashboard.DashboardScreen
import com.azrael.iqarena.ui.game.GameScreen
import com.azrael.iqarena.ui.login.LoginScreen
import com.azrael.iqarena.ui.profile.ProfileScreen
import com.azrael.iqarena.ui.registration.CompleteGoogleRegistrationScreen
import com.azrael.iqarena.ui.registration.RegistrationScreen
import com.azrael.iqarena.ui.tema.CreateTemaScreen
import com.azrael.iqarena.viewmodel.PreguntaViewModel
import com.azrael.iqarena.viewmodel.TemaViewModel
import com.azrael.iqarena.viewmodel.UsuarioViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Registration : Screen("registration")
    object CompleteGoogleRegistration : Screen("completeGoogleRegistration")
    object Dashboard : Screen("dashboard")
    object Game : Screen("game")
    object Profile : Screen("profile")
    object CreateTema : Screen("createTema")
}

@Composable
fun NavGraph(navController: NavHostController, usuarioViewModel: UsuarioViewModel) {
    val preguntaViewModel: PreguntaViewModel = viewModel()
    val temaViewModel: TemaViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                usuarioViewModel = usuarioViewModel,
                onLoginSuccess = { navController.navigate(Screen.Dashboard.route) },
                onNavigateToRegister = { navController.navigate(Screen.Registration.route) },
                onNavigateToCompleteGoogleRegistration = { navController.navigate(Screen.CompleteGoogleRegistration.route) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                usuarioViewModel = usuarioViewModel,
                onRegistrationSuccess = { navController.navigate(Screen.Dashboard.route) },
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.CompleteGoogleRegistration.route) {
            CompleteGoogleRegistrationScreen(
                usuarioViewModel = usuarioViewModel,
                onCompleteSuccess = { navController.navigate(Screen.Dashboard.route) }
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onStartGame = { navController.navigate(Screen.Game.route) },
                onViewProfile = { navController.navigate(Screen.Profile.route) },
                onCreateTema = { navController.navigate(Screen.CreateTema.route) }
            )
        }
        composable(Screen.Game.route) {
            GameScreen(preguntaViewModel = preguntaViewModel)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                usuarioViewModel = usuarioViewModel,
                onBack = { navController.navigate(Screen.Dashboard.route) }
            )
        }
        composable(Screen.CreateTema.route) {
            CreateTemaScreen(
                temaViewModel = temaViewModel,
                onTemaCreated = { navController.navigate(Screen.Dashboard.route) }
            )
        }
    }
}
