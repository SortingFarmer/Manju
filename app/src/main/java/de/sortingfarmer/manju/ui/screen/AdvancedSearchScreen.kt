package de.sortingfarmer.manju.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.MangaApi
import de.sortingfarmer.manju.openapi.models.MangaList
import de.sortingfarmer.manju.ui.components.MangaImageCard
import de.sortingfarmer.manju.ui.components.MangaTextCard
import kotlinx.coroutines.launch
import retrofit2.Response
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedSearchScreen(navController: NavController) {
    var manga by remember { mutableStateOf<MangaList?>(null) }
    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Cover Grid", "Detailed List")
    val searchText = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        manga = RetrofitClient.instance.create(MangaApi::class.java)
            .getSearchManga(
                includes = listOf("cover_art", "tags"),
                limit = 20
            ).body()
    }

    Column {
        if (manga == null) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        if (selectedIndex == 0) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 6.dp),
                state = gridState
            ) {
                item(
                    span = { GridItemSpan(2) }
                ) {
                    ListHeader(
                        options = options,
                        selectedIndex = selectedIndex,
                        onOptionSelected = { selectedIndex = it },
                        onSearchClicked = { showBottomSheet = true },
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                    )
                }
                manga?.data?.forEach { manga ->
                    item {
                        MangaImageCard(manga) {
                            navController.navigate("title/${manga.id}")
                        }
                    }
                }
            }
        } else if (selectedIndex == 1) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 10.dp),
                state = listState
            ) {
                item {
                    ListHeader(
                        options = options,
                        selectedIndex = selectedIndex,
                        onOptionSelected = { selectedIndex = it },
                        onSearchClicked = { showBottomSheet = true },
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                    )
                }
                manga?.data?.forEach { manga ->
                    item {
                        MangaTextCard(manga) {
                            navController.navigate("title/${manga.id}")
                        }
                    }
                }
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    
                    TextField(
                        value = searchText.value,
                        onValueChange = { searchText.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        placeholder = { Text("Search") },
                        label = { Text("Search") }
                    )
                }
            }
        }
    }
}

@Composable
fun ListHeader(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.padding(end = 10.dp)
        ) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == selectedIndex,
                    onClick = { onOptionSelected(index) },
                    shape = SegmentedButtonDefaults.itemShape(index, options.size),
                    label = {
                        Icon(
                            painter = painterResource(
                                when (label) {
                                    "Cover Grid" -> if (selectedIndex == index) R.drawable.grid else R.drawable.grid_outline
                                    "Detailed List" -> if (selectedIndex == index) R.drawable.list else R.drawable.list_outline
                                    else -> R.drawable.information
                                }
                            ),
                            contentDescription = stringResource(R.string.search),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )
            }
        }
        Button(onClick = onSearchClicked) {
            Icon(
                painter = painterResource(R.drawable.search_outline),
                contentDescription = stringResource(R.string.search),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}