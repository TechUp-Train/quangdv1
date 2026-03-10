package com.example.composetraining.session5.session5_3.data

import androidx.compose.ui.graphics.Color

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val categoryId: Int,
    val color: Color,
    val description: String,
)

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "Modern Desk Lamp",
        price = 120,
        categoryId = 1,
        color = Color(0xFF90CAF9),
        description = """
            Illuminate your workspace with this sleek and modern desk lamp.
            Features adjustable brightness levels and color temperatures.
            Designed with a minimal footprint to save desk space.
            Perfect for late-night study sessions or focused professional work.
            Built with energy-efficient LED technology for long-lasting use.
        """.trimIndent()
    ),
    Product(
        id = 2,
        name = "Ergonomic Chair",
        price = 450,
        categoryId = 1,
        color = Color(0xFFA5D6A7),
        description = """
            Experience ultimate comfort with our premium ergonomic office chair.
            Provides superior lumbar support and adjustable armrests.
            The breathable mesh back keeps you cool during long working hours.
            Features a high-density foam seat cushion for lasting durability.
            Designed to promote healthy posture and reduce back strain.
        """.trimIndent()
    ),
    Product(
        id = 3,
        name = "Wireless Headphones",
        price = 299,
        categoryId = 2,
        color = Color(0xFFFFCC80),
        description = """
            Immerse yourself in high-fidelity sound with these wireless headphones.
            Equipped with advanced noise-canceling technology for clear audio.
            Soft over-ear cushions provide comfort for hours of continuous listening.
            Long battery life ensures your music follows you throughout the day.
            Seamlessly connect to your favorite devices via stable Bluetooth.
        """.trimIndent()
    ),
    Product(
        id = 4,
        name = "Smart Watch Ultra",
        price = 799,
        categoryId = 2,
        color = Color(0xFFEF9A9A),
        description = """
            Stay connected and track your health with the Smart Watch Ultra.
            Features a vibrant Always-On retina display for easy reading.
            Advanced sensors monitor heart rate, sleep patterns, and daily activity.
            Water-resistant design makes it suitable for swimming and intense workouts.
            Receive notifications, calls, and messages directly on your wrist.
        """.trimIndent()
    ),
    Product(
        id = 5,
        name = "Bamboo Water Bottle",
        price = 35,
        categoryId = 3,
        color = Color(0xFFCE93D8),
        description = """
            Stay hydrated sustainably with this eco-friendly bamboo water bottle.
            Crafted from natural bamboo and high-quality stainless steel.
            Double-wall insulation keeps your drinks hot or cold for hours.
            Leak-proof lid design ensures no spills in your bag or backpack.
            A stylish and conscious choice for the environmentally friendly traveler.
        """.trimIndent()
    ),
    Product(
        id = 6,
        name = "Yoga Mat Eco",
        price = 65,
        categoryId = 3,
        color = Color(0xFF80CBC4),
        description = """
            Enhance your practice with this premium eco-friendly yoga mat.
            Made from non-toxic, biodegradable materials for a cleaner planet.
            The non-slip surface provides excellent grip even during sweaty sessions.
            Extra cushioning protects your joints during challenging poses.
            Lightweight and easy to carry with the included travel strap.
        """.trimIndent()
    ),
)
