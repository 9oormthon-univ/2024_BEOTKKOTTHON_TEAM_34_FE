package com.goorm.kkiri.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goorm.kkiri.databinding.ItemMyImageButtonBinding
import com.goorm.kkiri.domain.model.response.MyImageItem

class MyImageButtonAdapter(
    private val listener: MenuClickListener
) : RecyclerView.Adapter<MyImageButtonAdapter.MyImageButtonViewHolder>() {


    private val items = mutableListOf<MyImageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImageButtonViewHolder {
        val binding = ItemMyImageButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyImageButtonViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyImageButtonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(posts: List<MyImageItem>) {
        val diffUtil = RecordSaveDiffUtil(items, posts)
        val result = DiffUtil.calculateDiff(diffUtil)
        items.clear()
        items.addAll(posts)
        result.dispatchUpdatesTo(this)
    }

    class RecordSaveDiffUtil(
        private val oldItems: List<MyImageItem>,
        private val newItems: List<MyImageItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size
        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].imgId == newItems[newItemPosition].imgId

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }


    class MyImageButtonViewHolder(
        private val binding: ItemMyImageButtonBinding,
        private val listener: MenuClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyImageItem) {
            binding.record = item
            binding.tvDtPostWriteImageCount.text = "%s/10".format(item.imgCount)
            Glide.with(binding.ibDtPostWriteCameraButton.context)
                .load(item.imgUrl)
                .into(binding.ibDtPostWriteCameraButton)
            binding.listener = listener
            binding.executePendingBindings() // Ensure the data is bound immediately
        }
    }
}
