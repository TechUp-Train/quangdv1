package com.example.kmptraining.migrate.session1.session1_6.data

data class GitHubProfile(
    val username: String,
    val displayName: String,
    val bio: String,
    val location: String,
    val repoCount: Int,
    val starCount: Int,
    val followerCount: Int,
    val languages: List<String>,
    val pinnedRepos: List<PinnedRepo>,
)

val sampleProfile =
    GitHubProfile(
        username = "nqmgaming",
        displayName = "Nguyễn Quang Minh",
        bio = "Android & Flutter Developer · Open Source enthusiast",
        location = "Hà Nội, Việt Nam 🇻🇳",
        repoCount = 39,
        starCount = 47,
        followerCount = 12,
        languages = listOf("Kotlin", "Dart", "TypeScript", "Python", "Rust", "Go"),
        pinnedRepos =
            listOf(
                PinnedRepo("shose-shop", "Online shoe shopping app", "Kotlin", 9),
                PinnedRepo("ANeko-Reborn", "Revived pet app with Compose", "Kotlin", 23),
            ),
    )
