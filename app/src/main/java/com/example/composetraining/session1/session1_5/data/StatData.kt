package com.example.composetraining.session1.session1_5.data

// ─── Data Model ───────────────────────────────────────────────────────────────

data class StatData(
    val label: String,
    val value: String,
    val percentage: String,
    val isPositive: Boolean,
    val emoji: String = "📊",
)

// ─── Sample Data ──────────────────────────────────────────────────────────────

val allPositiveStats =
    listOf(
        StatData("Downloads", "12,450", "↑ +23%", true, "📱"),
        StatData("Rating", "4.7 / 5.0", "↑ +0.2", true, "⭐"),
        StatData("Revenue", "\$2,840", "↑ +12%", true, "💰"),
        StatData("Active Users", "8,920", "↑ +5%", true, "👥"),
    )

val mixedStats =
    listOf(
        StatData("Downloads", "12,450", "↑ +23%", true, "📱"),
        StatData("Rating", "4.6 / 5.0", "↓ -0.1", false, "⭐"),
        StatData("Revenue", "\$2,840", "↑ +12%", true, "💰"),
        StatData("Crash Rate", "0.8%", "↑ +0.3%", false, "💥"),
    )
