package com.devansh.pixabayimages.ui.component

import com.devansh.pixabayimages.network.PixabayApiService
import com.devansh.pixabayimages.network.model.PixabayResponse
import com.devansh.pixabayimages.utils.Constants
import com.devansh.pixabayimages.utils.Resource
import javax.inject.Inject


class PixabayRepository @Inject constructor(private val pixabayApiService: PixabayApiService) {
    suspend fun getQueryImages(query: String): Resource<PixabayResponse> {
        return try {
            val response = pixabayApiService.getImages(
                image_tag = query,
                api_key = Constants.API_KEY,
                image_type = "photo"
            )
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

}
