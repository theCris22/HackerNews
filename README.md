# 📰 Hacker News App

Android application that consumes the **Hacker News API** to display the latest news related to Android development. Built with **MVVM**, **Room Database**, and **StateFlow** for efficient state management.

---

## 📌 Features
- 🔹 Fetches **Android news** from the **Hacker News API**.
- 🔹 Uses **Room Database** to store articles locally.
- 🔹 Implements **MVVM** architecture for clean and scalable code.
- 🔹 Uses **StateFlow** for reactive state management.
- 🔹 Dependency injection with **Hilt**.
- 🔹 Handles **navigation** using the Jetpack Navigation Component.
- 🔹 API consumption with **Retrofit** and **Gson**.
- 🔹 Logs API requests using **Interceptor**.
- 🔹 **Swipe to delete** functionality in **RecyclerView**.
- 🔹 Uses **ViewBinding** for UI interactions.
- 🔹 **SwipeRefreshLayout** to refresh news dynamically.

---

## 📱 Technologies and Tools Used

| Technology             | Purpose                                     |
|------------------------|---------------------------------------------|
| **Kotlin**             | Main programming language                  |
| **MVVM**               | Architecture pattern                        |
| **StateFlow**          | Reactive state management                   |
| **Hilt**               | Dependency injection                        |
| **Navigation**         | Handles screen transitions                 |
| **Retrofit**           | API consumption                             |
| **Gson**               | JSON parsing                               |
| **LoggingInterceptor** | Logs API requests                          |
| **Room**               | Local database storage                     |
| **ViewBinding**        | UI interaction without findViewById        |
| **SwipeRefreshLayout** | Pull-to-refresh feature                    |

---

## 🏗 Dependency Injection (Hilt)
This app uses **Hilt** for dependency injection.

- **NetworkModule** → Provides Retrofit & API services.
- **DatabaseModule** → Provides Room database & DAOs.
- **UseCases** → Injected into ViewModels for business logic.

---

## 🔒 API Configuration
The base API URL is configured in **build.gradle** as follows:

```gradle
debug {
    buildConfigField(
        type = "String",
        name = "HACKER_NEWS_BASE_URL",
        value = "\"https://hn.algolia.com/api/v1/\""
    )
}
```

---

## 🚀 Installation and Setup

### 1️⃣ Clone the Repository

git clone https://github.com/theCris22/HackerNews.git
cd HackerNews


### 2️⃣ Open in Android Studio
- Open the project in **Android Studio Flamingo+**.
- Sync Gradle and install dependencies.

### 3️⃣ Run the Project
- Build and run on an emulator or physical device.

---

## 🏗 Architecture
The project follows the **MVVM architecture**, with the following structure:

```
📂 data       → Data sources (API, Room, DAOs)
📂 di         → Dependency injection modules
📂 domain     → Business logic and use cases
📂 ui         → ViewModels and UI components
```

---

## 📡 API Used
This app consumes:
- **[Hacker News API](https://hn.algolia.com/api)** → Fetches Android-related news articles.

---

## 👨‍💻 Developer
**Cristian Navarro González**  
✉️ Email: ingnavarrogonzalez@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/cristian-navarro-gonzalez-97b62213b/)  

---
