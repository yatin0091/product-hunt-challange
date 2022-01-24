package com.yatin.producthunt.di

import com.yatin.producthunt.domain.api.MakerApi
import com.yatin.producthunt.domain.api.PostApi
import com.yatin.producthunt.model.repositories.MakerRepository
import com.yatin.producthunt.model.repositories.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providePostsRepository(postsRepository: PostsRepository): PostApi = postsRepository

    @Provides
    @Singleton
    fun provideMakerRepository(makerRepository: MakerRepository): MakerApi = makerRepository
}
