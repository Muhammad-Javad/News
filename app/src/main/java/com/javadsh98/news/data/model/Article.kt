package com.javadsh98.news.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    @Expose(serialize = false, deserialize = false)
    @PrimaryKey(autoGenerate = true) var id : Int
    , val author: String
    , val title: String
    , val description: String
    , val url: String
    , val urlToImage: String = ""
    , val publishedAt: String
    , val content: String = ""
): Parcelable