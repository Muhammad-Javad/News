package com.javadsh98.news.ui.main.fragment.favorite

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.javadsh98.news.R
import com.javadsh98.news.common.hide
import com.javadsh98.news.common.show
import com.javadsh98.news.ui.main.fragment.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoriteFragment : Fragment(R.layout.fragment_favorite), SearchView.OnQueryTextListener {

    val viewmodel: FavoriteViewModel by viewModel()
    lateinit var adapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        setupRecyclerview()
        getFavorites()

    }

    private fun getFavorites() {
        viewmodel.favorites.observe(viewLifecycleOwner, Observer {
            Timber.i("all fav")
            if(it.isEmpty()){
                textview_favorite_empty_list.show()
            }else {
                textview_favorite_empty_list.hide()
            }
            adapter.submitList(it)
        })
    }

    private fun setupRecyclerview() {
        recyclerview_favorite.setHasFixedSize(true)
        recyclerview_favorite.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavoriteAdapter {
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it, it.title)
            findNavController().navigate(action)
        }
        recyclerview_favorite.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_favorite, menu)
        val deleteAll = menu.findItem(R.id.menu_delete_all)
        deleteAll.setOnMenuItemClickListener {
            viewmodel.deleteAll()
            false
        }
        val sv = menu.findItem(R.id.menu_favorite_search)
        val searchview = sv.actionView as SearchView
        searchview.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let {
            if (it.isEmpty()){
                getFavorites()
            }else{
                getSearchedFavorites(query)
            }
        }
        return false
    }

    private fun getSearchedFavorites(query: String) {
        viewmodel.readByQuery(query).observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter.submitList(it)
            }
            Timber.i("search")
        })
    }


}