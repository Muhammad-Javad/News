package com.javadsh98.news.ui.main.fragment.favorite

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequest
import coil.size.Precision
import coil.size.Scale
import com.javadsh98.news.R
import com.javadsh98.news.common.rawDateToPretty
import com.javadsh98.news.data.model.Article
import kotlinx.android.synthetic.main.item_article.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.ocpsoft.prettytime.PrettyTime
import timber.log.Timber

typealias OnItemClickListener = (Article) -> Unit

class FavoriteAdapter(val onItemClickListener: OnItemClickListener) : ListAdapter<Article, FavoriteViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.create(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
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

class FavoriteViewHolder(itemview: View, val onItemClickListener: OnItemClickListener)
    : RecyclerView.ViewHolder(itemview) , KoinComponent{

    val imageLoader: ImageLoader by inject()

    fun onBind(article: Article) {
        if (article.urlToImage != null) {
            val request = LoadRequest.Builder(itemView.context)
                .data(article.urlToImage)
                .error(R.drawable.img_item_preload)
                .target(onError = {
                    Timber.i("onerror")
                }, onSuccess = {
                    itemView.imageview_item_image.load(it)
                    Timber.i("onsuccess")
                }).build()

            imageLoader.execute(request)
        }
        itemView.textview_item_title.text = "${article.title}"
        itemView.textview_item_date.text = "${rawDateToPretty(article.publishedAt)}"
        itemView.textvew_item_author.text = "${article.author ?: ""}"

        //listener
        itemView.cardview_item.setOnClickListener {
            onItemClickListener.invoke(article)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onItemClickListener: OnItemClickListener): FavoriteViewHolder {
            return FavoriteViewHolder(
                LayoutInflater
                    .from(parent.context).inflate(
                        R.layout.item_article
                        , parent
                        , false
                    )
                , onItemClickListener
            )
        }
    }
}