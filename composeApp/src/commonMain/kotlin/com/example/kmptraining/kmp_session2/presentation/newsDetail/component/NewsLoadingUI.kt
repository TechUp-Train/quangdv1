package com.example.kmptraining.kmp_session2.presentation.newsDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer

@Composable
fun NewsLoadingUI() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(horizontal = maxWidth * 0.03f),
            verticalArrangement = Arrangement.spacedBy(screenHeight * 0.02f),
        ) {
            ShimmerContainer {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .height(screenHeight * 0.3f)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.05f),
            ) {
                ShimmerContainer {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(screenWidth * 0.25f)
                            .height(30.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }

                ShimmerContainer {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(screenWidth * 0.2f)
                            .height(20.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }
            }

            ShimmerContainer {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(6) {
                    ShimmerContainer {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .fillMaxWidth()
                                .height(16.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        )
                    }
                }
                ShimmerContainer {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth(0.6f)
                            .height(16.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsLoadingUIPreview() {
    NewsLoadingUI()
}