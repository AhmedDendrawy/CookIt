# CookIt 🍳

A simple and modern Android application that allows users to explore meal categories and browse delicious meals. Built entirely with Jetpack Compose.

## 🚀 Technologies & Tools Used

*   **Kotlin**: The primary programming language.
*   **Jetpack Compose**: Android's modern toolkit for building native UI.
*   **Retrofit2**: Type-safe REST client used to fetch meal data from [TheMealDB API](https://www.themealdb.com/).
*   **Gson Converter**: Used with Retrofit to parse JSON responses into Kotlin models.
*   **Coil (coil-compose)**: An image loading library used to load category and meal thumbnails asynchronously.
*   **State Management & Configuration**: Direct state management using Compose `rememberSaveable` and `mutableStateOf` to seamlessly preserve UI state and fetched data during screen rotations (Landscape/Portrait).
*   **Postman**: Used for testing and exploring the API endpoints and responses prior to implementation.

## 📸 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/faa42256-ae70-4444-94c4-a2ea8d797b02" width="25%" alt="Categories Screen"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/0917362e-11f8-431f-a46f-9cf88c05a287" width="25%" alt="Meals Screen"/>
</p>

<br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/1a1d71a2-0440-4924-8057-7f194ea11229" width="40%" alt="Landscape Screen" />
</p>

## API Reference
Data is provided by the free API from [TheMealDB](https://www.themealdb.com/api.php).
