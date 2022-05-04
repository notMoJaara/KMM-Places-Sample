package com.mohamad.kmmplaces.di

import com.mohamad.kmmplaces.localStorage.PlacesDBProvider

actual class CoreLogic: CoreLogicCommon() {
    override fun createDataBase(): PlacesDBProvider {
        TODO("Not yet implemented")
    }
}