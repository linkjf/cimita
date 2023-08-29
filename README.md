
# Climita

Climita is a weather forecasting Android app that provides accurate weather information from [WeatherAPI](https://www.weatherapi.com/). It follows modern Android development best practices, including Clean Architecture, MVVM, Jetpack Compose, Hilt, Glide, and Retrofit.

## Features

- **Weather Forecast:** Get the latest weather forecast for your location.
- **Location Search:** Look for your favorite location with an autocomplete field.

## Getting Started

To build and run Climita on your local machine, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/climita.git
   ```

2. **Open with Android Studio:**

   Open Android Studio and choose "Open an existing Android Studio project." Select the cloned directory (climita) and click "OK."

3. **API Key:**

    - Obtain a free API key from [WeatherAPI](https://www.weatherapi.com/).
    - Create a `local.properties` file in the project root directory.
    - Add your API key to the `local.properties` file as follows:

      ```properties
      WEATHER_API_KEY=YOUR_API_KEY_HERE
      ```

4. **Build and Run:**

   Build and run the Climita app on your Android emulator or physical device.

## Tech Stack

- **Clean Architecture:** Separation of concerns and maintainability.
- **MVVM:** Model-View-ViewModel for organized UI logic.
- **Jetpack Compose:** Modern Android UI toolkit for a declarative UI.
- **Hilt:** Dependency injection library for efficient and scalable DI.
- **Glide:** Image loading and caching for smooth image display.
- **Retrofit:** HTTP client for making network requests.

# Credits

- Design by [Carlos Loaiza]()
- Design on [Figma](https://www.figma.com/file/B41LYYd8YbrnHtrNUx2pIO?type=design).

## License

This project is licensed under the Apache 2.0 licence - see the [LICENSE](LICENSE) file for details.

## Contact

Have questions or suggestions? Feel free to [open an issue](https://github.com/linkjf/cimita/issues) or contact us at [link.josef@gmail.com](mailto:link.josef@gmail.com).

---
