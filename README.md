# Weather App

![Weather App Banner](app_banner.png)

## Overview

Weather App is an Android application that provides real-time weather information using modern architecture patterns and Jetpack architecture components. It follows the MVVM (Model-View-ViewModel) architecture along with the repository pattern for efficient data management. The app gracefully handles no internet conditions and incorporates local caching for a seamless user experience.

## Features

- **MVVM Architecture:** Utilizes a clean and scalable architecture pattern for better separation of concerns and maintainability.

- **Repository Pattern:** Implements a repository layer to abstract the data source and manage data access.

- **Jetpack Architecture Components:**
  - LiveData: Ensures data updates are observed and reflected in the UI.
  - ViewModel: Manages UI-related data and handles the communication between the UI and the underlying data model.
  - Room Database: Provides local caching of weather data for offline access.

- **Dependency Injection with Hilt:**
  - Utilizes Hilt for managing dependency injection, making the codebase more modular and testable.

- **Weather Information:**
  - Displays real-time weather information for the current location.
  - Shows temperature details for six major cities (New York, Singapore, Mumbai, Delhi, Sydney, Melbourne).

- **No Internet Handling:**
  - Gracefully handles scenarios where there is no internet connection.
  - Provides a user-friendly message to inform users about the lack of connectivity.


## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/WeatherApp.git
    ```

2. Open the project in Android Studio.

3. Build and run the application on an Android device or emulator.

## Dependencies

- Retrofit: For network operations
- Room Database: For local caching
- ViewModel & LiveData: Part of Android Jetpack for UI updates
- Other Jetpack components as required (e.g., Navigation)
- Build in electric eel version of android studio
