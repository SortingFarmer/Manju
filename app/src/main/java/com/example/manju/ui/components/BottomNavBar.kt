package com.example.manju.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.manju.R
import com.example.manju.data.BottomNavigationItem

@Composable
fun NavBar(navController: NavHostController, navBackStackEntry: NavBackStackEntry?) {
    NavigationBar {
        items.forEach{ item ->
            val isSelected =
                item.route == navBackStackEntry?.destination?.route
            NavigationBarItem(
                selected = isSelected,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.route) {

                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != 0) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            painterResource(if (isSelected) item.selectedIcon else item.unselectedIcon),
                            contentDescription = item.title,
                            modifier = Modifier.width(24.dp)
                        )
                    }
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}

val items = listOf(
    BottomNavigationItem(
        route = "home",
        title = "Home",
        selectedIcon = R.drawable.home,
        unselectedIcon = R.drawable.home_outline
    ),
    BottomNavigationItem(
        route = "feed",
        title = "Feed",
        selectedIcon = R.drawable.bookmarks,
        unselectedIcon = R.drawable.bookmarks_outline,
        badgeCount = 5
    ),
    BottomNavigationItem(
        route = "search",
        title = "Search",
        selectedIcon = R.drawable.search,
        unselectedIcon = R.drawable.search_outline
    ),
    BottomNavigationItem(
        route = "social",
        title = "Social",
        selectedIcon = R.drawable.people,
        unselectedIcon = R.drawable.people_outline
    ),
    BottomNavigationItem(
        route = "library",
        title = "Library",
        selectedIcon = R.drawable.library,
        unselectedIcon = R.drawable.library_outline
    )
)