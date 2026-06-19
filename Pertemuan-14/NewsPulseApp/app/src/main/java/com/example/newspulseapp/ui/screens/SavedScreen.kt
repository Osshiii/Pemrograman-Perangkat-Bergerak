package com.example.newspulseapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newspulseapp.data.model.Article
import com.example.newspulseapp.ui.components.NewsCard
import com.example.newspulseapp.ui.theme.*
import com.example.newspulseapp.viewmodel.NewsViewModel

@Composable
fun SavedScreen(
    viewModel: NewsViewModel,
    onDetailClick: (Article) -> Unit
) {
    val savedArticles = viewModel.savedArticles

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(SoftPeach, BgSoft, BgSoft))
            )
    ) {
        if (savedArticles.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Belum ada berita tersimpan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Tekan ikon bookmark pada berita untuk menyimpannya.",
                    color = TextMedium,
                    fontSize = 13.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Tersimpan",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextDark,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Text(
                        text = "Berita favoritmu selama sesi ini",
                        color = TextMedium,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp, bottom = 6.dp)
                    )
                }

                items(savedArticles) { article ->
                    NewsCard(
                        article = article,
                        isSaved = true,
                        onClick = { onDetailClick(article) },
                        onSaveClick = { viewModel.toggleSavedArticle(article) }
                    )
                }
            }
        }
    }
}