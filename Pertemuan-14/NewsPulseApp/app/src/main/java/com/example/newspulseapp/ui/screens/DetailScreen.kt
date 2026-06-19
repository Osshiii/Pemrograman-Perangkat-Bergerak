package com.example.newspulseapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newspulseapp.data.model.Article
import com.example.newspulseapp.ui.theme.*
import com.example.newspulseapp.viewmodel.NewsViewModel
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton

@Composable
fun DetailScreen(
    article: Article,
    viewModel: NewsViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val isSaved = viewModel.isArticleSaved(article)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgSoft)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Box {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC3A3552))
                            )
                        )
                )

                // Tombol back
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(20.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xE6FFFFFF))
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = PrimaryPurpleDark
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xE6FFFFFF))
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = article.source?.name ?: "News",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryPurpleDark
                    )
                }
            }

            Column(modifier = Modifier.padding(22.dp)) {
                Text(
                    text = article.title ?: "Tanpa Judul",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(SoftLavender)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "${article.author ?: "Unknown Author"} • ${
                            article.publishedAt?.take(
                                10
                            ) ?: "-"
                        }",
                        color = PrimaryPurpleDark,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = article.description ?: "Deskripsi berita tidak tersedia.",
                    fontSize = 16.sp,
                    color = TextDark,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = article.content
                        ?: "Konten lengkap tidak tersedia dari API. Silakan buka sumber asli untuk membaca berita selengkapnya.",
                    fontSize = 14.sp,
                    color = TextMedium,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(26.dp))

                Button(
                    onClick = { viewModel.toggleSavedArticle(article) },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSaved) AccentCoral else PrimaryPurple,
                        contentColor = CardWhite
                    )
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Save"
                    )
                    Text(
                        text = if (isSaved) "Berita Disimpan" else "Simpan Berita",
                        modifier = Modifier.padding(start = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = {
                        article.url?.let { url ->
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryPurple)
                ) {
                    Icon(
                        Icons.Filled.OpenInNew,
                        contentDescription = "Open original",
                        tint = PrimaryPurple
                    )
                    Text(
                        text = "Buka Sumber Asli",
                        color = PrimaryPurple,
                        modifier = Modifier.padding(start = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}