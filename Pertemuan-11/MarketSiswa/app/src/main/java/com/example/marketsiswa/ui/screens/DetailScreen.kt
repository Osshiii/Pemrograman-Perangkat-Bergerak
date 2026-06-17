package com.example.marketsiswa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketsiswa.data.Product
import com.example.marketsiswa.ui.components.formatPrice
import com.example.marketsiswa.ui.theme.*

@Composable
fun DetailScreen(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Category chip
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = CategoryChipColor
        ) {
            Text(
                text = product.category.uppercase(),
                color = CategoryChipText,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }

        Text(
            text = product.name,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = OnSurface
        )

        Text(
            text = "Rp ${formatPrice(product.price)}",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = PrimaryPurple
        )

        HorizontalDivider()

        Text("Deskripsi Produk", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(
            text = product.description,
            color = SubtleGray,
            fontSize = 15.sp,
            lineHeight = 24.sp
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple)
        ) {
            Text("Hubungi Penjual", fontWeight = FontWeight.Bold)
        }
    }
}