package com.example.manju.ui.screen.sub

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.manju.dataClass.ApiClient
import com.example.manju.dataClass.apiResult.GET.GetManga
import com.example.manju.ui.components.MangaTextCard

@Composable
fun MangaScreen(navController: NavHostController, id: String) {
    var manga by remember { mutableStateOf<GetManga?>(null) }

    LaunchedEffect(key1 = Unit) {
        manga = ApiClient.api.getMangaById(id)
    }

    if (manga != null) {
        MangaTextCard(manga!!.data) {}
    } else {
        Text("Loading...") // Or some other loading indicator
    }
}