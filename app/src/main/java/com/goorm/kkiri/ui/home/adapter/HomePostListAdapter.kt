package com.goorm.kkiri.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemHelpPostBinding
import com.goorm.kkiri.domain.model.response.BoardIndividualDto
import com.goorm.kkiri.ui.common.HelpPostClickListener

class HomePostListAdapter(
    private val listener: HelpPostClickListener
) : RecyclerView.Adapter<HomePostListAdapter.HomePostListViewHolder>() {
    private val boardIndividualDtos = mutableListOf<BoardIndividualDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostListViewHolder {
        return HomePostListViewHolder.from(parent)
    }

    override fun getItemCount(): Int = boardIndividualDtos.size

    override fun onBindViewHolder(holder: HomePostListViewHolder, position: Int) {
        holder.bind(boardIndividualDtos[position], listener)
    }

    fun update(newItems: List<BoardIndividualDto>) {
        val diffUtil = PostListDiffUtil(boardIndividualDtos, newItems)
        val result = DiffUtil.calculateDiff(diffUtil)
        boardIndividualDtos.clear()
        boardIndividualDtos.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    class PostListDiffUtil(
        private val oldItems: List<BoardIndividualDto>,
        private val newItems: List<BoardIndividualDto>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }

    }

    class HomePostListViewHolder(
        private val binding: ItemHelpPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(boardIndividualDto: BoardIndividualDto, listener: HelpPostClickListener) {
            binding.postItem = boardIndividualDto
            binding.listener = listener
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