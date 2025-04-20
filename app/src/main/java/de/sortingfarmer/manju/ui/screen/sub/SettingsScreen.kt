package de.sortingfarmer.manju.ui.screen.sub

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.dataClass.Settings

@Composable
fun SettingsScreen(navController: NavController) {
    LazyColumn {
        items.forEach { item ->
            item {
                SettingsItem(item) {
                    navController.navigate(item.route)
                }
            }
        }
    }
}

@Composable
fun SettingsItem(item: Settings, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        headlineContent = {
            Text(item.title)
        },
        leadingContent = {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = item.title,
                modifier = Modifier.size(20.dp)
            )
        },
    )
}

val items = listOf(
    Settings(
        title = "Account",
        icon = R.drawable.person_outline,
        route = "account"
    ),
    Settings(
        title = "Privacy",
        icon = R.drawable.shield_half,
        route = "privacy"
    ),
//    Settings(
//        title = "Notifications",
//        icon = R.drawable.notifications_outline,
//        route = "notifications"
//    ),
    Settings(
        title = "About",
        icon = R.drawable.information_circle_outline,
        route = "about"
    )
)