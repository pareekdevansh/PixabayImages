package com.devansh.pixabayimages.network

import com.devansh.pixabayimages.network.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService{

    @GET("api/")
    suspend fun getImages(
        @Query("q") image_tag : String,
        @Query("key") api_key : String,
        @Query("image_type") image_type: String
    ) : PixabayResponse


}
