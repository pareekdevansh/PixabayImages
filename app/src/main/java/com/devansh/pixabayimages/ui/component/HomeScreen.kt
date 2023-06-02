package com.devansh.pixabayimages.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.devansh.pixabayimages.R
import com.devansh.pixabayimages.network.model.Hit

@Preview(showBackground = true)
@Composable
fun HomeScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            SearchBar(mainViewModel)
            Spacer(modifier = Modifier.height(8.dp))
            val state = mainViewModel.state.value
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(

                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(width = 100.dp, height = 100.dp)
                    )
                }
            } else if (state.error.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(state.error)
                }
            } else {
                val list = state.images
                if (list.isEmpty()) {
                    Text("No matching images found")
                } else {
                    ShowImages(list)
                }
            }
        }
    }
}

@Composable
fun ShowImages(list: List<Hit>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        list?.let {
            items(it) {
                ImageItem(image = it)
            }
        }
    }
}

@Composable
fun ImageItem(image: Hit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val painter =
            rememberAsyncImagePainter(model = image.largeImageURL)
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(260.dp)
                .fillMaxWidth()
        )

        Row {
            Icon(
                painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                contentDescription = "views icon"
            )
            Text(text = image.views.toString())

            Icon(
                painter = painterResource(id = R.drawable.baseline_save_alt_24),
                contentDescription = "downloads icon"
            )
            Text(text = image.downloads.toString())

            Icon(
                painter = painterResource(id = R.drawable.baseline_thumb_up_24),
                contentDescription = "likes count icon"
            )
            Text(text = image.likes.toString())

            Icon(
                painter = painterResource(id = R.drawable.baseline_comment_24),
                contentDescription = "comments count icon"
            )
            Text(text = image.comments.toString())
        }
    }
}

@Composable
fun SearchBar(viewModel: MainViewModel) {
    var text by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { newText ->
            text = newText
            viewModel.getImages(newText)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
        },
        maxLines = 1,
        placeholder = {
            Text(text = "Search Images")
        })

}
