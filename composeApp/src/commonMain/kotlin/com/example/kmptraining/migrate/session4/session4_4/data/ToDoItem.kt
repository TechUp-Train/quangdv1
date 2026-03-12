package com.example.kmptraining.migrate.session4.session4_4.data

// ─── Data Model ──────────────────────────────────────────────────────────────

data class TodoItem(
    val id: Int,
    val title: String,
    val isDone: Boolean = false,
)

val todoList = listOf(
    TodoItem(1, "Mua thực phẩm cho tuần mới", isDone = true),
    TodoItem(2, "Hoàn thành bài tập Compose Session 4", isDone = false),
    TodoItem(3, "Gửi email báo cáo công việc", isDone = false),
    TodoItem(4, "Đi tập gym buổi chiều", isDone = true),
    TodoItem(5, "Đọc 20 trang sách Clean Code", isDone = false),
    TodoItem(6, "Hẹn bác sĩ nha khoa lúc 3h", isDone = false),
    TodoItem(7, "Thanh toán hóa đơn tiền điện", isDone = true),
    TodoItem(8, "Chuẩn bị slide cho buổi thuyết trình", isDone = false),
    TodoItem(9, "Tưới cây ngoài ban công", isDone = false),
    TodoItem(10, "Học từ vựng tiếng Anh mới", isDone = false)
)