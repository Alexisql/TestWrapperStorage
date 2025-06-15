package com.alexis.testwrapperstorage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alexis.testwrapperstorage.ui.core.ErrorScreen
import com.alexis.testwrapperstorage.ui.home.HomeScreen
import com.alexis.testwrapperstorage.ui.home.HomeViewModel
import com.alexis.testwrapperstorage.ui.login.LoginScreen
import com.alexis.testwrapperstorage.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                val navigationController = rememberNavController()
                NavHost(navController = navigationController, startDestination = "Login") {
                    composable("Login") {
                        LoginScreen(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = loginViewModel,
                            navController = navigationController
                        )
                    }
                    composable("Home") {
                        HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = homeViewModel,
                            navController = navigationController
                        )
                    }
                    composable(
                        route = "Error/{message}",
                        arguments = listOf(navArgument("message") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        ErrorScreen(
                            modifier = Modifier.padding(innerPadding),
                            message = backStackEntry.arguments?.getString("message")!!,
                            navController = navigationController
                        )
                    }
                }
            }
        }
    }
}