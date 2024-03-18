package com.goorm.kkiri.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemHelpPostBinding
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

    fun update(newItems: List<PostItem>) {
        val diffUtil = PostListDiffUtil(postItems, newItems)
        val result = DiffUtil.calculateDiff(diffUtil)
        postItems.clear()
        postItems.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    class PostListDiffUtil(
        private val oldItems: List<PostItem>,
        private val newItems: List<PostItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }

    }

    class HomePostListViewHolder(
        private val binding: ItemHelpPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(postItem: PostItem) {
            binding.postItem = postItem
        }

        companion object {
            fun from(parent: ViewGroup): HomePostListViewHolder {
                return HomePostListViewHolder(
                    ItemHelpPostBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}