package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class PixabayResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<ImageHit>
)

data class ImageHit(
    @SerializedName("id") val id: Int,
    @SerializedName("previewURL") val previewURL: String
)