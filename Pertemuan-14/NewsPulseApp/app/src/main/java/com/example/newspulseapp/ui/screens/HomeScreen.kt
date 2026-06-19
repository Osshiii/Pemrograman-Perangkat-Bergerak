package com.example.newspulseapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newspulseapp.data.model.Article
import com.example.newspulseapp.ui.components.NewsCard
import com.example.newspulseapp.ui.theme.*
import com.example.newspulseapp.viewmodel.NewsUiState
import com.example.newspulseapp.viewmodel.NewsViewModel

@Composable
fun HomeScreen(
    viewModel: NewsViewModel,
    onDetailClick: (Article) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(SoftLavender, BgSoft, BgSoft)
                )
            )
    ) {
        when (state) {
            is NewsUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = PrimaryPurple)
                }
            }

            is NewsUiState.Success -> {
                val articles = (state as NewsUiState.Success).articles

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp, bottom = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .background(AccentCoral, shape = androidx.compose.foundation.shape.CircleShape)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = "NewsPulse",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextDark
                                )
                            }

                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = "Update teknologi paling segar hari ini ✨",
                                fontSize = 14.sp,
                                color = TextMedium
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Box(
                                modifier = Modifier
                                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(50))
                                    .background(SoftMint)
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = "Top Headlines",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AccentMint
                                )
                            }
                        }
                    }

                    items(articles) { article ->
                        NewsCard(
                            article = article,
                            isSaved = viewModel.isArticleSaved(article),
                            onClick = { onDetailClick(article) },
                            onSaveClick = { viewModel.toggleSavedArticle(article) }
                        )
                    }
                }
            }

            is NewsUiState.Error -> {
                val message = (state as NewsUiState.Error).message

                Column(
                    modifier = Modifier.fillMaxSize().padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "😕 Gagal memuat berita",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(message, color = TextMedium, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { viewModel.loadTopHeadlines() },
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple)
                    ) {
                        Text("Coba Lagi", color = CardWhite, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}