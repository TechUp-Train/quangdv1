package com.example.composetraining.session2.session2_4.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.session2.session2_4.PricingPlan

@Composable
fun PricingCard(pricingPlan: PricingPlan, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        border = if (pricingPlan.isPopular)
            BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        else null
    ) {
        Box {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = pricingPlan.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (pricingPlan.isPopular) MaterialTheme.colorScheme.primary else Color.Unspecified
                )
                Text(
                    text = pricingPlan.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = pricingPlan.period,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                pricingPlan.features.forEach { feature ->
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = feature,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(
                        text = stringResource(R.string.choose_plan),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (pricingPlan.isPopular) {
                PopularBadge(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D0D)
@Composable
private fun PricingCardPreview() {
    PricingCard(
        PricingPlan(
            name = "Basic",
            price = "Free",
            period = "mãi mãi",
            features = listOf(
                "5 AI generations/ngày",
                "Độ phân giải 512px",
                "Watermark"
            )
        ),
    )
}
