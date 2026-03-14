package com.example.kmptraining.kmp_session2.data.network

import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class RemoteDataSourceImpl : RemoteNewsDataSource {
    override fun fetchNews(): Flow<ResponseStatus<List<News>>> {
        return flow<ResponseStatus<List<News>>> {
            delay(3000)
            emit(
                ResponseStatus.Success(
                    listOf(
                        News(
                            id = 1,
                            title = "AI Breakthrough",
                            content = "A new AI model has achieved state-of-the-art performance in multiple benchmarks.",
                            imageUrl = "https://picsum.photos/400/300?random=1",
                            author = "John Doe",
                            category = "Technology",
                            readDuration = "5 min read"
                        ),
                        News(
                            id = 2,
                            title = "Startup Raises $20M",
                            content = "A tech startup has successfully raised $20 million in its latest funding round.",
                            imageUrl = "https://picsum.photos/400/300?random=2",
                            author = "Anna Lee",
                            category = "Business",
                            readDuration = "4 min read"
                        ),
                        News(
                            id = 3,
                            title = "Android Update Released",
                            content = "The latest Android version introduces performance improvements and new UI features.",
                            imageUrl = "https://picsum.photos/400/300?random=3",
                            author = "David Kim",
                            category = "Mobile",
                            readDuration = "3 min read"
                        ),
                        News(
                            id = 4,
                            title = "Kotlin Multiplatform Growing",
                            content = "More companies are adopting Kotlin Multiplatform for cross-platform development.",
                            imageUrl = "https://picsum.photos/400/300?random=4",
                            author = "Emma Brown",
                            category = "Programming",
                            readDuration = "6 min read"
                        ),
                        News(
                            id = 5,
                            title = "SpaceX Launch Success",
                            content = "A new satellite mission was launched successfully into orbit.",
                            imageUrl = "https://picsum.photos/400/300?random=5",
                            author = "Chris Green",
                            category = "Science",
                            readDuration = "4 min read"
                        ),
                        News(
                            id = 6,
                            title = "New Smartphone Announced",
                            content = "The latest flagship smartphone features an improved camera system.",
                            imageUrl = "https://picsum.photos/400/300?random=6",
                            author = "Sophia White",
                            category = "Gadgets",
                            readDuration = "3 min read"
                        ),
                        News(
                            id = 7,
                            title = "AI in Healthcare",
                            content = "Hospitals are increasingly using AI to improve diagnostics.",
                            imageUrl = "https://picsum.photos/400/300?random=7",
                            author = "Michael Scott",
                            category = "Health",
                            readDuration = "5 min read"
                        ),
                        News(
                            id = 8,
                            title = "Cybersecurity Alert",
                            content = "Experts warn about a new wave of phishing attacks.",
                            imageUrl = "https://picsum.photos/400/300?random=8",
                            author = "Olivia Taylor",
                            category = "Security",
                            readDuration = "4 min read"
                        ),
                        News(
                            id = 9,
                            title = "Electric Cars Trending",
                            content = "EV adoption continues to grow worldwide.",
                            imageUrl = "https://picsum.photos/400/300?random=9",
                            author = "Daniel Wilson",
                            category = "Automotive",
                            readDuration = "6 min read"
                        ),
                        News(
                            id = 10,
                            title = "Cloud Computing Growth",
                            content = "Cloud services continue expanding as businesses move online.",
                            imageUrl = "https://picsum.photos/400/300?random=10",
                            author = "Lucas Martinez",
                            category = "Cloud",
                            readDuration = "5 min read"
                        ),
                        News(
                            id = 11,
                            title = "Gaming Industry Booms",
                            content = "The global gaming market continues its rapid growth.",
                            imageUrl = "https://picsum.photos/400/300?random=11",
                            author = "Ethan Clark",
                            category = "Gaming",
                            readDuration = "4 min read"
                        ),
                        News(
                            id = 12,
                            title = "5G Expansion",
                            content = "Telecom companies expand their 5G infrastructure globally.",
                            imageUrl = "https://picsum.photos/400/300?random=12",
                            author = "Ava Johnson",
                            category = "Telecom",
                            readDuration = "3 min read"
                        )
                    )
                )
            )
        }.onStart { emit(ResponseStatus.Loading) }.flowOn(Dispatchers.IO).catch { error ->
            emit(ResponseStatus.Error(message = error.message ?: "Unknown Error"))
        }
    }
}