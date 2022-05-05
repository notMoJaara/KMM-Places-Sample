package com.mohamad.kmmplaces

import co.touchlab.kermit.Logger
import com.mohamad.kmmplaces.functional.fold
import com.mohamad.kmmplaces.network.foursquare.FoursquareFailure
import com.mohamad.kmmplaces.util.Either

sealed class CoreError {
    abstract val rootCause: Throwable
}

sealed class NetworkError : CoreError() {
    // TODO: add relevant network errors (E.g. no internet connection)
    data class Generic(override val rootCause: Throwable) : NetworkError()
}

sealed class StorageError : CoreError() {
    object DataNotFound : StorageError() {
        override val rootCause: Throwable by lazy { NullPointerException() }
    }

    data class Generic(override val rootCause: Throwable) : StorageError()
}


internal inline fun <T : Any> wrapRemoteRequest(networkCall: () -> Either<FoursquareFailure, T>): Either<NetworkError, T> {
    return try {
        networkCall().fold({
            Either.Left(NetworkError.Generic(it.rootCause))
        }, {
            Either.Right(it)
        })
    } catch (e: Exception) {
        Either.Left(NetworkError.Generic(e))
    }
}


internal inline fun <T : Any> wrapStorageRequest(storageRequest: () -> T?): Either<StorageError, T> {
    return try {
        storageRequest()?.let { Either.Right(it) } ?: Either.Left(StorageError.DataNotFound)
    } catch (e: Exception) {
        Either.Left(StorageError.Generic(e))
    }
}

