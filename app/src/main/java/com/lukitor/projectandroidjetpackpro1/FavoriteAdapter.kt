package com.lukitor.projectandroidjetpackpro1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy

class FavoriteAdapter :
    PagedListAdapter<MovieEntitiy, FavoriteAdapter.ListViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntitiy>() {
            override fun areItemsTheSame(oldItem: MovieEntitiy, newItem: MovieEntitiy): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntitiy, newItem: MovieEntitiy): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var gambar: ImageView = itemView.findViewById(R.id.gambarcard)
        private var judul: TextView = itemView.findViewById(R.id.txtJudul)
        private var tipe: TextView = itemView.findViewById(R.id.txtTipe)
        fun bind(movie: MovieEntitiy) {
            judul.text = movie.judul
            tipe.text = movie.type
            Glide.with(itemView.context).load(movie.image).apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            ).into(gambar)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("data", movie.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}