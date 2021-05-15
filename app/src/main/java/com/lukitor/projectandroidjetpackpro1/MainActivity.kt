package com.lukitor.projectandroidjetpackpro1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lukitor.projectandroidjetpackpro1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgFav.setOnClickListener { view ->
            startActivity(
                Intent(
                    this,
                    FavoriteActivity::class.java
                )
            )
        }
        binding.NavView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.menu_all -> {
                    fragment = DataFragment.newInstance("All")
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_movie -> {
                    fragment = DataFragment.newInstance("Movie")
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_tvshow -> {
                    fragment = DataFragment.newInstance("TV Show")
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        if (savedInstanceState == null) {
            binding.NavView.setSelectedItemId(R.id.menu_all)
        }
    }
}