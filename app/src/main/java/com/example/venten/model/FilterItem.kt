package com.example.venten.model

data class FilterItem(
    val id: Int,
    val start_year: Int,
    val end_year: Int,
    val gender: String,
    val countries: List<String>,
    val colors: List<String>


)