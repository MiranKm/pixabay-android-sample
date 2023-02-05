package dev.miran.pixabayapp.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.miran.pixabayapp.ui.theme.SizeUtil
import dev.miran.pixabayapp.util.VoidCallback
import dev.miran.view_model.util.conditional

@Composable
fun TagComposable(
    label: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: VoidCallback? = null
) {
    val primaryColor = MaterialTheme.colors.primary
    val color =
        animateColorAsState(targetValue = if (selected) MaterialTheme.colors.primary else Color.Transparent)

    Box(
        modifier

            .border(
                BorderStroke(2.dp, color = primaryColor),
                shape = RoundedCornerShape(50),
            )
            .clip(RoundedCornerShape(50))
            .conditional(selected) {
                clip(RoundedCornerShape(50))
                background(
                    color = color.value,
                    shape = RoundedCornerShape(50),
                )
            }

            .conditional(onClick != null) {
                clickable {
                    onClick?.let { it() }
                }

            }
            .padding(horizontal = SizeUtil.medium, vertical = SizeUtil.small)

    ) {
        Text(label)
    }

}