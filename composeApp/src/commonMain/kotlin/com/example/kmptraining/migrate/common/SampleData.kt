package com.apero.composetraining.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.composetraining.session2.session2_bonus.StreamingHomeScreen
import com.example.kmptraining.migrate.common.Session
import com.example.kmptraining.migrate.common.SubSession
import com.example.kmptraining.migrate.session1.session1_1.GreetingCard
import com.example.kmptraining.migrate.session1.session1_2.ContactCard
import com.example.kmptraining.migrate.session1.session1_3.ProfileScreen
import com.example.kmptraining.migrate.session1.session1_4.SocialPostCard
import com.example.kmptraining.migrate.session1.session1_4.sampleSocialPostData
import com.example.kmptraining.migrate.session1.session1_5.StatsDashboard
import com.example.kmptraining.migrate.session1.session1_5.data.allPositiveStats
import com.example.kmptraining.migrate.session1.session1_6.GitHubProfileCard
import com.example.kmptraining.migrate.session1.session1_6.data.sampleProfile
import com.example.kmptraining.migrate.session2.session2_1.ProfileCardScreen
import com.example.kmptraining.migrate.session2.session2_2.MovieBrowserScreen
import com.example.kmptraining.migrate.session2.session2_3.DashboardScreen
import com.example.kmptraining.migrate.session2.session2_4.PricingTableScreen
import com.example.kmptraining.migrate.session2.session2_5.TagLayoutScreen
import com.example.kmptraining.migrate.session3.session3_1.InteractiveCounterScreen
import com.example.kmptraining.migrate.session3.session3_2.ShoppingCartScreen
import com.example.kmptraining.migrate.session3.session3_3.SearchFilterScreen
import com.example.kmptraining.migrate.session3.session3_4.MultiStepFormScreen
import com.example.kmptraining.migrate.session4.session4_1.WeatherCardScreen
import com.example.kmptraining.migrate.session4.session4_2.CustomThemeDetailScreen
import com.example.kmptraining.migrate.session4.session4_3.DesignSystemProductCard
import com.example.kmptraining.migrate.session4.session4_4.AnimatedListScreen
import com.example.kmptraining.migrate.session4.session4_5.StaggeredGalleryScreen
import com.example.kmptraining.migrate.session5.session5_1.TwoScreenFlowApp
import com.example.kmptraining.migrate.session5.session5_2.TabAppScreen
import com.example.kmptraining.migrate.session5.session5_3.ECommerceApp
import com.example.kmptraining.migrate.session5.session5_4.AuthTabApp
import com.example.kmptraining.migrate.session6.session6_1.AnimatedLikeButton
import com.example.kmptraining.migrate.session6.session6_2.ExpandableListScreen
import com.example.kmptraining.migrate.session6.session6_3.SwipeCardScreen
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.user_bio
import kmptraining.composeapp.generated.resources.user_name
import org.jetbrains.compose.resources.stringResource

/**
 * Dữ liệu mẫu dùng chung cho tất cả sessions.
 * Hardcoded — không cần network call.
 */

// === Models ===

data class Movie(
    val id: Int,
    val title: String,
    val year: Int,
    val rating: Float,
    val genre: String,
    val posterUrl: String = "https://picsum.photos/seed/movie$id/200/300",
    val description: String = "",
)

data class Contact(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val avatarUrl: String = "https://i.pravatar.cc/150?u=$id",
    val isFavorite: Boolean = false,
    val bio: String = "",
)

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val imageUrl: String = "https://picsum.photos/seed/product$id/200/200",
    val description: String = "",
    val isAiRecommended: Boolean = false,
    val isTrending: Boolean = false,
)

data class Todo(
    val id: Int,
    val title: String,
    val isDone: Boolean = false,
)

data class FaqItem(
    val id: Int,
    val question: String,
    val answer: String,
)

data class ProfileCard(
    val id: Int,
    val name: String,
    val age: Int,
    val bio: String,
    val imageUrl: String = "https://i.pravatar.cc/300?u=swipe$id",
)

data class Photo(
    val id: Int,
    val title: String,
    val category: String,
    val imageUrl: String = "https://picsum.photos/seed/photo$id/400/300",
    val heightDp: Int = (150..300).random(),
)

// === Sample Data ===

object SampleData {
    val movies =
        listOf(
            Movie(
                1,
                "The Shawshank Redemption",
                1994,
                9.3f,
                "Drama",
                description = "Two imprisoned men bond over a number of years."
            ),
            Movie(
                2,
                "The Godfather",
                1972,
                9.2f,
                "Crime",
                description = "The aging patriarch of an organized crime dynasty."
            ),
            Movie(
                3,
                "The Dark Knight",
                2008,
                9.0f,
                "Action",
                description = "Batman must accept one of the greatest psychological tests."
            ),
            Movie(
                4,
                "Pulp Fiction",
                1994,
                8.9f,
                "Crime",
                description = "The lives of two mob hitmen intertwine."
            ),
            Movie(
                5,
                "Forrest Gump",
                1994,
                8.8f,
                "Drama",
                description = "The life story of a slow-witted but kind-hearted man."
            ),
            Movie(
                6,
                "Inception",
                2010,
                8.8f,
                "Sci-Fi",
                description = "A thief who steals secrets through dream-sharing technology."
            ),
            Movie(
                7,
                "The Matrix",
                1999,
                8.7f,
                "Sci-Fi",
                description = "A hacker discovers the true nature of reality."
            ),
            Movie(
                8,
                "Interstellar",
                2014,
                8.7f,
                "Sci-Fi",
                description = "Explorers travel through a wormhole in space."
            ),
            Movie(
                9,
                "Parasite",
                2019,
                8.5f,
                "Thriller",
                description = "Greed and class discrimination threaten a symbiotic relationship."
            ),
            Movie(
                10,
                "Spirited Away",
                2001,
                8.6f,
                "Animation",
                description = "A girl wanders into a world of gods and spirits."
            ),
            Movie(
                11,
                "Avengers: Endgame",
                2019,
                8.4f,
                "Action",
                description = "The Avengers assemble once more to reverse Thanos' actions.",
            ),
            Movie(
                12,
                "Your Name",
                2016,
                8.4f,
                "Animation",
                description = "Two strangers find themselves linked in a bizarre way."
            ),
            Movie(
                13,
                "Joker",
                2019,
                8.4f,
                "Drama",
                description = "A mentally troubled comedian embarks on a downward spiral."
            ),
            Movie(
                14,
                "Whiplash",
                2014,
                8.5f,
                "Music",
                description = "A drummer enrolls at a cutthroat music conservatory."
            ),
            Movie(
                15,
                "Coco",
                2017,
                8.4f,
                "Animation",
                description = "A boy journeys to the Land of the Dead."
            ),
        )

    val contacts =
        listOf(
            Contact(
                1,
                "Nguyễn Văn An",
                "0901234567",
                "an.nguyen@email.com",
                isFavorite = true,
                bio = "Android Developer tại Apero"
            ),
            Contact(
                2,
                "Trần Thị Bình",
                "0912345678",
                "binh.tran@email.com",
                bio = "UI/UX Designer"
            ),
            Contact(
                3,
                "Lê Hoàng Cường",
                "0923456789",
                "cuong.le@email.com",
                isFavorite = true,
                bio = "Backend Engineer"
            ),
            Contact(
                4,
                "Phạm Minh Duy",
                "0934567890",
                "duy.pham@email.com",
                bio = "Product Manager"
            ),
            Contact(5, "Hoàng Thị Em", "0945678901", "em.hoang@email.com", bio = "QA Engineer"),
            Contact(
                6,
                "Ngô Đức Phú",
                "0956789012",
                "phu.ngo@email.com",
                isFavorite = true,
                bio = "iOS Developer"
            ),
            Contact(7, "Vũ Thị Giang", "0967890123", "giang.vu@email.com", bio = "Data Analyst"),
            Contact(8, "Đỗ Quang Hùng", "0978901234", "hung.do@email.com", bio = "DevOps Engineer"),
            Contact(9, "Bùi Thị Oanh", "0989012345", "oanh.bui@email.com", bio = "Scrum Master"),
            Contact(10, "Lý Văn Khánh", "0990123456", "khanh.ly@email.com", bio = "Tech Lead"),
        )

    val products =
        listOf(
            Product(
                1,
                "iPhone 16 Pro",
                999.0,
                "Electronics",
                description = "Latest Apple smartphone",
                isAiRecommended = true
            ),
            Product(
                2,
                "MacBook Air M3",
                1099.0,
                "Electronics",
                description = "Ultra-thin laptop",
                isTrending = true
            ),
            Product(
                3,
                "AirPods Pro 3",
                249.0,
                "Electronics",
                description = "Noise-cancelling earbuds"
            ),
            Product(
                4,
                "Nike Air Max",
                159.0,
                "Fashion",
                description = "Classic running shoes",
                isTrending = true
            ),
            Product(
                5,
                "Kindle Paperwhite",
                139.0,
                "Electronics",
                description = "E-reader with warm light"
            ),
            Product(
                6,
                "Lego Technic",
                89.0,
                "Toys",
                description = "Building set for adults",
                isAiRecommended = true
            ),
            Product(7, "Uniqlo T-Shirt", 19.0, "Fashion", description = "Basic cotton tee"),
            Product(
                8,
                "Sony WH-1000XM5",
                349.0,
                "Electronics",
                description = "Premium headphones",
                isAiRecommended = true
            ),
            Product(
                9,
                "Moleskine Notebook",
                25.0,
                "Stationery",
                description = "Classic ruled notebook"
            ),
            Product(
                10,
                "Anker PowerBank",
                45.0,
                "Electronics",
                description = "20000mAh portable charger"
            ),
        )

    val todos =
        (1..20).map { i ->
            Todo(
                id = i,
                title =
                    listOf(
                        "Học Jetpack Compose",
                        "Review pull request",
                        "Fix bug login screen",
                        "Viết unit test",
                        "Update README",
                        "Refactor ViewModel",
                        "Design new feature",
                        "Code review session",
                        "Deploy staging",
                        "Họp sprint planning",
                        "Cập nhật documentation",
                        "Tối ưu performance",
                        "Test trên nhiều devices",
                        "Viết release notes",
                        "Setup CI/CD",
                        "Học Kotlin coroutines",
                        "Implement dark mode",
                        "Add animations",
                        "Xử lý edge cases",
                        "Prepare demo cho khách hàng",
                    )[i - 1],
                isDone = i % 3 == 0,
            )
        }

    val faqItems =
        listOf(
            FaqItem(
                1,
                "Jetpack Compose là gì?",
                "Jetpack Compose là toolkit UI hiện đại của Android, sử dụng Kotlin để xây dựng giao diện theo cách khai báo (declarative). Thay vì dùng XML layout, bạn viết các hàm composable mô tả UI.",
            ),
            FaqItem(
                2,
                "Tại sao nên dùng Compose thay vì XML?",
                "Compose giảm boilerplate code, hỗ trợ preview real-time, dễ test hơn, và tích hợp tốt với Kotlin. Google đang chuyển hướng hoàn toàn sang Compose.",
            ),
            FaqItem(
                3,
                "remember và rememberSaveable khác gì nhau?",
                "remember giữ state qua recomposition nhưng mất khi configuration change (xoay màn hình). rememberSaveable giữ state qua cả configuration change.",
            ),
            FaqItem(
                4,
                "State hoisting là gì?",
                "State hoisting là pattern đưa state lên component cha, component con chỉ nhận state và callback. Giúp component con stateless, dễ test và reuse.",
            ),
            FaqItem(
                5,
                "LazyColumn khác gì Column?",
                "Column render tất cả items cùng lúc. LazyColumn chỉ render items đang hiển thị trên màn hình (giống RecyclerView). Dùng LazyColumn cho list dài.",
            ),
        )

    val profileCards =
        listOf(
            ProfileCard(1, "Minh Anh", 25, "Coffee lover ☕ | Traveler ✈️ | Cat person 🐱"),
            ProfileCard(2, "Hồng Nhung", 23, "Bookworm 📚 | Yoga enthusiast 🧘 | Foodie 🍜"),
            ProfileCard(3, "Đức Thắng", 27, "Gym rat 💪 | Music producer 🎵 | Dog dad 🐕"),
            ProfileCard(4, "Thu Hà", 24, "Photography 📸 | Hiking 🏔️ | Plant mom 🌿"),
            ProfileCard(5, "Quang Minh", 26, "Gamer 🎮 | Coder by day 💻 | Chef by night 👨‍🍳"),
        )

    val photos =
        listOf(
            Photo(1, "Sunset Beach", "Nature", heightDp = 200),
            Photo(2, "City Skyline", "City", heightDp = 250),
            Photo(3, "Mountain Peak", "Nature", heightDp = 180),
            Photo(4, "Street Market", "City", heightDp = 220),
            Photo(5, "Happy Family", "People", heightDp = 160),
            Photo(6, "Waterfall", "Nature", heightDp = 280),
            Photo(7, "Neon Signs", "City", heightDp = 190),
            Photo(8, "Portrait", "People", heightDp = 240),
            Photo(9, "Forest Path", "Nature", heightDp = 210),
            Photo(10, "Coffee Shop", "City", heightDp = 170),
            Photo(11, "Group Photo", "People", heightDp = 200),
            Photo(12, "Ocean Waves", "Nature", heightDp = 260),
        )

    val categories = listOf("Electronics", "Fashion", "Toys", "Stationery")
}

val sessions = listOf(
    Session(
        title = "Session 1",
        subSessions = listOf(
            SubSession(
                name = "Session 1.1",
                content = { GreetingCard() },
            ),
            SubSession(
                name = "Session 1.2",
                content = { ContactCard() },
            ),
            SubSession(
                name = "Session 1.3",
                content = { ProfileScreen() },
            ),
            SubSession(
                name = "Session 1.4",
                content = { SocialPostCard(socialPostData = sampleSocialPostData) },
            ),
            SubSession(
                name = "Session 1.5",
                content = { StatsDashboard(stats = allPositiveStats) },
            ),
            SubSession(
                name = "Session 1.6",
                content = { GitHubProfileCard(profile = sampleProfile) },
            ),
        )
    ),
    Session(
        title = "Session 2",
        subSessions = listOf(
            SubSession(
                name = "Session 2.1",
                content = { ProfileCardScreen() },
            ),
            SubSession(
                name = "Session 2.2",
                content = { MovieBrowserScreen() },
            ),
            SubSession(
                name = "Session 2.3",
                content = { DashboardScreen() },
            ),
            SubSession(
                name = "Session 2.4",
                content = { PricingTableScreen() },
            ),
            SubSession(
                name = "Session 2.5",
                content = { TagLayoutScreen() },
            ),
            SubSession(
                name = "Session 2 bonus",
                content = { StreamingHomeScreen() },
            ),
        )
    ),
    Session(
        title = "Session 3",
        subSessions = listOf(
            SubSession(
                name = "Session 3.1",
                content = { InteractiveCounterScreen() },
            ),
            SubSession(
                name = "Session 3.2",
                content = { ShoppingCartScreen() },
            ),
            SubSession(
                name = "Session 3.3",
                content = { SearchFilterScreen() },
            ),
            SubSession(
                name = "Session 3.4",
                content = { MultiStepFormScreen() },
            ),
        )
    ),
    Session(
        title = "Session 4",
        subSessions = listOf(
            SubSession(
                name = "Session 4.1",
                content = { WeatherCardScreen() },
            ),
            SubSession(
                name = "Session 4.2",
                content = { CustomThemeDetailScreen() },
            ),
            SubSession(
                name = "Session 4.3",
                content = { DesignSystemProductCard() },
            ),
            SubSession(
                name = "Session 4.4",
                content = { AnimatedListScreen() },
            ),
            SubSession(
                name = "Session 4.5",
                content = { StaggeredGalleryScreen() },
            ),
        )
    ),
    Session(
        title = "Session 5",
        subSessions = listOf(
            SubSession(
                name = "Session 5.1",
                content = { TwoScreenFlowApp() },
            ),
            SubSession(
                name = "Session 5.2",
                content = { TabAppScreen() },
            ),
            SubSession(
                name = "Session 5.3",
                content = { ECommerceApp() },
            ),
            SubSession(
                name = "Session 5.4",
                content = { AuthTabApp() },
            ),
        )
    ),
    Session(
        title = "Session 6",
        subSessions = listOf(
            SubSession(
                name = "Session 6.1",
                content = { AnimatedLikeButton() },
            ),
            SubSession(
                name = "Session 6.2",
                content = { ExpandableListScreen() },
            ),
            SubSession(
                name = "Session 6.3",
                content = { SwipeCardScreen() },
            ),
        )
    ),
)