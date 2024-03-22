package com.goorm.kkiri.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemImageFileBinding

class HomeUploadImageFileAdapter : RecyclerView.Adapter<HomeUploadImageFileAdapter.HomeUploadImageFileViewHolder>() {
    private val imageItems = mutableListOf<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeUploadImageFileViewHolder {
        return HomeUploadImageFileViewHolder.from(parent)
    }

    override fun getItemCount(): Int = imageItems.size

    override fun onBindViewHolder(holder: HomeUploadImageFileViewHolder, position: Int) {
        holder.bind(imageItems[position])
    }

    fun update(images: List<String>) {
        val positionStart = images.size
        imageItems.addAll(images)
        notifyItemRangeInserted(positionStart, images.size)
    }

    class HomeUploadImageFileViewHolder(
        private val binding: ItemImageFileBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(imageItem: String) {
            binding.imageUrl = imageItem
        }

        companion object {
            fun from(parent: ViewGroup): HomeUploadImageFileViewHolder {
                return HomeUploadImageFileViewHolder(
                    ItemImageFileBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}