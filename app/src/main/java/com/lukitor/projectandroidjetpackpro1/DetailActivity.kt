package com.lukitor.projectandroidjetpackpro1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import com.lukitor.projectandroidjetpackpro1.databinding.ActivityDetailBinding
import com.lukitor.projectandroidjetpackpro1.viewmodel.ViewModelFactory
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var tempMovie: MovieEntitiy
    var datamovie = ArrayList<MovieEntitiy>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        if (intent.hasExtra("data")) {
            val judul: String = intent.getStringExtra("data").toString()
            viewModel.getDetail(judul).observe(this, { courses ->
                binding.progressBar.visibility = View.GONE
                setMovie(courses)
            })
        }
        binding.imgback.setOnClickListener { view -> finish() }
        binding.ImgDetailFav.setOnClickListener { view ->
            viewModel.setFavorite(tempMovie)
            if (tempMovie.favorite == 0) {
                Toast.makeText(
                    this,
                    "Berhasil Menambahkan " + tempMovie.type + " Dengan Judul " + tempMovie.judul + " Ke Daftar Favorite",
                    Toast.LENGTH_SHORT
                ).show()
                Glide.with(this).load(R.drawable.ic_baseline_favorite_24).into(binding.ImgDetailFav)
            } else {
                Toast.makeText(
                    this,
                    "Berhasil Menghapus " + tempMovie.type + " Dengan Judul " + tempMovie.judul + " Dari Daftar Favorite",
                    Toast.LENGTH_SHORT
                ).show()
                Glide.with(this).load(R.drawable.ic_baseline_unfavorite_border_24)
                    .into(binding.ImgDetailFav)
            }
        }
    }

    fun setMovie(movie: List<MovieEntitiy>?) {
        if (movie == null) return
        this.datamovie.clear()
        this.datamovie.addAll(movie)
        this.datamovie.forEach {
            Glide.with(this).load(it.image).into(binding.imgCover)
            Glide.with(this).load(it.image).into(binding.imgMovie)
            binding.txtDJudul.text = it.judul
            binding.txtRating.text = it.rating
            binding.txtGenre.text = it.genre
            binding.txtdeskripsi.text = it.description
            if (it.favorite == 0) {
                Glide.with(this).load(R.drawable.ic_baseline_unfavorite_border_24)
                    .into(binding.ImgDetailFav)
            } else {
                Glide.with(this).load(R.drawable.ic_baseline_favorite_24).into(binding.ImgDetailFav)
            }
            tempMovie = MovieEntitiy(
                it.id,
                it.judul,
                it.image,
                it.description,
                it.rating,
                it.genre,
                it.type,
                it.favorite
            )
        }
    }
}