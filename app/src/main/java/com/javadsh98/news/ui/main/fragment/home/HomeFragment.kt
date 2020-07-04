package com.javadsh98.news.ui.main.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
        recyclerview_home.layoutManager = LinearLayoutManager(requireContext())
        var adapter = HomeAdapter()
        recyclerview_home.adapter = adapter

        adapter.submitList(
            listOf(
                Article(
                    0,
                    "test",
                    "title",
                    "description",
                    "url",
                    "urlToImage",
                    "1399",
                    "content"
                )
            )
        )

        viewmodel.news.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            var str = it.joinToString(limit = 5, separator = ",\n", prefix = "{\n", postfix = "\n}") {
                "title : ${it.title}"
            }
            Toast.makeText(requireContext(), str, Toast.LENGTH_LONG).show()
            Timber.i(str)
        })

    }

}