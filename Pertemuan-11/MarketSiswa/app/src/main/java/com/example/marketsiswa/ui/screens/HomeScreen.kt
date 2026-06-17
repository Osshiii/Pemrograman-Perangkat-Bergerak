package com.example.marketsiswa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketsiswa.data.Product
import com.example.marketsiswa.ui.components.ProductCard
import com.example.marketsiswa.ui.theme.*

@Composable
fun HomeScreen(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.ShoppingBag,
                        contentDescription = null,
                        tint = PrimaryPurple,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Marketplace Siswa",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = OnSurface
                    )
                }

                // Avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "R",
                        fontWeight = FontWeight.Bold,
                        color = PrimaryPurple,
                        fontSize = 13.sp
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(8.dp))
            Text(
                "Halo, Rosi!",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp,
                color = OnSurface
            )
            Text(
                "Temukan produk kreatif dari teman-temanmu.",
                color = SubtleGray,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
            Spacer(Modifier.height(8.dp))
        }

        items(products) { product ->
            ProductCard(
                product = product,
                onDetailClick = { onProductClick(product) }
            )
        }
    }
}