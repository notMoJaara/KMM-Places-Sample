package com.mohamad.kmmplaces.network.foursquare

import com.mohamad.kmmplaces.util.Either
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias FoursquareResponse<T> = Either<FoursquareFailure, T>

@Serializable
internal data class ErrorResponse(
    @SerialName("message") val message: String
)

sealed class FoursquareFailure {
    abstract val rootCause: Exception

    data class RequestError(override val rootCause: Exception, val message: String) : FoursquareFailure()
    data class Server(override val rootCause: Exception) : FoursquareFailure()
    data class Generic(override val rootCause: Exception) : FoursquareFailure()
}


internal suspend inline fun <reified BodyType : Any> wrapFoursquareRequest(performRequest: () -> HttpResponse): FoursquareResponse<BodyType> {
    return try {
        performRequest().let { result ->
            Either.Right(result.body())
        }
    } catch (e: ResponseException) {
        when (e) {
            // 300 .. 399
            is RedirectResponseException -> {
                Either.Left(FoursquareFailure.Generic(e))
            }
            // 400 .. 499
            is ClientRequestException -> {
                e.response.body<ErrorResponse>().message.let { errorMessage ->
                    Either.Left(FoursquareFailure.RequestError(e, errorMessage))
                }
            }
            // 500 .. 599
            is ServerResponseException -> {
                Either.Left(FoursquareFailure.Server(e))
            }
            else -> {
                Either.Left(FoursquareFailure.Generic(e))
            }
        }
    }
}