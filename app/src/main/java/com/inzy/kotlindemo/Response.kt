package com.inzy.kotlindemo

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("articles") val feeds: List<Feed>)

data class Feed(
    val title: String,
    @SerializedName("urlToImage")
    val image: String
)
