package com.lukitor.projectandroidjetpackpro1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lukitor.projectandroidjetpackpro1.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgHome.setOnClickListener { view -> finish() }
        binding.NavViewFav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.menu_all -> {
                    fragment = FavoriteFragment.newInstance("")
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_movie -> {
                    fragment = FavoriteFragment.newInstance("Movie")
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_tvshow -> {
                    fragment = FavoriteFragment.newInstance("TV Show")
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        if (savedInstanceState == null) {
            binding.NavViewFav.setSelectedItemId(R.id.menu_all)
        }
    }
}