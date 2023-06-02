package com.devansh.pixabayimages.ui.component

import com.devansh.pixabayimages.network.model.Hit

data class MainState(
    var loading : Boolean = false,
    val images  : List<Hit> = emptyList(),
    val error :String = ""
)
