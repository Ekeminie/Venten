package com.example.venten.model

//Model class for Deserializing our response from the Api
data class FilterModel (
    val id : Int = 0,
    val start_year : Int,
    val end_year : Int,
    val gender : String,
    val countries : List<String>,
    val colors : List<String>
    )



