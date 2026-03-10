package com.example.composetraining.session4.session4_4.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.session4.session4_4.data.TodoItem
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// ─── Todo Row ─────────────────────────────────────────────────────────────────

@Composable
fun TodoRow(
    todo: TodoItem,
    onToggle: () -> Unit,
    onDelete: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // 1. animateFloatAsState cho alpha khi completed:
    //    val alpha by animateFloatAsState(
    //        targetValue = if (todo.isDone) 0.5f else 1f,
    //        label = "todo_alpha"
    //    )
    //
    // 2. ListItem với:
    //    - headlineContent: Text todo.title với textDecoration = LineThrough nếu isDone
    //    - leadingContent: Checkbox(checked = isDone, onCheckedChange = { onToggle() })
    //    - trailingContent: Row với IconButton(ArrowBack/Up), IconButton(ArrowForward/Down), IconButton(Delete, error tint)
    //    - modifier với background (surfaceVariant.alpha(0.5f) nếu done, surface nếu không)
    //
    // 3. HorizontalDivider ở cuối
    val alpha by animateFloatAsState(
        targetValue = if (todo.isDone) 0.5f else 1f,
        label = "todo_alpha"
    )
    ListItem(
        modifier = modifier
            .graphicsLayer(alpha = alpha)
            .background(if (todo.isDone) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface),
        leadingContent = { Checkbox(checked = todo.isDone, onCheckedChange = { onToggle() }) },
        headlineContent = {
            Text(
                todo.title, style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None
                )
            )
        },
        trailingContent = {
            Row {
                IconButton(onClick = { onMoveUp() }) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Move Up")
                }
                IconButton(onClick = { onMoveDown() }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Move Down")
                }
                IconButton(onClick = { onDelete() }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    )
}

@Preview(showBackground = true, name = "Todo Row Preview")
@Composable
private fun TodoRowPreview() {
    ComposeTrainingTheme {
        Column {
            TodoRow(
                todo = TodoItem(1, "Design mockup", isDone = false),
                onToggle = {},
                onDelete = {},
                onMoveUp = {},
                onMoveDown = {},
            )
            TodoRow(
                todo = TodoItem(2, "Write tests", isDone = true),
                onToggle = {},
                onDelete = {},
                onMoveUp = {},
                onMoveDown = {},
            )
        }
    }
}