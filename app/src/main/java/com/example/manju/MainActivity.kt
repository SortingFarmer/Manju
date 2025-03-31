package com.example.manju

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.manju.ui.components.NavBar
import com.example.manju.ui.components.TopBar
import com.example.manju.ui.screen.UpdatesScreen
import com.example.manju.ui.screen.HomeScreen
import com.example.manju.ui.screen.LibraryScreen
import com.example.manju.ui.screen.MangaScreen
import com.example.manju.ui.screen.AdvancedSearchScreen
import com.example.manju.ui.screen.SettingsScreen
import com.example.manju.ui.screen.SocialScreen
import com.example.manju.ui.screen.sub.AboutScreen
import com.example.manju.ui.theme.ManjuTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManjuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    var showBackArrow = true

                    when (navBackStackEntry?.destination?.route) {
                        "home" -> {
                            showBackArrow = false
                        }

                        else -> {
                            showBackArrow = true
                        }
                    }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopBar(navController, showBackArrow, navBackStackEntry?.destination?.route)
                        },
                        bottomBar = {
                            when (navBackStackEntry?.destination?.route) {
                                "home", "updates", "advanced_search", "social", "library" -> {
                                    NavBar(navController, navBackStackEntry)
                                }
                            }
                        }
                    ) { innerPadding ->

                        Navigation(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    bottom = innerPadding.calculateBottomPadding(),
                                    top = innerPadding.calculateTopPadding()
                                ),
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home"
    ) {

        //Main screens
        composable(route = "home") {
            HomeScreen(navController)
        }

        composable(route = "updates") {
            UpdatesScreen(navController)
        }

        composable(route = "advanced_search") {
            AdvancedSearchScreen(navController)
        }

        composable(route = "social") {
            SocialScreen(navController)
        }

        composable(route = "library") {
            LibraryScreen(navController)
        }

        //Manga screens
        composable(
            route = "title/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            MangaScreen(navController, backStackEntry.arguments?.getString("id"))
        }

        //Secondary screens
        composable(route = "settings") {
            SettingsScreen(navController)
        }

        composable(route = "account") {
            Text("Account")
        }

        composable(route = "privacy") {
            Text("Privacy")
        }

        composable(route = "notifications") {
            Text("Notifications")
        }

        composable(route = "about") {
            AboutScreen()
        }
    }
}