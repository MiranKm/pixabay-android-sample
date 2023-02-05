package dev.miran.pixabayapp.screens.searchScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import dev.miran.entity.HitsItem
import dev.miran.entity.ImageType
import dev.miran.pixabayapp.composables.LoadingIndicator
import dev.miran.pixabayapp.composables.TagComposable
import dev.miran.pixabayapp.ui.theme.SizeUtil
import dev.miran.pixabayapp.uiUtil.navigation.navigateToImageDetailsScreen
import dev.miran.view_model.HomeViewModel
import dev.miran.view_model.util.UiState


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(homeViewModel: HomeViewModel, navController: NavController) {

    val imagesState by homeViewModel.imagesState.collectAsState()

    val selectedImageTypeFilter = homeViewModel.getSelectedFilterImageType()

    var showGoDetailsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var selectedHitImage by remember {
        mutableStateOf<HitsItem?>(null)
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pixabay")
                }
            )
        }
    ) {

        Column {

            TextField(
                value = homeViewModel.getSearchQuery(),
                onValueChange = homeViewModel::searchFieldOnChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SizeUtil.medium),

                trailingIcon = {
                    IconButton(onClick = homeViewModel::searchNewImages) {
                        Icon(Icons.Default.Search, "search")
                    }
                }
            )

            Row(
                Modifier
                    .padding(horizontal = 16.dp)
                    .horizontalScroll(
                        rememberScrollState()
                    )
            ) {
                ImageType.values().map {
                    TagComposable(
                        it.name,
                        Modifier.padding(end = 8.dp),
                        it == selectedImageTypeFilter
                    ) {
                        homeViewModel.setImageTypeFilter(it)
                        homeViewModel.searchNewImages()
                    }
                }
            }


            when (imagesState) {
                is UiState.Error -> {
                    Text("Error loading images")
                }
                UiState.Idle -> {

                }
                UiState.Loading -> {
                    LoadingIndicator()
                }
                is UiState.Success -> {
                    val state = (imagesState as UiState.Success<List<HitsItem>>)

                    if (state.values.isEmpty()) {
                        Box(Modifier.fillMaxSize()) {
                            Text("No images found", Modifier.align(Alignment.Center))
                        }
                    }

                    LazyColumn {
                        itemsIndexed(state.values) { _, item ->
                            ItemHitImage(item) {
                                selectedHitImage = item
                                showGoDetailsDialog = true
                            }
                        }
                    }
                }
            }
        }
    }

    if (showGoDetailsDialog)
        AlertDialog(
            onDismissRequest = { showGoDetailsDialog = false },
            title = {
                Text("See photo's details?")
            },
            text = {
                Text("Are you sure you want to see this photos details?")
            },
            properties = DialogProperties(), confirmButton = {
                Button(onClick = {
                    showGoDetailsDialog = false
                    navController.navigateToImageDetailsScreen(selectedHitImage!!.id)
                }) {
                    Text("Yes")
                }
            }, dismissButton = {
                TextButton(onClick = {
                    showGoDetailsDialog = false
                    selectedHitImage = null
                }) {
                    Text("No")
                }
            })
}