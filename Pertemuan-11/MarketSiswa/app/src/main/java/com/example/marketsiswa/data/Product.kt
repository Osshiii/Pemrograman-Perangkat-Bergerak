package com.example.marketsiswa.data

data class Product(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val price: String,
    val description: String,
    val category: String = "UMUM"
)