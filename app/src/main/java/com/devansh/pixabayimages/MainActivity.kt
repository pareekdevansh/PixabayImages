package com.devansh.pixabayimages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.devansh.pixabayimages.ui.component.HomeScreen
import com.devansh.pixabayimages.ui.theme.PixabayImagesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixabayImagesTheme {
                HomeScreen()
            }
        }
    }
}
