package com.javadsh98.news.ui.main.fragment.favorite

import androidx.lifecycle.*
import com.javadsh98.news.data.model.Article
import com.javadsh98.news.data.repository.favorite.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(val repository: FavoriteRepository) : ViewModel() {

    val favorites: LiveData<List<Article>>
        get() = repository.readAll()

    lateinit var searchedFavorites: LiveData<List<Article>>


    fun insert(article: Article) {
        val newArticle = Article(
            article.id
            , article.author ?: ""
            , article.title ?: ""
            , article.description ?: ""
            , article.url ?: ""
            , article.urlToImage ?: ""
            , article.publishedAt ?: ""
            , article.content ?: ""
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(newArticle)
        }
    }

    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(article)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun readByQuery(query: String): LiveData<List<Article>>{
        searchedFavorites = repository.readByQuery(query)
        return searchedFavorites
    }

}