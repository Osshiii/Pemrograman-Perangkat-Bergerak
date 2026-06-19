package com.example.newspulseapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.newspulseapp.data.model.Article
import com.example.newspulseapp.ui.theme.*

@Composable
fun NewsCard(
    article: Article,
    isSaved: Boolean,
    onClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column {
            Box {
                SubcomposeAsyncImage(
                    model = article.urlToImage,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    contentScale = ContentScale.Crop,
                    loading = { ImagePlaceholder() },
                    error = { ImagePlaceholder() }
                )

                // Soft gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0x4D3A3552))
                            )
                        )
                )

                // Source pill
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(14.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xE6FFFFFF))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = article.source?.name ?: "News",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryPurpleDark,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Bookmark button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xE6FFFFFF))
                ) {
                    IconButton(onClick = onSaveClick) {
                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Save",
                            tint = if (isSaved) AccentCoral else TextMedium
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = article.title ?: "Tanpa Judul",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = article.description ?: "Deskripsi berita tidak tersedia.",
                    fontSize = 13.sp,
                    color = TextMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(SoftLavender)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = article.author?.take(20) ?: "Unknown",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryPurpleDark,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        text = article.publishedAt?.take(10) ?: "-",
                        fontSize = 11.sp,
                        color = TextLight
                    )
                }
            }
        }
    }
}

@Composable
fun ImagePlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(SoftLavender, SoftBlue, SoftMint)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "📰",
            fontSize = 32.sp
        )
    }
}