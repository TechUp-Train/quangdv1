package com.example.composetraining.session5.session5_4.data

data class UserData (
    val id: Int,
    val name: String,
    val username: String,
    val password: String = "123456",
    val email: String,
    val address: String,
    val bio: String
)

val registeredUsers = listOf (
    UserData(
        id = 1,
        name = "Quang Doan",
        username = "quangdv",
        email = "quang.doan@example.com",
        address = "Hanoi, Vietnam",
        bio = "Android Developer | Jetpack Compose Enthusiast"
    ),
    UserData(
        id = 2,
        name = "Jane Smith",
        username = "janesmith",
        email = "jane.smith@example.com",
        address = "New York, USA",
        bio = "UI/UX Designer | Passionate about minimalist design"
    ),
    UserData(
        id = 3,
        name = "Alex Johnson",
        username = "alexj",
        email = "alex.j@example.com",
        address = "London, UK",
        bio = "Full-stack Engineer | Tech Explorer | Coffee Lover"
    )
)