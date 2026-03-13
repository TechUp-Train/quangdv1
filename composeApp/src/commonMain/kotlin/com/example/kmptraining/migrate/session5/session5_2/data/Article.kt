package com.example.kmptraining.migrate.session5.session5_2.data

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Int,
    val title: String,
    val summary: String,
    val content: String,
    val author: String,
    val date: String
)

val sampleArticles = listOf(
    Article(
        id = 1,
        title = "Modern Android Development with Jetpack Compose",
        summary = "Learn how to build beautiful native UIs with declarative components.",
        content = "Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.",
        author = "Jane Doe",
        date = "Oct 24, 2024"
    ),
    Article(
        id = 2,
        title = "Mastering Kotlin Coroutines",
        summary = "Understand the power of asynchronous programming in Kotlin.",
        content = "Coroutines are a design pattern that you can use on Android to simplify code that executes asynchronously. On Android, coroutines help to manage long-running tasks that might otherwise block the main thread and cause your app to become unresponsive.",
        author = "John Smith",
        date = "Oct 22, 2024"
    ),
    Article(
        id = 3,
        title = "Exploring Navigation 3",
        summary = "A deep dive into the new Navigation 3 library for Compose.",
        content = "Navigation 3 is the latest iteration of the Android Navigation library, designed from the ground up to be more flexible, type-safe, and easier to use with Jetpack Compose and other UI frameworks.",
        author = "Alice Johnson",
        date = "Oct 20, 2024"
    ),
    Article(
        id = 4,
        title = "Material Design 3 in Compose",
        summary = "Implementing the latest design trends in your Android app.",
        content = "Material Design 3 (also known as Material You) is the next evolution of Material Design. It includes updated theming, components, and Material You personalization features like dynamic color.",
        author = "Bob Wilson",
        date = "Oct 18, 2024"
    ),
    Article(
        id = 5,
        title = "Clean Architecture for Android",
        summary = "Best practices for structuring your Android projects for maintainability.",
        content = "Clean Architecture is a software design philosophy that separates the elements of a design into ring-like levels. The main goal of Clean Architecture is to provide a way to organize code so that it is encapsulated, kept separate from the UI and database, and easy to test.",
        author = "Charlie Brown",
        date = "Oct 15, 2024"
    )
)
