package com.javadsh98.news.data.repository.home.datasource.online

import com.javadsh98.news.data.model.Article
import kotlinx.coroutines.flow.*
import timber.log.Timber

class OnlineDataSourceImpl(val networkHelper: INetworkHelper) : IOnlineDataSource {

    private val news = mutableListOf<Article>()
    var errorMessage: String = ""

    override suspend fun sendRequest(): List<Article> {

        val list = mutableListOf<Article>()
        networkHelper.getNews(1)
            .zip(networkHelper.getNews(2)) { src1, src2 ->
                list.addAll(src1.articles)
                list.addAll(src2.articles)
                list
            }
            .catch {
                errorMessage = "$it"

            }.onEach {
                var str = it.joinToString(prefix = "{", postfix = "}", limit = 3) {
                    "title : ${it.title}"
                }
                Timber.i("src : $str")
            }
            .collect {
                news.clear()
                news.addAll(it)
            }

        return news
    }

}