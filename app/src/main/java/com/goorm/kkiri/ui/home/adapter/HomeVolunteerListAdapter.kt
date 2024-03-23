package com.goorm.kkiri.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemVolunteerBinding
import com.goorm.kkiri.domain.model.response.BoardVolunteerDto

class HomeVolunteerListAdapter : RecyclerView.Adapter<HomeVolunteerListAdapter.HomeVolunteerListViewHolder>() {
    private val items = mutableListOf<BoardVolunteerDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVolunteerListViewHolder {
        return HomeVolunteerListViewHolder.from(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeVolunteerListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(newItems: List<BoardVolunteerDto>) {
        val positionStart = newItems.size
        items.addAll(newItems)
        notifyItemRangeInserted(positionStart, items.size)
    }

    class HomeVolunteerListViewHolder(
        private val binding: ItemVolunteerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardVolunteerDto) {
            binding.boardVolunteerDto = item
        }

        companion object {
            fun from(parent: ViewGroup): HomeVolunteerListViewHolder {
                return HomeVolunteerListViewHolder(
                    ItemVolunteerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}