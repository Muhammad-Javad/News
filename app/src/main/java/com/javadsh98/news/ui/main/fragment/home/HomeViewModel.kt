package com.javadsh98.news.ui.main.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javadsh98.news.data.model.Article
import com.javadsh98.news.data.repository.home.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: IHomeRepository) : ViewModel(){

    private val _news: MutableLiveData<List<Article>> = MutableLiveData()
    val news: LiveData<List<Article>>
        get() = _news

    init {
        getNews()
    }

    private fun getNews(){
        viewModelScope.launch(context = Dispatchers.IO){
            val news = repository.getNews()
            withContext(Dispatchers.Main){
                _news.value = news
            }
        }
    }

    fun searchInList(query: String, articles: List<Article>)=
        articles.filter {
            it.title.contains(query, true)
        }

}