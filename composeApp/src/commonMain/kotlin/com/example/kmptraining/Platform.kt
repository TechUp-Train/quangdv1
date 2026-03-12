package com.example.kmptraining

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform