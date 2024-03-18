package com.goorm.kkiri.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemHomeHelpPostBinding
import com.goorm.kkiri.domain.model.response.PostItem

class HomePostListAdapter : RecyclerView.Adapter<HomePostListAdapter.HomePostListViewHolder>() {
    private val postItems = mutableListOf<PostItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostListViewHolder {
        return HomePostListViewHolder.from(parent)
    }

    override fun getItemCount(): Int = postItems.size

    override fun onBindViewHolder(holder: HomePostListViewHolder, position: Int) {
        holder.bind(postItems[position])
    }

    class HomePostListViewHolder(
        private val binding: ItemHomeHelpPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(postItem: PostItem) {

        }

        companion object {
            fun from(parent: ViewGroup): HomePostListViewHolder {
                return HomePostListViewHolder(
                    ItemHomeHelpPostBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}