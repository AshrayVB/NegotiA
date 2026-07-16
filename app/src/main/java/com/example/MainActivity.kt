package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.MainViewModel
import com.example.ui.screens.NegotiationDetailScreen
import com.example.ui.screens.NewNegotiationScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NegotiAIApp()
                }
            }
        }
    }
}

@Composable
fun NegotiAIApp() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") {
            com.example.ui.screens.LandingScreen(
                onNavigateToDashboard = {
                    navController.navigate("dashboard") {
                        popUpTo("landing") { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                viewModel = viewModel,
                onNavigateToNew = { navController.navigate("new") },
                onNavigateToDetail = { id -> navController.navigate("detail/$id") }
            )
        }
        composable("new") {
            NewNegotiationScreen(
                viewModel = viewModel,
                onBack = { navController.navigateUp() },
                onNavigateToDetail = {
                    navController.popBackStack()
                    navController.navigate("detail/-1") // -1 means use current temporary analysis
                }
            )
        }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            NegotiationDetailScreen(
                id = id,
                viewModel = viewModel,
                onBack = { navController.navigateUp() }
            )
        }
    }
}
