package dev.miran.pixabayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.miran.pixabayapp.screens.BaseScreen
import dev.miran.pixabayapp.ui.theme.PixabayAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixabayAppTheme {
                BaseScreen()
            }
        }
    }
}
