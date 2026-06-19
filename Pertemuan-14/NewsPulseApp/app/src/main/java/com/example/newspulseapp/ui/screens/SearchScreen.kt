package com.example.newspulseapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newspulseapp.data.model.Article
import com.example.newspulseapp.ui.components.NewsCard
import com.example.newspulseapp.ui.theme.*
import com.example.newspulseapp.viewmodel.NewsUiState
import com.example.newspulseapp.viewmodel.NewsViewModel

@Composable
fun SearchScreen(
    viewModel: NewsViewModel,
    onDetailClick: (Article) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(SoftBlue, BgSoft, BgSoft))
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text(
                        text = "Cari Berita",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextDark
                    )
                    Text(
                        text = "Temukan topik favoritmu",
                        fontSize = 14.sp,
                        color = TextMedium
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Cari berita terbaru...", color = TextLight) },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Search", tint = PrimaryPurple)
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(50),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryPurple,
                            unfocusedBorderColor =  Color(0xFFE3DFF5),
                            cursorColor = PrimaryPurple,
                            focusedContainerColor = CardWhite,
                            unfocusedContainerColor = CardWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { viewModel.searchNews(query) },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple)
                    ) {
                        Text("Cari Berita", color = CardWhite, fontWeight = FontWeight.Bold)
                    }
                }
            }

            when (state) {
                is NewsUiState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = PrimaryPurple)
                        }
                    }
                }

                is NewsUiState.Success -> {
                    val articles = (state as NewsUiState.Success).articles
                    if (articles.isEmpty()) {
                        item {
                            Text(
                                text = "Tidak ada berita yang ditemukan 😔",
                                color = TextMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    } else {
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
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Gagal mencari berita", color = TextDark, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(message, color = TextMedium, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}