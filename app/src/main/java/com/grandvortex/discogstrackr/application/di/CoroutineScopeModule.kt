package com.grandvortex.discogstrackr.application.di

import com.grandvortex.discogstrackr.application.di.AppDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopesModule {
    @Provides
    @Singleton
    @ApplicationScope
    fun provideApplicationCoroutineScope(
        @Dispatcher(IO) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}
