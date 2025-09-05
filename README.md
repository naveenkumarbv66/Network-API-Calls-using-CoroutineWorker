Network/API calls using CoroutineWorker, ViewModel, Repository.
==========================

Android demo showcasing GET and POST network operations using MVVM, Repository, Retrofit, Coroutines, and WorkManager with Jetpack Compose UI.

Features
--
- Two buttons on `MainActivity` for GET and POST
- Navigates to dedicated screens: `GetActivity` and `PostActivity`
- GET: Fetches a sample post and displays title/body
- POST: Creates a sample post and displays the response

Architecture
--
- UI: Jetpack Compose in `MainActivity`, `GetActivity`, `PostActivity`
- ViewModel: `GetViewModel`, `PostViewModel`
- Workers: `GetPostWorker`, `CreatePostWorker`
- Repository: `NetworkRepository`
- Network: `RetrofitClient`, `ApiService`

Endpoints (Demo)
--
- Base URL: `https://jsonplaceholder.typicode.com/`
- GET: `GET /posts/1`
- POST: `POST /posts`

Requirements
--
- Android Studio Giraffe/Koala+
- JDK 11
- Min SDK 24, Target SDK 36

How to Run
--
1. Open the project in Android Studio.
2. Sync Gradle.
3. Run the app on a device/emulator with internet access.
4. In the app: tap "GET Request" or "POST Request".

Notable Files
--
- `app/src/main/java/com/naveen/networkcoroutines/MainActivity.kt`
- `app/src/main/java/com/naveen/networkcoroutines/GetActivity.kt`
- `app/src/main/java/com/naveen/networkcoroutines/PostActivity.kt`
- `app/src/main/java/com/naveen/networkcoroutines/GetViewModel.kt`
- `app/src/main/java/com/naveen/networkcoroutines/PostViewModel.kt`
- `app/src/main/java/com/naveen/networkcoroutines/repository/NetworkRepository.kt`
- `app/src/main/java/com/naveen/networkcoroutines/workers/GetPostWorker.kt`
- `app/src/main/java/com/naveen/networkcoroutines/workers/CreatePostWorker.kt`
- `app/src/main/java/com/naveen/networkcoroutines/network/ApiService.kt`
- `app/src/main/java/com/naveen/networkcoroutines/network/RetrofitClient.kt`

Notes
--
- Internet permission is declared in the manifest.
- WorkManager is used to perform network work in the background.
- Logging interceptor is enabled for network call inspection in Logcat.

