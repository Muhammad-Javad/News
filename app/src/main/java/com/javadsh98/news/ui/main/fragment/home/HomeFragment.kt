package com.javadsh98.news.ui.main.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.javadsh98.news.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    val viewmodel: HomeViewModel by viewModel()
    val adapter = HomeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewmodel.news.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        recyclerview_home.setHasFixedSize(true)
        recyclerview_home.adapter = adapter



    }

}