package com.javadsh98.news.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.javadsh98.news.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)

        var controller = findNavController(R.id.fragment_navigation)

        var appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment), drawerlayout_main)

        NavigationUI.setupActionBarWithNavController(this, controller, drawerlayout_main)
        NavigationUI.setupWithNavController(toolbar_main, controller, appBarConfiguration)

        navigationview_main.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if(drawerlayout_main.isDrawerOpen(GravityCompat.START))
            drawerlayout_main.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigateUp(): Boolean {

        return findNavController(R.id.fragment_navigation).navigateUp() || super.onNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerlayout_main.closeDrawer(GravityCompat.START)
        return NavigationUI
            .onNavDestinationSelected(item, findNavController(R.id.fragment_navigation))
    }

}