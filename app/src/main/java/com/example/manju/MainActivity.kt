package com.example.manju

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.manju.data.ApiClient
import com.example.manju.data.BottomNavigationItem
import com.example.manju.data.manga.Mangas
import com.example.manju.ui.components.NavBar
import com.example.manju.ui.components.TopBar
import com.example.manju.ui.screen.FeedScreen
import com.example.manju.ui.screen.HomeScreen
import com.example.manju.ui.screen.LibraryScreen
import com.example.manju.ui.screen.MangaScreen
import com.example.manju.ui.screen.SearchScreen
import com.example.manju.ui.screen.SocialScreen
import com.example.manju.ui.theme.ManjuTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

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
                        "home", "feed", "search", "social", "library" -> {
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
                                "home", "feed", "search", "social", "library" -> {
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

        composable(route = "home") {
            HomeScreen(navController)
        }

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

        composable(route = "feed") {
            FeedScreen(navController)
        }

        composable(route = "search") {
            SearchScreen(navController)
        }

        composable(route = "social") {
            SocialScreen(navController)
        }

        composable(route = "library") {
            LibraryScreen(navController)
        }
    }
}