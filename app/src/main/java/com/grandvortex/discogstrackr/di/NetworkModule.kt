package com.grandvortex.discogstrackr.di

import com.grandvortex.discogstrackr.BuildConfig
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepository
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepositoryDefault
import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): BaseUrl = BaseUrl(BuildConfig.BASE_URL)

    @Singleton
    @Provides
    fun provideHttpAuthInterceptor(): Interceptor = Interceptor { chain ->
        val newRequest =
            chain.request().newBuilder().addHeader("Authorization", Credentials.keyAndSecret())
                .build()
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpAuthInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
            addInterceptor(httpAuthInterceptor)
        }.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: BaseUrl): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl.value).client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideRemoteService(retrofit: Retrofit): RemoteService =
        retrofit.create(RemoteService::class.java)

    @Singleton
    @Provides
    fun provideSearchRepository(remoteService: RemoteService): SearchRepository =
        SearchRepositoryDefault(remoteService)
}
