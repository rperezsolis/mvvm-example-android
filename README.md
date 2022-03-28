# mvvm-example-android
Test project for implementing MVVM architecture pattern, Repository pattern, Dependency Injection, and Testing.

This app consist of a simple movie catalog. The movies info is powered by [The Movie DB](https://www.themoviedb.org/ "The Movie DB"). 
- In the presentation layer the app makes use of [jetpack compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAjwuYWSBhByEiwAKd_n_mxSF-rgO8IUaVphtU10T8wcXJmSQS0mP_XEh3laTIQpmTBn4vALPhoC5KQQAvD_BwE&gclsrc=aw.ds "jetpack compose") for building the UI. For state management the app relies on [viewmodels](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjwuYWSBhByEiwAKd_n_v8jJMT4xNOCWIX5KiRpWrp-f-kSm-HnZEOJfWXl0SnwBvZGa8dHZhoCGcoQAvD_BwE&gclsrc=aw.ds "viewmodels"), making use of [state flows](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow "state flows").
- In order to keep things clean, the data layer is separated in two sublayers, the repository, and the datasource. For fetching the movies info from the network into the datasource we make use of [retrofit](https://square.github.io/retrofit/ "retrofit"), since it allows us to define the datasource components as interfaces, which we can pass in into our repository components making use of dependency injection, in that way the repository does not depend on a specific datasource implementation. 
- In the domain layer, we define our data classes. For parsing the api json response in our data classes we use the [retrofit's moshi converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi "retrofit's moshi converter").
- For dependency injection, we use [koin](https://insert-koin.io/ "koin"), a lightweight DI library for kotlin.

