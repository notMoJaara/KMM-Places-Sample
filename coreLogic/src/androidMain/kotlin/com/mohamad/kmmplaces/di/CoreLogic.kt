package com.mohamad.kmmplaces.di

import android.content.Context
import com.mohamad.kmmplaces.localStorage.PlacesDBProvider

actual class CoreLogic(private val appContext: Context) : CoreLogicCommon() {
    override fun createDataBase(): PlacesDBProvider = PlacesDBProvider(appContext)
}