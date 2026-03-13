package com.example.kmptraining.migrate.session4.session4_4

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session4.session4_4.data.TodoItem
import com.example.kmptraining.migrate.session4.session4_4.data.todoList
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import com.example.kmptraining.migrate.session4.session4_4.component.SectionHeader
import com.example.kmptraining.migrate.session4.session4_4.component.TodoRow
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐⭐⭐ BÀI TẬP NÂNG CAO BUỔI 4: Animated Todo List
 *
 * Mô tả: LazyColumn với animations khi thêm/xóa/reorder items
 *
 * ┌───────────────────────────────────────┐
 * │ Animated Todo List                    │
 * ├───────────────────────────────────────┤
 * │ ── Active Tasks ─────────────────── │  ← stickyHeader
 * │ □ Design mockup          [↑][↓][🗑] │
 * │ □ Code review            [↑][↓][🗑] │
 * ├───────────────────────────────────────┤
 * │ ── Completed Tasks ─────────────── │  ← stickyHeader
 * │ ✓ Write tests            [↑][↓][🗑] │
 * └───────────────────────────────────────┘
 *                                  [+ FAB]  ← ẩn khi scroll xuống
 *
 * Key concepts:
 * - animateItem(): Compose 1.7+ API, tự động animate placement khi list thay đổi
 * - stickyHeader {}: Header dính trên cùng khi scroll qua section
 * - rememberLazyListState() + derivedStateOf: track scroll direction → FAB show/hide
 */

// ─── Main Screen ──────────────────────────────────────────────────────────────
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatedListScreen(modifier: Modifier = Modifier) {
    // 1. State setup:
    //    - var todos by remember { mutableStateOf(listOf(...initial items...)) }
    //    - var newTodoText by remember { mutableStateOf("") }
    //    - val listState = rememberLazyListState()
    //    - val coroutineScope = rememberCoroutineScope()
    //    - var nextId by remember { mutableStateOf(todos.size + 1) }
    //
    // 2. derivedStateOf cho FAB visibility:
    //    val showFab by remember {
    //        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    //    }
    //    GỢI Ý: Tại sao dùng derivedStateOf thay vì đọc trực tiếp?
    //    → Đọc trực tiếp → recompose MỖI PIXEL scroll → performance issue
    //    → derivedStateOf chỉ recalculate khi firstVisibleItemIndex thay đổi
    //
    // 3. Tách active và completed:
    //    val activeTodos = todos.filter { !it.isDone }
    //    val completedTodos = todos.filter { it.isDone }
    //
    // 4. Scaffold với FAB:
    //    floatingActionButton = {
    //        AnimatedVisibility(
    //            visible = showFab,
    //            enter = fadeIn() + slideInVertically { it },
    //            exit = fadeOut() + slideOutVertically { it }
    //        ) { ExtendedFloatingActionButton(...) }
    //    }
    //
    // 5. Column bên trong: Header + AddTodoInput + LazyColumn
    //
    // 6. LazyColumn với stickyHeader + items(key = { it.id }):
    //    - stickyHeader(key = "active_header") { SectionHeader(...) }
    //    - items(activeTodos, key = { it.id }) { todo →
    //        TodoRow(modifier = Modifier.animateItem())  ← MAGIC LINE!
    //      }
    //    - Tương tự cho completed section
    //
    // GỢI Ý: animateItem() tự động animate:
    // - slide in khi add
    // - slide out khi remove
    // - move khi reorder
    // KEY BẮT BUỘC để animateItem hoạt động đúng!
    var todos by remember { mutableStateOf(todoList) }
    var newTodoText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var nextId by remember { mutableIntStateOf(todos.size + 1) }

    val showFab by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    val activeTodos by remember {
        derivedStateOf { todos.filter { !it.isDone } }
    }
    val completedTodos by remember {
        derivedStateOf { todos.filter { it.isDone } }
    }

    fun moveItem(item: TodoItem, direction: Int) {
        val index = todos.indexOf(item)
        val targetIndex = index + direction
        if (targetIndex in todos.indices) {
            todos = todos.toMutableList().apply {
                add(targetIndex, removeAt(index))
            }
        }
    }

    Scaffold(
        topBar = {
            Text(
                "Animated Todo List",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showFab,
                enter = fadeIn() + slideInVertically { it },
                exit = fadeOut() + slideOutVertically { it }
            ) {
                ExtendedFloatingActionButton(
                    text = { Text("Add") },
                    icon = { Text("+") },
                    onClick = {
                        todos = todos + TodoItem(nextId++, newTodoText)
                        newTodoText = "New note"
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(state = listState) {
                stickyHeader(key = "active_header", contentType = "sticky_header") {
                    SectionHeader(title = "Active Tasks")
                }
                items(
                    items = activeTodos,
                    key = { it.id },
                    contentType = { _ -> "todo_item" },
                ) { todoItem ->
                    TodoRow(
                        todo = todoItem,
                        onDelete = {
                            todos = todos - todoItem
                        },
                        onToggle = {
                            todos = todos.map { toggleItem ->
                                if (toggleItem.id == todoItem.id) {
                                    toggleItem.copy(isDone = !toggleItem.isDone)
                                } else {
                                    toggleItem
                                }
                            }
                        },
                        onMoveUp = {
                            moveItem(todoItem, -1)
                        },
                        onMoveDown = {
                            moveItem(todoItem, 1)
                        },
                        modifier = Modifier.animateItem()
                    )
                }
                stickyHeader(key = "completed_header", contentType = "sticky_header") {
                    SectionHeader(title = "Completed Tasks")
                }
                items(
                    items = completedTodos,
                    key = { it.id },
                    contentType = { _ -> "todo_item" },
                ) { todoItem ->
                    TodoRow(
                        todo = todoItem,
                        onDelete = {
                            todos = todos - todoItem
                        },
                        onToggle = {
                            todos = todos.map { toggleItem ->
                                if (toggleItem.id == todoItem.id) {
                                    toggleItem.copy(isDone = !toggleItem.isDone)
                                } else {
                                    toggleItem
                                }
                            }
                        },
                        onMoveUp = { moveItem(todoItem, -1) },
                        onMoveDown = { moveItem(todoItem, 1) },
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
    }
}

// ─── Previews ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, name = "Animated List - Light")
@Composable
private fun AnimatedListScreenPreview() {
    ComposeTrainingTheme {
        AnimatedListScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Animated List - Dark",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun AnimatedListScreenDarkPreview() {
    ComposeTrainingTheme(darkTheme = true) {
        AnimatedListScreen()
    }
}
