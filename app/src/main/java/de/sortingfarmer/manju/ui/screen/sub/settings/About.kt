package de.sortingfarmer.manju.ui.screen.sub.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.sortingfarmer.manju.R


@Composable
fun AboutScreen() {
    var iconSize = 30.dp
    Box {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo_github),
                        contentDescription = "GitHub",
                        modifier = Modifier.size(iconSize)
                    )
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo_discord),
                        contentDescription = "Discord",
                        modifier = Modifier.size(iconSize)
                    )
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo_twitter),
                        contentDescription = "Twitter",
                        modifier = Modifier.size(iconSize)
                    )
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo_paypal),
                        contentDescription = "Paypal",
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
        }
    }
}