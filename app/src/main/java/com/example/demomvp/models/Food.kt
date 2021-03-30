package com.example.demomvp.models

data class Food(
    val id: Int,
    val categoryId: Int,
    val specialtiesId: Int,
    val title: String,
    val thumbnail: String,
    val description: String
)