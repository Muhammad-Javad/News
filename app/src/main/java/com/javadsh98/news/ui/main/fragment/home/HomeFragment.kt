package com.javadsh98.news.ui.main.fragment.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.javadsh98.news.R
import com.javadsh98.news.data.model.Article
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment(R.layout.fragment_home),
    SearchView.OnQueryTextListener {

    val viewmodel: HomeViewModel by viewModel()
    var articles: List<Article> = listOf()
    var adapter: HomeAdapter = HomeAdapter { article ->
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(article, article.title)
        findNavController()
            .navigate(
                action
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        recyclerview_home.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerview_home.layoutManager = layoutManager

        recyclerview_home.adapter = adapter

        viewmodel.news.observe(viewLifecycleOwner, Observer {
            articles = it
            adapter.submitList(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home_search, menu)
        val search = menu.findItem(R.id.menu_home_search)
        val searchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let {
            if (query.isEmpty())
                adapter.submitList(articles)
            else
                adapter.submitList(
                    viewmodel.searchInList(
                        query
                        , articles
                    )
                )
        }
        return false
    }

}