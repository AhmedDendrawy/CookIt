package com.ahmed.cookit.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahmed.cookit.model.Category
import com.ahmed.cookit.ui.theme.gradientBrush

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onItemClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.imageURL,
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .border(width = if (isSelected) 4.dp else 2.dp, gradientBrush, CircleShape)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.name,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}