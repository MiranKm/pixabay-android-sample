@file:OptIn(ExperimentalMaterialApi::class)

package dev.miran.pixabayapp.screens.searchScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.miran.entity.HitsItem
import dev.miran.pixabayapp.composables.TagComposable
import dev.miran.pixabayapp.ui.theme.SizeUtil
import dev.miran.pixabayapp.util.VoidCallback

@Composable
fun ItemHitImage(hitImage: HitsItem, onClick: VoidCallback) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier =
        Modifier
            .padding(SizeUtil.medium)
            //todo added alternative shadow to the card since the clip is cutting it
            // the clip is for clipping the clickable {  }'s ripple since its a squire and the card is by default has a radius
            .shadow(4.dp, MaterialTheme.shapes.medium, false, Color.Black.copy(0.1f))
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick()
            }

    ) {
        Column {
            AsyncImage(
                model = hitImage.previewURL,
                contentDescription = hitImage.tags,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Text(
                "Photo by: ${hitImage.user}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(SizeUtil.medium)
            )

            if (hitImage.tags.isNotEmpty())
                Row(
                    Modifier
                        .padding(8.dp)
                        .horizontalScroll(
                            rememberScrollState()
                        )
                ) {
                    hitImage.tags.split(",").map { tag ->
                        TagComposable(tag, Modifier.padding(end = 6.dp))
                    }
                }

        }
    }
}


private val hitImageSample = HitsItem(
    id = 1122537,
    pageURL = "https://pixabay.com/photos/apple-water-droplets-fruit-moist-1122537/",
    type = "photo",
    tags = "apple, water droplets, fruit",
    previewURL = "https://cdn.pixabay.com/photo/2016/01/05/13/58/apple-1122537_150.jpg",
    previewWidth = 150,
    previewHeight = 95,
    webformatURL = "https://pixabay.com/get/gce3980a49333f35841990725fc36e94689aa25504fc6a8c48903ffa0ed30a363821fd7b80d60fc35af8bee17e2e0592600e360b9a702efda0ae13596efeb2df7_640.jpg",
    webformatWidth = 640,
    webformatHeight = 409,
    largeImageURL = "https://pixabay.com/get/g6a51469bc1de094b01120c6fad5252d0bf93738d01b26b7139e69674f36d791176f55429cebc756a5be998e74da50581334144ad75954031ae898c120101cc41_1280.jpg",
    imageWidth = 4752,
    imageHeight = 3044,
    imageSize = 5213632,
    views = 315025,
    downloads = 180751,
    collections = 1022,
    likes = 1127,
    comments = 184,
    userId = 1445608,
    user = "mploscar",
    userImageURL = "https://cdn.pixabay.com/user/2016/01/05/14-08-20-943_250x250.jpg"

)

@Preview
@Composable
fun ItemHitImagePrev() {

    Column {
        ItemHitImage(hitImage = hitImageSample) {

        }
    }
}