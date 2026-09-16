# CookIt 🍳

A simple and modern Android application that allows users to explore meal categories and browse delicious meals. Built entirely with Jetpack Compose.

## 🚀 Technologies & Tools Used

*   **Kotlin**: The primary programming language.
*   **Jetpack Compose**: Android's modern toolkit for building native UI.
*   **Retrofit2**: Type-safe REST client used to fetch meal data from [TheMealDB API](https://www.themealdb.com/).
*   **Gson Converter**: Used with Retrofit to parse JSON responses into Kotlin models.
*   **Coil (coil-compose)**: An image loading library backed by Kotlin Coroutines to load category and meal thumbnails asynchronously.
*   **State Management**: Direct state management using Compose `remember` and `mutableStateOf` (Simple and clean UI without ViewModel).
*   **Postman**: Used for testing and exploring the API endpoints and responses prior to implementation.

## 📸 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/faa42256-ae70-4444-94c4-a2ea8d797b02" width="30%" alt="Categories Screen"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/0917362e-11f8-431f-a46f-9cf88c05a287" width="30%" alt="Meals Screen"/>
</p>

## API Reference
Data is provided by the free API from [TheMealDB](https://www.themealdb.com/api.php).
