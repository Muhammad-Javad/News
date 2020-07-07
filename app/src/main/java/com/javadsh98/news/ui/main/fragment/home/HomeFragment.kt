package com.javadsh98.news.ui.main.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class HomeFragment : Fragment(R.layout.fragment_home) {

    val viewmodel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerview_home.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerview_home.layoutManager = layoutManager
        var adapter = HomeAdapter { article ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(article, article.title)
            findNavController()
                .navigate(
                    action
                )
        }

        recyclerview_home.adapter = adapter

        viewmodel.news.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }

}