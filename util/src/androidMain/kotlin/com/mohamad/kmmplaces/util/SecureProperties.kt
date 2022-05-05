package com.mohamad.kmmplaces.util

actual object SecureProperties {
    actual val fourSquareKey: String get() = BuildConfig.fourSquareKey
    actual val defLat: String
        get() = BuildConfig.defLat
    actual val defLong: String
        get() = BuildConfig.defLong
}