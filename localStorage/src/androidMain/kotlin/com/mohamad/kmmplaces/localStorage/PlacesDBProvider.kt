package com.mohamad.kmmplaces.localStorage

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

// TODO: register content provider in the android app to easily get app context
actual class PlacesDBProvider(context: Context) {
    private val database: PoiDatabase

    init {
        val onConnectCallback = object : AndroidSqliteDriver.Callback(PoiDatabase.Schema) {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                db.execSQL("PRAGMA foreign_keys=ON;")
            }
        }
        val driver = AndroidSqliteDriver(
            schema = PoiDatabase.Schema,
            context = context,
            name = DBName,
            callback = onConnectCallback
        )
        database = PoiDatabase(driver)
    }

    actual val poiEntityQueries: PoiEntityQueries
        get() = database.poiEntityQueries

    private companion object {
        const val DBName = "poi_database"
    }

}