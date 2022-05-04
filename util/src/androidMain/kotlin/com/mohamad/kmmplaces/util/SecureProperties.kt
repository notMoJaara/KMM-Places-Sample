package com.mohamad.kmmplaces.util

actual object SecureProperties {
    actual val fourSquareKey: String get() = BuildConfig.fourSquareKey
    actual val googleMapsKey: String get() = TODO("Not yet implemented")
    actual val defLatL: String
        get() = BuildConfig.defLat
    actual val defLong: String
        get() = BuildConfig.defLong
}