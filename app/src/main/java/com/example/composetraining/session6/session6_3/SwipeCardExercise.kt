package com.example.composetraining.session6.session6_3

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apero.composetraining.common.SampleData
import com.example.composetraining.session6.session6_3.component.InteractionButtonsRow
import com.example.composetraining.session6.session6_3.component.SwipeableProfileCard
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import kotlinx.coroutines.launch
import kotlin.math.abs
import androidx.compose.runtime.remember

/**
 * ⭐⭐⭐ BÀI TẬP 3: Tinder Swipe Card (Challenge)
 *
 * Yêu cầu:
 * - 5 profile cards stacked (Box)
 * - Drag gesture: detectDragGestures
 * - Swipe left = "Nope" (red overlay), right = "Like" (green overlay)
 * - Card rotation theo drag offset
 * - animateFloatAsState cho snap back khi thả giữa
 * - Card tiếp theo slide lên khi top card bị swipe
 * - Drag amount > threshold → dismiss
 * - Drag amount < threshold → snap back với spring
 */

@Composable
fun SwipeCardScreen() {
    val profiles = SampleData.profileCards
    val scope = rememberCoroutineScope()
    var currentIndex by remember { mutableIntStateOf(0) }
    val offsetX = remember { Animatable(0f) }

    val threshold = 300f

    fun swipe(direction: Float) {
        scope.launch {
            offsetX.animateTo(direction * 1000f)
            currentIndex++
            offsetX.snapTo(0f)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Swipe Cards", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (currentIndex < profiles.size) {
                val visibleCards = remember { profiles.drop(currentIndex).take(3).reversed() }
                visibleCards.forEachIndexed { index, profile ->
                    val isTopCard = index == visibleCards.lastIndex

                    SwipeableProfileCard(
                        profile = profile,
                        isTopCard = isTopCard,
                        offsetX = if (isTopCard) offsetX.value else 0f,
                        modifier = Modifier
                            .fillMaxSize(0.9f)
                            .offset(y = (index * -4).dp)
                            .then(
                                if (isTopCard) {
                                    Modifier.pointerInput(Unit) {
                                        detectDragGestures(
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                scope.launch {
                                                    offsetX.snapTo(offsetX.value + dragAmount.x)
                                                }
                                            },
                                            onDragEnd = {
                                                if (abs(offsetX.value) > threshold) {
                                                    swipe(if (offsetX.value > 0) 1f else -1f)
                                                } else {
                                                    scope.launch {
                                                        offsetX.animateTo(0f, spring())
                                                    }
                                                }
                                            }
                                        )
                                    }
                                } else Modifier
                            )
                    )
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("No more profiles!", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { currentIndex = 0 }) {
                        Text("Restart")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        InteractionButtonsRow(isEnabled = currentIndex < profiles.size, onSwipeLeft = { swipe(-1f) }, onSwipeRight = { swipe(1f) })
    }
}

@Preview(showBackground = true)
@Composable
private fun SwipeCardScreenPreview() {
    ComposeTrainingTheme { SwipeCardScreen() }
}
