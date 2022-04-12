package com.example.historyvn.models

import kotlinx.serialization.Serializable

@Serializable
data class TheObject(
    val id: Int,
    val title: String,
    val category: Int,
    val location: String,
    val images: List<ImageModel>
)