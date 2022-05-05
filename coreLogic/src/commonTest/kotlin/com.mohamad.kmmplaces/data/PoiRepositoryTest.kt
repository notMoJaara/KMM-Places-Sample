package com.mohamad.kmmplaces.data

import com.mohamad.kmmplaces.NetworkError
import com.mohamad.kmmplaces.di.MapperProvider
import com.mohamad.kmmplaces.localStorage.dao.PoiDAO
import com.mohamad.kmmplaces.network.foursquare.FoursquareFailure
import com.mohamad.kmmplaces.network.foursquare.model.PlacesListDTO
import com.mohamad.kmmplaces.network.foursquare.model.ResultDTO
import com.mohamad.kmmplaces.network.foursquare.nearby.Location
import com.mohamad.kmmplaces.network.foursquare.nearby.NearbyApi
import com.mohamad.kmmplaces.util.Either
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class PoiRepositoryTest {

    @Mock
    private val nearbyApi = mock(classOf<NearbyApi>())

    @Mock
    private val poiDAO = mock(classOf<PoiDAO>())

    private lateinit var poiRepository: PoiRepository


    @BeforeTest
    fun setup() {
        poiRepository = PoiRepositoryImpl(nearbyApi, poiDAO)
    }


    @Test
    fun givenBackEndResponseSuccess_whenFetchFromRemote_thenSuccessIsPropagated() = runTest {
        val expected = Either.Right(TEST_POI_LIST)
        val param = NearbyApi.NearbyParam(Location("1.2", "1.4"), limit = 55)
        given(nearbyApi).coroutine { findNearbyPlaces(param) }.then { Either.Right(TEST_RESULT_DTO) }

        poiRepository.fetchFromRemote("1.2", "1.4", limit = 55).let { actual ->
            assertIs<Either.Right<List<Poi>>>(actual)
            assertEquals(expected.value, actual.value)
        }

        verify(nearbyApi).suspendFunction(nearbyApi::findNearbyPlaces).with(anything()).wasInvoked(exactly = once)
    }

    @Test
    fun givenBackEndResponseError_whenFetchFromRemote_thenErrorIsPropagated() = runTest {
        val expected = Either.Left(NetworkError.Generic(NullPointerException()))
        val param = NearbyApi.NearbyParam(Location("1.2", "1.4"), limit = 55)
        given(nearbyApi).coroutine { findNearbyPlaces(param) }.then { Either.Left(FoursquareFailure.Generic(NullPointerException())) }

        poiRepository.fetchFromRemote("1.2", "1.4", limit = 55).let { actual ->
            assertIs<Either.Left<NetworkError.Generic>>(actual)
        }
    }

    companion object {

        // TODO: move stubs to testUtil module
        val TEST_POI_LIST = listOf<Poi>(
            Poi(
                "5b1e7ed0c0af570039c4a529",
                "Ethereum Dev",
                Location(52.500309, 13.425039),
                5,
                Address(
                    "Oranienstraße 6",
                    "DE",
                    "Oranienstraße 6, 10997 Berlin",
                    "Berlin",
                    "10997",
                    "Berlin",
                )
            ),
            Poi(
                "4d5105e74015b1f725f96bcf",
                "Kaffeeladen Görlitzer Bahnhof Café",
                Location(52.49978, 13.4271),
                124,
                Address(
                    "Manteuffelstraße 87",
                    "DE",
                    "Manteuffelstraße 87, 10997 Berlin",
                    "Berlin",
                    "10997",
                    "Berlin",
                )
            ),

            )
        val TEST_RESULT_DTO: PlacesListDTO = Json {
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
            coerceInputValues = true
        }.decodeFromString(
            """{
  "results": [
    {
      "fsq_id": "5b1e7ed0c0af570039c4a529",
      "categories": [
        {
          "id": 11167,
          "name": "Technology Business",
          "icon": {
            "prefix": "https://ss3.4sqi.net/img/categories_v2/shops/technology_",
            "suffix": ".png"
          }
        }
      ],
      "chains": [],
      "distance": 5,
      "geocodes": {
        "main": {
          "latitude": 52.500309,
          "longitude": 13.425039
        },
        "roof": {
          "latitude": 52.500309,
          "longitude": 13.425039
        }
      },
      "link": "/v3/places/5b1e7ed0c0af570039c4a529",
      "location": {
        "address": "Oranienstraße 6",
        "country": "DE",
        "cross_street": "",
        "formatted_address": "Oranienstraße 6, 10997 Berlin",
        "locality": "Berlin",
        "postcode": "10997",
        "region": "Berlin"
      },
      "name": "Ethereum Dev",
      "related_places": {},
      "timezone": "Europe/Berlin"
    },
    {
      "fsq_id": "4d5105e74015b1f725f96bcf",
      "categories": [
        {
          "id": 13034,
          "name": "Café",
          "icon": {
            "prefix": "https://ss3.4sqi.net/img/categories_v2/food/cafe_",
            "suffix": ".png"
          }
        },
        {
          "id": 13035,
          "name": "Coffee Shop",
          "icon": {
            "prefix": "https://ss3.4sqi.net/img/categories_v2/food/coffeeshop_",
            "suffix": ".png"
          }
        },
        {
          "id": 13065,
          "name": "Restaurant",
          "icon": {
            "prefix": "https://ss3.4sqi.net/img/categories_v2/food/default_",
            "suffix": ".png"
          }
        }
      ],
      "chains": [],
      "distance": 124,
      "geocodes": {
        "main": {
          "latitude": 52.49978,
          "longitude": 13.4271
        },
        "roof": {
          "latitude": 52.49978,
          "longitude": 13.4271
        }
      },
      "link": "/v3/places/4d5105e74015b1f725f96bcf",
      "location": {
        "address": "Manteuffelstraße 87",
        "country": "DE",
        "formatted_address": "Manteuffelstraße 87, 10997 Berlin",
        "locality": "Berlin",
        "postcode": "10997",
        "region": "Berlin"
      },
      "name": "Kaffeeladen Görlitzer Bahnhof Café",
      "related_places": {},
      "timezone": "Europe/Berlin"
    }
  ]
}"""
        )
    }

}