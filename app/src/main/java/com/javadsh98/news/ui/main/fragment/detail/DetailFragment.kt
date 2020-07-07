package com.javadsh98.news.ui.main.fragment.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequest
import coil.size.Precision
import coil.size.Scale
import com.javadsh98.news.R
import com.javadsh98.news.common.rawDateToPretty
import com.javadsh98.news.data.model.Article
import com.javadsh98.news.ui.main.fragment.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_article.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class DetailFragment : Fragment(R.layout.fragment_detail) {

    val args: DetailFragmentArgs by navArgs()
    lateinit var article: Article

    val viewModel: FavoriteViewModel by viewModel()
    var favorites: List<Article> = listOf()

    val imageLoader: ImageLoader by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        readAllFavorite()

        setupViews()
        setupListener()
    }

    private fun readAllFavorite() {
        viewModel.favorites.observe(viewLifecycleOwner, Observer {
            favorites = it
            Timber.i("favorites")
            updateFavIcon()
        })
    }

    private fun setupListener() {
        imageview_detail_favorite.setOnClickListener {
            if (isFavorite()) {
                viewModel.delete(equivalentInFavorites())
                Timber.i("delete")
            } else {
                viewModel.insert(article)
                Timber.i("insert")
            }
        }

        imageview_detail_share.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = "${article.title}\n${article.url}"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
    }

    private fun setupViews() {
        article = args.article
        textview_detail_author.text = "${article.author ?: ""}"
        textview_detail_title.text = "${article.title ?: ""}"
        textview_detail_date.text = "${rawDateToPretty(article.publishedAt)}"
        textview_detail_description.text = "${article.description ?: ""}"

        loadImaegAndTransition()
    }

    private fun loadImaegAndTransition() {

        imageview_detail.load(article.urlToImage){
            placeholder(R.drawable.img_item_preload)
            error(R.drawable.img_item_preload)
            precision(Precision.EXACT)
            scale(Scale.FILL)
        }
    }

    private fun updateFavIcon() {
        imageview_detail_favorite
            .setImageResource(
                if (isFavorite()) R.drawable.ic_favorite_red_selected
                else R.drawable.ic_favorite_red_unselected
            )
    }

    private fun isFavorite(): Boolean {
        val filteredFavorite = favorites
            .filter { article.equals(it) }

        return !filteredFavorite.isEmpty()
                && filteredFavorite.size == 1
    }

    private fun equivalentInFavorites(): Article {
        val filteredFavorite = favorites
            .filter { article.equals(it) }
        return filteredFavorite[0]
    }


}