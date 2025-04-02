package com.example.manju.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.manju.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    showBackArrow: Boolean = false,
    currentRoute: String?
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
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
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_outline),
                    contentDescription = "Search",
                    modifier = Modifier.width(24.dp)
                )
            }
            IconButton(
                onClick = {
                    navController.navigate("settings")
                }
            ) {
                BadgedBox(
                    badge = {
                        Badge()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings_outline),
                        contentDescription = "Settings",
                        modifier = Modifier.width(24.dp)
                    )
                }
            }
        },
        modifier = Modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 400)
@Composable
fun TopBarPreviewNoBackArrow() {
    val navController = NavHostController(LocalContext.current)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    TopBar(navController, scrollBehavior, false, "Home")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 400)
@Composable
fun TopAppPreviewBackArrow() {
    val navController = NavHostController(LocalContext.current)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    TopBar(navController, scrollBehavior, true, "Home")
}