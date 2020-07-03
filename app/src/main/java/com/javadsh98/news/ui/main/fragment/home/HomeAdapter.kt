package com.javadsh98.news.ui.main.fragment.home

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.javadsh98.news.R
import com.javadsh98.news.data.model.Article
import kotlinx.android.synthetic.main.item_article.view.*

class HomeAdapter : ListAdapter<Article, HomeViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return TextUtils.equals(oldItem.urlToImage, newItem.urlToImage)
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class HomeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    fun onBind(article: Article) {
        itemView.imageview_item_image.load(article.urlToImage)
        itemView.textview_item_title.text = "${article.title}"
        itemView.textview_item_description.text = "${article.description}"
        itemView.textview_item_date.text = "${article.publishedAt}"
    }

    companion object {
        fun create(parent: ViewGroup): HomeViewHolder {
            return HomeViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
            )
        }
    }
}