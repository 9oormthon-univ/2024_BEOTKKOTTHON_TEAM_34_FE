package com.goorm.kkiri.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemHomeHelpPostBinding
import com.goorm.kkiri.domain.model.response.BoardHomeDto

class HomeHelpMeListAdapter : RecyclerView.Adapter<HomeHelpMeListAdapter.HomeHelpMeListViewHolder>() {
    private val items = mutableListOf<BoardHomeDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHelpMeListViewHolder {
        return HomeHelpMeListViewHolder.from(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeHelpMeListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(newItems: List<BoardHomeDto>) {
        val positionStart = newItems.size
        items.addAll(newItems)
        notifyItemRangeInserted(positionStart, items.size)
    }

    class HomeHelpMeListViewHolder(
        private val binding: ItemHomeHelpPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardHomeDto) {
            binding.boardHomeDto = item
        }

        companion object {
            fun from(parent: ViewGroup): HomeHelpMeListViewHolder {
                return HomeHelpMeListViewHolder(
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