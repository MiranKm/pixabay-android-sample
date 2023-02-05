@file:OptIn(ExperimentalMaterialApi::class)

package dev.miran.pixabayapp.screens.detailsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.flowlayout.FlowRow
import dev.miran.entity.HitsItem
import dev.miran.pixabayapp.composables.LoadingIndicator
import dev.miran.pixabayapp.composables.TagComposable
import dev.miran.view_model.DetailsViewModel
import dev.miran.view_model.util.UiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImageDetailsScreen(id: Int, viewModel: DetailsViewModel, navController: NavController) {

    /*
    A bigger version of the image. The name of the user.
    A list of the image’s tags.
    The number of likes.
    The number of downloads.
    The number of comments.
    */

    LaunchedEffect(Unit) {
        viewModel.selectedImageId(id)
        viewModel.getImageById()
    }

    val uiState by viewModel.imageDetailsState.collectAsState()



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pixabay")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "back button")
                    }
                }
            )
        }
    ) {
        Column {

            when (uiState) {
                is UiState.Error -> {
                    val state = (uiState as UiState.Error).exception
                    Text("Error getting image id $id")
                    Text("Error: ${state.message}")
                }
                UiState.Idle -> {

                }
                UiState.Loading -> {
                    LoadingIndicator()
                }
                is UiState.Success -> {
                    val state = (uiState as UiState.Success<HitsItem>).values

                    SubcomposeAsyncImage(
                        state.largeImageURL,
                        contentDescription = state.tags,
                        modifier = Modifier.fillMaxWidth(),
                        loading = {
                            LoadingIndicator(
                                Modifier
                                    .size(32.dp)
                                    .height(100.dp)
                            )
                        },
                    )

                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = state.userImageURL,
                            contentDescription = state.user,
                            Modifier
                                .size(46.dp)
                                .clip(
                                    RoundedCornerShape(100)
                                )
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(
                            "Photo by: ${state.user}",
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }

                    Divider()
                    Spacer(Modifier.height(22.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {


                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(state.likes.toString(), style = MaterialTheme.typography.body2)
                            Text("Likes", style = TextStyle(fontSize = 14.sp))
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(state.downloads.toString(), style = MaterialTheme.typography.body2)
                            Text("Downloads", style = TextStyle(fontSize = 14.sp))
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(state.comments.toString(), style = MaterialTheme.typography.body2)
                            Text("Comments", style = TextStyle(fontSize = 14.sp))
                        }

                    }
                    Spacer(Modifier.height(22.dp))

                    Divider()


                    Spacer(Modifier.height(22.dp))

                    if (state.tags.isNotEmpty()) {
                        Text("Tags", Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                        FlowRow(
                            mainAxisSpacing = 8.dp,
                            crossAxisSpacing = 8.dp,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        ) {
                            state.tags.split(",").map { tag ->
                                TagComposable(tag, Modifier.padding(end = 6.dp))
                            }
                        }
                    }


                }
            }
        }

    }
}