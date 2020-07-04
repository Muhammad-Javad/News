package com.javadsh98.news.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.javadsh98.news.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var controller = findNavController(R.id.fragment_navigation)

        var appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment), drawerlayout_main)

        toolbar_main.setupWithNavController(controller, appBarConfiguration)
        navigationview_main.setupWithNavController(controller)

    }

}