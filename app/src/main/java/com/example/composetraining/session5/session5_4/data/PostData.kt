package com.example.composetraining.session5.session5_4.data

data class PostData(
    val id: Int,
    val userId: Int,
    val title: String,
    val content: String,
    val imageUrl: String? = null,
    val timestamp: String,
    val likes: Int = 0,
    val comments: Int = 0
)

val samplePosts = listOf(
    PostData(
        id = 1,
        userId = 1,
        title = "Modern Android Development",
        content = "Jetpack Compose is revolutionizing how we build Android UIs. It's declarative, concise, and powerful!",
        imageUrl = "https://picsum.photos/id/1/800/600",
        timestamp = "2 hours ago",
        likes = 124,
        comments = 18
    ),
    PostData(
        id = 2,
        userId = 2,
        title = "The Beauty of Kotlin",
        content = "Kotlin's null safety and extension functions make developer's life so much easier. What's your favorite feature?",
        imageUrl = "https://picsum.photos/id/10/800/600",
        timestamp = "5 hours ago",
        likes = 89,
        comments = 12
    ),
    PostData(
        id = 3,
        userId = 1,
        title = "Compose Navigation 3.0",
        content = "Exploring the new Navigation 3.0 API. It feels much more aligned with Compose's state-driven nature.",
        imageUrl = "https://picsum.photos/id/20/800/600",
        timestamp = "Yesterday",
        likes = 256,
        comments = 45
    ),
    PostData(
        id = 4,
        userId = 3,
        title = "Material 3 Design Systems",
        content = "Implementing dynamic color schemes with Material 3 provides a personalized experience for every user.",
        imageUrl = "https://picsum.photos/id/30/800/600",
        timestamp = "2 days ago",
        likes = 67,
        comments = 5
    ),
    PostData(
        id = 5,
        userId = 2,
        title = "Clean Architecture in 2024",
        content = "Is Clean Architecture still relevant? Let's discuss how to balance structure and simplicity.",
        imageUrl = "https://picsum.photos/id/40/800/600",
        timestamp = "3 days ago",
        likes = 156,
        comments = 23
    ),
    PostData(
        id = 6,
        userId = 1,
        title = "Mastering Coroutines",
        content = "Structured concurrency and Flow are essential for modern Android apps. Here's a deep dive into advanced patterns.",
        imageUrl = "https://picsum.photos/id/50/800/600",
        timestamp = "4 days ago",
        likes = 210,
        comments = 31
    ),
    PostData(
        id = 7,
        userId = 3,
        title = "Accessibility in Compose",
        content = "Building apps for everyone is a responsibility. Learn how to use Semantics effectively in Jetpack Compose.",
        imageUrl = "https://picsum.photos/id/60/800/600",
        timestamp = "5 days ago",
        likes = 45,
        comments = 8
    ),
    PostData(
        id = 8,
        userId = 2,
        title = "Performance Optimization",
        content = "Debugging recomposition and using stable types can significantly boost your app's performance.",
        imageUrl = "https://picsum.photos/id/70/800/600",
        timestamp = "1 week ago",
        likes = 178,
        comments = 15
    ),
    PostData(
        id = 9,
        userId = 1,
        title = "Testing Compose UIs",
        content = "Unit testing and UI testing in Compose are straightforward once you understand the test rule and finders.",
        imageUrl = "https://picsum.photos/id/80/800/600",
        timestamp = "1 week ago",
        likes = 112,
        comments = 10
    ),
    PostData(
        id = 10,
        userId = 3,
        title = "Modularization Strategies",
        content = "Breaking down a monolith into feature modules helps in faster builds and better code organization.",
        imageUrl = "https://picsum.photos/id/90/800/600",
        timestamp = "2 weeks ago",
        likes = 134,
        comments = 14
    ),
    PostData(
        id = 11,
        userId = 2,
        title = "KMP is the Future",
        content = "Kotlin Multiplatform is maturing fast. Share code across Android, iOS, and Web with ease.",
        imageUrl = "https://picsum.photos/id/100/800/600",
        timestamp = "2 weeks ago",
        likes = 342,
        comments = 56
    ),
    PostData(
        id = 12,
        userId = 1,
        title = "The Power of Hilt",
        content = "Dependency injection doesn't have to be complicated. Hilt simplifies the setup for Android apps.",
        imageUrl = "https://picsum.photos/id/110/800/600",
        timestamp = "3 weeks ago",
        likes = 95,
        comments = 12
    ),
    PostData(
        id = 13,
        userId = 3,
        title = "Dark Mode Best Practices",
        content = "Designing for dark mode isn't just about inverting colors. It's about readability and contrast.",
        imageUrl = "https://picsum.photos/id/120/800/600",
        timestamp = "3 weeks ago",
        likes = 82,
        comments = 6
    ),
    PostData(
        id = 14,
        userId = 2,
        title = "Retrofit vs Ktor Client",
        content = "Comparing the two most popular networking libraries for Android. Which one do you prefer?",
        imageUrl = "https://picsum.photos/id/130/800/600",
        timestamp = "1 month ago",
        likes = 167,
        comments = 29
    ),
    PostData(
        id = 15,
        userId = 1,
        title = "Room Database Migrations",
        content = "Managing database changes can be tricky. Here's a guide to handling migrations safely in Room.",
        imageUrl = "https://picsum.photos/id/140/800/600",
        timestamp = "1 month ago",
        likes = 76,
        comments = 9
    ),
    PostData(
        id = 16,
        userId = 3,
        title = "Custom Layouts in Compose",
        content = "Sometimes built-in layouts aren't enough. Learn how to create your own custom Layout composables.",
        imageUrl = "https://picsum.photos/id/150/800/600",
        timestamp = "1 month ago",
        likes = 128,
        comments = 22
    ),
    PostData(
        id = 17,
        userId = 2,
        title = "Effective Paging 3",
        content = "Handle large datasets efficiently with Paging 3. It integrates perfectly with Compose and Flow.",
        imageUrl = "https://picsum.photos/id/160/800/600",
        timestamp = "2 months ago",
        likes = 145,
        comments = 17
    ),
    PostData(
        id = 18,
        userId = 1,
        title = "State Hoisting Patterns",
        content = "Proper state management is key to building maintainable and testable Compose applications.",
        imageUrl = "https://picsum.photos/id/170/800/600",
        timestamp = "2 months ago",
        likes = 189,
        comments = 24
    ),
    PostData(
        id = 19,
        userId = 3,
        title = "Firebase Auth Integration",
        content = "Secure your app with Firebase Authentication. A quick guide to setting up Google and Email login.",
        imageUrl = "https://picsum.photos/id/180/800/600",
        timestamp = "3 months ago",
        likes = 104,
        comments = 13
    ),
    PostData(
        id = 20,
        userId = 2,
        title = "Animations in Compose",
        content = "Bring your app to life with beautiful animations. From simple transitions to complex gesture-driven ones.",
        imageUrl = "https://picsum.photos/id/190/800/600",
        timestamp = "3 months ago",
        likes = 231,
        comments = 38
    )
)