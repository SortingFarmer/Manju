package de.sortingfarmer.manju.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import de.sortingfarmer.manju.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    showBackArrow: Boolean = false,
    currentRoute: String?,
) {
    TopAppBar(
        title = {
            Text(
                text = if (showBackArrow) currentRoute?.split('/')[0]?.replaceFirstChar {
                    it.uppercase()
                }?.replace("_", " ") ?: "Manju" else "Manju",
                fontSize = 24.sp
            )
        },
        navigationIcon = {
            if (showBackArrow) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back_outline),
                        contentDescription = "Back",
                        modifier = Modifier.width(24.dp)
                    )
                }
            }
        },
        actions = {
            if (currentRoute != "advanced_search") {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_outline),
                        contentDescription = "Search",
                        modifier = Modifier.width(24.dp)
                    )
                }
            }
            IconButton(
                onClick = {
                    navController.navigate("settings")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_outline),
                    contentDescription = "Settings",
                    modifier = Modifier.width(24.dp)
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 400)
@Composable
fun TopBarPreviewNoBackArrow() {
    val navController = NavHostController(LocalContext.current)
    TopBar(navController, false, "Home")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 400)
@Composable
fun TopBarPreviewNoSearchArrow() {
    val navController = NavHostController(LocalContext.current)
    TopBar(navController, false, "advanced_search")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 400)
@Composable
fun TopAppPreviewBackArrow() {
    val navController = NavHostController(LocalContext.current)
    TopBar(navController, true, "Home")
}