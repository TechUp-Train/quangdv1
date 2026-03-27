package com.example.techup_miniproject_quangdv1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
