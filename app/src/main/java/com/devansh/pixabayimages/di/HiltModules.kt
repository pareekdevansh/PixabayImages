package com.devansh.pixabayimages.di

import com.devansh.pixabayimages.network.PixabayApiService
import com.devansh.pixabayimages.ui.component.PixabayRepository
import com.devansh.pixabayimages.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HiltModules {

    @Singleton
    @Provides
    fun providePixabayApiService(): PixabayApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayApiService::class.java)
    }

    @Provides
    fun providePixabayRepository(pixabayApiService: PixabayApiService): PixabayRepository {
        return PixabayRepository(pixabayApiService = providePixabayApiService())
    }

}
