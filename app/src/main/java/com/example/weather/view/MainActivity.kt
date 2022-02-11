package com.example.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.R
import com.example.weather.databinding.MainActivityBinding
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat

class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: MainActivityBinding
    lateinit var drawer: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(getLayoutInflater())
        val view = binding.root
        setContentView(view)
        initDrawer()

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()

    }

    private fun initDrawer() {
        drawer = binding.drawerLayout
        navigationView = binding.navView
        toolbar = binding.toolbar
        //  toolbar.setOnMenuItemClickListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.nav_about -> TODO()
            R.id.nav_home -> TODO()
            R.id.nav_settings -> TODO()
        }
        drawer = binding.drawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}