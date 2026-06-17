package com.example.marketsiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.marketsiswa.data.Product
import com.example.marketsiswa.ui.screens.AddProductScreen
import com.example.marketsiswa.ui.screens.DetailScreen
import com.example.marketsiswa.ui.screens.HomeScreen
import com.example.marketsiswa.ui.screens.ProfileScreen
import com.example.marketsiswa.ui.theme.MarketplaceTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketplaceTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("home") }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    val productList = remember { mutableStateListOf<Product>() }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (productList.isEmpty()) {
            productList.add(
                Product(
                    name = "Brownies Lumer",
                    price = "15000",
                    description = "Cokelat melimpah.",
                    category = "MAKANAN"
                )
            )
            productList.add(
                Product(
                    name = "Kaos Custom",
                    price = "85000",
                    description = "Bahan adem, bisa request desain sendiri.",
                    category = "FASHION"
                )
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            if (currentScreen == "add" || currentScreen == "detail") {
                TopAppBar(
                    title = {
                        Text(
                            if (currentScreen == "add") "Tambah Produk" else "Detail Produk",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { currentScreen = "home" }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (currentScreen != "add") {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentScreen == "home",
                        onClick = { currentScreen = "home" },
                        label = { Text("Home") },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) }
                    )
                    NavigationBarItem(
                        selected = currentScreen == "profile",
                        onClick = { currentScreen = "profile" },
                        label = { Text("Profile") },
                        icon = { Icon(Icons.Default.Person, contentDescription = null) }
                    )
                }
            }
        },
        floatingActionButton = {
            if (currentScreen == "home") {
                ExtendedFloatingActionButton(
                    onClick = { currentScreen = "add" },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                "home" -> HomeScreen(
                    products = productList,
                    onProductClick = { product ->
                        selectedProduct = product
                        currentScreen = "detail"
                    }
                )
                "add" -> AddProductScreen(
                    onProductAdded = { newProduct ->
                        productList.add(0, newProduct)
                        scope.launch {
                            currentScreen = "home"
                            snackbarHostState.showSnackbar("Produk berhasil ditambahkan!")
                        }
                    }
                )
                "profile" -> ProfileScreen()
                "detail" -> selectedProduct?.let { DetailScreen(product = it) }
            }
        }
    }
}