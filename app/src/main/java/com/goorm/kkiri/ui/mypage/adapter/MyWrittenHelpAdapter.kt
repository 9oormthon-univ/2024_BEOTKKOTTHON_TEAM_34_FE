package com.goorm.kkiri.ui.mypage.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.HelpitemBinding
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem

@RequiresApi(Build.VERSION_CODES.O)
class MyWrittenHelpAdapter(
    private val listener: MenuClickListener
) : RecyclerView.Adapter<MyWrittenHelpAdapter.MyWrittenHelpViewHolder>() {
    private val items = mutableListOf<MyWrittenMenuItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyWrittenHelpViewHolder {
        return MyWrittenHelpViewHolder.from(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyWrittenHelpViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    fun update(posts: List<MyWrittenMenuItem>) {
        val diffUtil = RecordSaveDiffUtil(items, posts)
        val result = DiffUtil.calculateDiff(diffUtil)
        items.clear()
        items.addAll(posts)
        result.dispatchUpdatesTo(this)
    }

    class RecordSaveDiffUtil(
        private val oldItems: List<MyWrittenMenuItem>,
        private val newItems: List<MyWrittenMenuItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size
        override fun getNewListSize(): Int = newItems.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem.recordIdWt == newItem.recordIdWt
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }

    class MyWrittenHelpViewHolder(
        private val binding: HelpitemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: MyWrittenMenuItem, listener: MenuClickListener) {
            binding.record = post
            binding.root.setOnClickListener {
                listener.menuClickListener(adapterPosition.toLong())
            }
        }

        companion object {
            fun from(parent: ViewGroup): MyWrittenHelpViewHolder {
                return MyWrittenHelpViewHolder(
                    HelpitemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}
