<h1 align="center">RandomUsersComposeAndroid</h1>

<p align="center">
  <a href="https://developer.android.com/jetpack/compose">
    <img src="https://img.shields.io/badge/compose-1.6.8-brightgreen" alt="Compose Version">
  </a>
  <a href="https://kotlinlang.org/docs/whatsnew18.html">
    <img src="https://img.shields.io/badge/kotlin-2.0.0-blue" alt="Kotlin Version">
  </a>
  <a href="https://docs.gradle.org/8.0.2/release-notes.html">
    <img src="https://img.shields.io/badge/gradle-8.4.1-blue" alt="Gradle">
  </a>
  <a href="https://android-arsenal.com/api?level=27">
    <img src="https://img.shields.io/badge/API-27%2B-blue" alt="API">
  </a>
  <a href="https://github.com/hivian/Android-Compose-MVVM/blob/master/LICENSE">
    <img src="https://img.shields.io/badge/License-MIT-green" alt="License">
  </a>
</p>


A simple Android & iOS demo app built with Kotlin and <a href="https://www.jetbrains.com/lp/compose-multiplatform">Compose Multiplatform</a> for sharing UIs. <br/>
Data fetched from https://randomuser.me api and saved to SQLDelight database.
 
This project is the Kotlin Multiplatform copy of this <a href="https://github.com/hivian/RandomUsersComposeAndroid">Android project</a>

## Preview
<p>
  <img src="https://github.com/hivian/RandomUsersComposeAndroid/blob/master/preview1.gif" width="270"/>
  <img src="https://github.com/hivian/RandomUsersComposeAndroid/blob/master/preview2.gif" width="270"/>
</p>

## Features

- [x] Offline mode
- [x] Pagination: infinite scroll
- [x] Specific error messages with retry action
- [x] Dark mode

## Tech stack

* [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) - Cross-platform mobile development framework
* [Compose Multiplatform](https://www.jetbrains.com/fr-fr/lp/compose-multiplatform) - UI sharing framework buit on top of Kotlin Multi Platform
* [Compose](https://developer.android.com/jetpack/compose) - Declarative and simplified way for UI development
* [Koin](https://insert-koin.io/docs/quickstart/android/) - Dependency injection
* [Compose Navigation](https://developer.android.com/develop/ui/compose/navigation?hl=fr) - Screen routing handler
* [Compose ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - UI related data holder, lifecycle aware
* [SQLDelight](https://cashapp.github.io/sqldelight/2.0.2/android_sqlite) - Local database
* [Ktor](https://ktor.io) - Networking client
* [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines) - Concurrency design pattern for asynchronous programming
* [Coil](https://coil-kt.github.io/coil/) - Image loading library

## Architecture

* SOLID principles
* MVVM clean architecture
* Modularization by feature and by layer

### Compose Multiplatform architecture

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Layers dependencies
```
presentation → domain ← data
```
  
### Global package structure

`/composeApp`
```                                               
com.hivian.randomusers.homefeature          # Main feature Module
├── di                                      # Dependency injection module
├── data                                    # Data layer
│   ├── mappers                             # DTO to domain models mapper
│   └── services                            # local & remote data source implementation
├── domain                                  # Domain layer
│   ├── models                              # Domain models
│   ├── services                            # services contracts
│   └── usecases                            # Use cases encapsulation for presentation layer
└── presentation                            # Presentation layer
    ├── home                                # Main screen & viewModel
    ├── detail                              # Detail screen & viewModel
    └── themes                              # Design system

com.hivian.randomusers.core                 # Core Module
├── di                                      # Dependency injection module
├── data                                    # Data sources
│   ├── models                              # Entities & DTO Models
│   ├── local                               # Local database client
│   │   ├── converters                      # Complex data serializer
│   │   └── dao                             # Data Access Object for Room
│   └── remote                              # Remote data client & data wrappers
├── domain                                  # Domain layer
│   ├── base                                # Base classes
│   ├── extensions                          # Kotlin extensions
│   ├── services                            # Core services contracts
│   └── usecases                            # Core usecases
└── presentation                            # Presentation layer
    ├── navigation                          # Navigation routes
    └── services                            # UI services implementation
```


## Download

Go to the [releases page](https://github.com/hivian/Android-Compose-MVVM/releases) to download the latest available apk.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
