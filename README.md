Rick and Morty Demo App
=======================
A simple example app demonstrating use of the Rick and Morty REST API.

### Architecture
Overall, the structure of this project tends to reflect a platform-agnostic
kotlin multiplatform setup. The idea is that by not directly using Android-specific
architectural components, the core functionality of this app can easily be
adapted to iOS and JS.

[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
is the main inspiration behind the layered setup of this app.
Currently, there are 5 layers:
```
UI -> View -> Presenter -> Repository -> Data Source
```

- The UI layer is the only Android specific layer and contains the actual Android
  implementation of a generic View.
- The View layer contains mostly interfaces
that can be implemented on any number of platforms from Android, iOS, Web, etc.
- The Presenter layer controls the UI layer through the abstraction of the View layer
  and handles domain logic and async data loading.
- The Repository adds caching, persistence to the data source so that data is
  not reloaded from the server everytime a presenter is recreated.
- Finally, the Data Source layer provides access to the raw data. In this case,
  a REST API.