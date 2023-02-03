package dev.miran.pixabayapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.miran.view_model.HomeViewModel
import dev.miran.view_model.util.UiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(homeViewModel: HomeViewModel, navController: NavController) {

    val imagesState = homeViewModel.imagesState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pixaby")
                }
            )
        }
    ) {

        TextField(
            value = homeViewModel.getSearchQuery(),
            onValueChange = homeViewModel::searchFieldOnChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            trailingIcon = {
                IconButton(onClick = homeViewModel::searchNewImages) {
                    Icon(Icons.Default.Search, "search")
                }
            }
        )


        when (imagesState) {
            is UiState.Error -> {
                Text("Error loading images")
            }
            UiState.Idle -> {

            }
            UiState.Loading -> {

                CircularProgressIndicator()
            }
            is UiState.Success -> {
                LazyColumn {
                    itemsIndexed(imagesState.values) { index, item ->
                        Text(item.pageURL.toString())
                    }
                }

            }
        }
    }

}