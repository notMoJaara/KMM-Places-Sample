# KMM Places Sample Apps 

## foursquare.com/products/places/ API implementation with kotlin multi platform , coroutines and Android jetpack components, compose
This repo is a sample android/iOS app implementing places API and google maps with MVVM design pattern.
Using ViewModel, navigation component, lifecycle, compose, Ktor client, sqlDelight and Mockative for testing to display list of POIs (places of interest).
The Logic is following SOLID principles.

### TODO:
* support paging, so when observing POIs from local DB all of the data is fetched at once instead on need bases.
  **Solution**: Add pagination support to `localStorage` module.
* Add a layout to show when there is no internet connection and there is no data cached.
* The ui makeover.
* More  unit/UI test.
* Add display POIs icon.
* Add support for user current location.
* Add support for changing the center location manually.
* Group up markers that are near to each other inorder to avoid screen clutter.

### Modules Details
* androidApp: the presentation layer for the android app.
* buildSrc: Dependencies and build config.
* coreLogic: business logic for places API.
* iosApp: the presentation layer for the iOS app (not yet implemented).
* localStorage: app local data source.
* network: app remote data source.
* util: shared util classes and SecureProperties.


### Frameworks and Libraries
* Coroutines for async tasks.
* Flow to observe BD changes.
* Android Architecture components (Lifecycle, ViewModel, Navigation).
* Compose and google maps compose.
* Hilt - Dagger for dependency injection for the android app (core logic uses manual DI).
* [Ktor client](https://ktor.io/docs/create-client.html) for network calls.
* [sqlDelight](https://github.com/cashapp/sqldelight) local database.
* [Mockative](https://github.com/mockative/mockative) mocking for Kotlin/Native using Kotlin Symbol Processing (KSP).
* [kotlinx-serialization](https://github.com/Kotlin/kotlinx.serialization) Kotlin multiplatform serializer (used for json only).


### Compile
  Inorder to run the apps the following values should be added to `local.properties`
```aidl
    fourSquareKey="{fourSquareKey}"
    defLat="{default latitude}"
    defLong="{default longitude}"
    MAPS_API_KEY={google maps api key}
```

