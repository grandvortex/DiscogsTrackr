package com.grandvortex.discogstrackr.di

import android.util.Log
import com.grandvortex.discogstrackr.BuildConfig
import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import com.grandvortex.discogstrackr.utils.BaseUrl
import com.grandvortex.discogstrackr.utils.Credentials
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
                addNetworkInterceptor(
                    HttpLoggingInterceptor { message ->
                        Log.d("DTOKHTTP", message)
                    }.apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
            addInterceptor(httpAuthInterceptor)
        }.build()

    @Singleton
    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    )

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: BaseUrl,
        moshiConverter: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(baseUrl.value).client(okHttpClient)
        .addConverterFactory(moshiConverter).build()

    @Singleton
    @Provides
    fun provideRemoteService(retrofit: Retrofit): RemoteService =
        retrofit.create(RemoteService::class.java)
}
