package com.example.sabaideatest.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sabaideatest.R
import com.example.sabaideatest.databinding.ItemMovieBinding
import com.example.sabaideatest.domain.model.MovieItemEntity

class SearchAdapter : ListAdapter<MovieItemEntity, SearchAdapter.MovieViewHolder>(MovieDiffUtil()) {

    override fun submitList(list: List<MovieItemEntity?>?) {
        super.submitList(list?.toList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItemEntity) {
            binding.textViewMovieTitle.text = item.movieTitle.toString().trim()
            binding.textViewMovieTitleEnglish.text = item.movieTitleEnglish.toString().trim()
            Glide
                .with(binding.root.context)
                .load(item.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_no_image)
                .into(binding.thumbnail)
        }
    }

    class MovieDiffUtil : DiffUtil.ItemCallback<MovieItemEntity>() {
        override fun areItemsTheSame(oldItem: MovieItemEntity, newItem: MovieItemEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieItemEntity,
            newItem: MovieItemEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}