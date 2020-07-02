package com.javadsh98.news.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    val author: String
    , val title: String
    , val description: String
    , val url: String
    , @PrimaryKey val urlToImage: String
    , val publishedAt: String
    , val content: String
): Parcelable