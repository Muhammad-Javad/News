package com.javadsh98.news.ui.main.fragment.home

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Precision
import coil.size.Scale
import com.javadsh98.news.R
import com.javadsh98.news.common.rawDateToPretty
import com.javadsh98.news.data.model.Article
import kotlinx.android.synthetic.main.item_article.view.*
import org.ocpsoft.prettytime.PrettyTime

typealias OnItemClickListener = (Article) -> Unit

class HomeAdapter(val onItemClickListener: OnItemClickListener) : ListAdapter<Article, HomeViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.create(parent, onItemClickListener)
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

class HomeViewHolder(itemview: View, val onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemview) {

    fun onBind(article: Article) {
        if (article.urlToImage != null)
            itemView.imageview_item_image
                .load(article.urlToImage){
                    placeholder(R.drawable.img_item_preload)
                    error(R.drawable.img_item_preload)
                    precision(Precision.EXACT)
                    scale(Scale.FILL)
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
        fun create(parent: ViewGroup, onItemClickListener: OnItemClickListener): HomeViewHolder {
            return HomeViewHolder(
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