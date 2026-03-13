package com.example.kmptraining.kmp_session2.data.network

import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class RemoteDataSourceImpl : RemoteNewsDataSource {
    override fun fetchNews(): Flow<ResponseStatus<List<News>>> {
        return flow<ResponseStatus<List<News>>> {
            emit(
                ResponseStatus.Success(
                    listOf(
                        News(
                            id = 1,
                            title = "AI Is Transforming Mobile Development",
                            content = "Artificial Intelligence is rapidly changing how developers build mobile applications, enabling smarter automation and better user experiences.",
                            imageUrl = "https://picsum.photos/id/1015/600/400",
                            author = "John Carter"
                        ),
                        News(
                            id = 2,
                            title = "Kotlin Multiplatform Gains Popularity",
                            content = "Kotlin Multiplatform is becoming a popular choice for developers who want to share business logic between Android and iOS.",
                            imageUrl = "https://picsum.photos/id/1016/600/400",
                            author = "Emily Johnson"
                        ),
                        News(
                            id = 3,
                            title = "Jetpack Compose Simplifies UI Development",
                            content = "Jetpack Compose allows developers to create modern Android UIs with less boilerplate and improved performance.",
                            imageUrl = "https://picsum.photos/id/1018/600/400",
                            author = "Michael Brown"
                        ),
                        News(
                            id = 4,
                            title = "Cross-Platform Development Trends in 2026",
                            content = "More companies are adopting cross-platform technologies to reduce development time and maintenance costs.",
                            imageUrl = "https://picsum.photos/id/1020/600/400",
                            author = "Sophia Lee"
                        ),
                        News(
                            id = 5,
                            title = "The Rise of Developer Productivity Tools",
                            content = "Tools powered by AI are helping developers write code faster, debug efficiently, and improve overall productivity.",
                            imageUrl = "https://picsum.photos/id/1024/600/400",
                            author = "David Wilson"
                        ),
                        News(
                            id = 6,
                            title = "Modern UI/UX Trends for Mobile Apps",
                            content = "Minimalist design, smooth animations, and intuitive interactions are dominating modern mobile UI design.",
                            imageUrl = "https://picsum.photos/id/1027/600/400",
                            author = "Olivia Martinez"
                        ),
                        News(
                            id = 7,
                            title = "Cloud Services Power the Future of Apps",
                            content = "Cloud platforms provide scalable infrastructure that enables apps to serve millions of users globally.",
                            imageUrl = "https://picsum.photos/id/1031/600/400",
                            author = "Daniel Clark"
                        ),
                        News(
                            id = 8,
                            title = "Remote Work Changes the Tech Industry",
                            content = "Remote work continues to shape how development teams collaborate and build software worldwide.",
                            imageUrl = "https://picsum.photos/id/1035/600/400",
                            author = "Isabella Walker"
                        ),
                        News(
                            id = 9,
                            title = "Security Becomes a Top Priority for Apps",
                            content = "With increasing cyber threats, developers must implement stronger security practices and encryption methods.",
                            imageUrl = "https://picsum.photos/id/1040/600/400",
                            author = "James Hall"
                        ),
                        News(
                            id = 10,
                            title = "Open Source Communities Continue to Grow",
                            content = "Open source projects drive innovation by allowing developers from around the world to collaborate.",
                            imageUrl = "https://picsum.photos/id/1043/600/400",
                            author = "Ava Anderson"
                        ),
                        News(
                            id = 11,
                            title = "The Future of Mobile App Architecture",
                            content = "Architectures like MVVM and Clean Architecture are helping developers build scalable applications.",
                            imageUrl = "https://picsum.photos/id/1050/600/400",
                            author = "William Young"
                        ),
                        News(
                            id = 12,
                            title = "Why Developers Love Kotlin",
                            content = "Kotlin’s concise syntax and powerful features make it one of the most loved programming languages today.",
                            imageUrl = "https://picsum.photos/id/1057/600/400",
                            author = "Emma Thompson"
                        )
                    )
                )
            )
        }.onStart { emit(ResponseStatus.Loading) }.flowOn(Dispatchers.IO).catch { error ->
            emit(ResponseStatus.Error(message = error.message ?: "Unknown Error"))
        }
    }
}