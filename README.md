# Using Kotlin Coroutine and Retrofit Together

This repository demonstrates how to use Kotlin Coroutines with Retrofit for efficient asynchronous operations in Android development.

## Contents
1. **Async Coroutine** - Understanding asynchronous operations with coroutines.
2. **Coroutine Job Usage** - Managing coroutines with `Job`.
3. **Coroutine Scopes and Dispatchers** - Using different coroutine scopes and dispatchers for threading control.
4. **runBlocking** - Blocking coroutine execution and its use cases.
5. **Retrofit and Coroutine Correlation** - Integrating Retrofit with coroutines for network operations.
6. **Coroutine Exception Handling** - Handling errors properly within coroutines.
7. **Suspend Coroutine Functions** - Writing and using `suspend` functions for coroutine-based operations.

### Dependencies
#### Using Gradle Version Catalog
If you are using **Gradle Version Catalog**, add the following to your `libs.versions.toml(Version Catalog)` file:

```toml
[versions]
# Coroutine
coroutine-ver = "1.10.1"
coroutine-core-ver = "1.10.1"
# Retrofit & RxJava
retrofit-ver = "2.11.0"
rxjava-ver = "2.2.21"
rxjava3-ver = "3.1.8"
rxandroid-ver = "3.0.2"
lifecycle-ktx-ver = "2.4.0"

[libraries]
# Coroutine
coroutine = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "coroutine-ver" }
coroutine-core = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version.ref = "coroutine-core-ver" }
lifecycle-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycle-ktx-ver" }

# Retrofit
retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit-ver" }
converter-gson = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "retrofit-ver" }
adapter-rxjava3 = { group = "com.squareup.retrofit2", name = "adapter-rxjava3", version.ref = "retrofit-ver" }

# RxJava
rxjava3 = { group = "io.reactivex.rxjava3", name = "rxjava", version.ref = "rxjava3-ver" }
rxandroid = { group = "io.reactivex.rxjava3", name = "rxandroid", version.ref = "rxandroid-ver" }
```

#### Without Version Catalog
If you are not using **Gradle Version Catalog**, add these dependencies to your `build.gradle.kts(Module :app)` file:

```kotlin
dependencies {
    //Coroutine
    implementation(libs.coroutine)
    implementation(libs.coroutine.core)
    implementation(libs.lifecycle.ktx)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava3)

    //RxJava
    implementation(libs.rxjava3)
    implementation(libs.rxandroid)
}
```
