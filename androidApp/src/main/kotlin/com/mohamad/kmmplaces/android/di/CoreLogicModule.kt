package com.mohamad.kmmplaces.android.di

import android.content.Context
import com.mohamad.kmmplaces.di.CoreLogic
import com.mohamad.kmmplaces.feature.ObservePoiListUseCase
import com.mohamad.kmmplaces.feature.UpdatePoiFromRemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PlacesCoreLogic

@Module
@InstallIn(SingletonComponent::class)
class CoreLogicModule {
    @PlacesCoreLogic
    @Singleton
    @Provides
    // TODO: register content provider for context
    fun coreLogicProvider(@ApplicationContext context: Context): CoreLogic = CoreLogic(appContext = context)
}

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @ViewModelScoped
    @Provides
    fun observePoiListUseCaseProvider(@PlacesCoreLogic coreLogic: CoreLogic): ObservePoiListUseCase = coreLogic.observePoiListUseCase

    @ViewModelScoped
    @Provides
    fun updatePoiFromRemoteUseCaseProvider(@PlacesCoreLogic coreLogic: CoreLogic): UpdatePoiFromRemoteUseCase =
        coreLogic.updatePoiFromRemoteUseCase
}