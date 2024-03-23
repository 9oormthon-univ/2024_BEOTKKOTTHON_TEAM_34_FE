package com.goorm.kkiri.ui.chatting.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemChatMessageBinding
import com.goorm.kkiri.domain.model.response.ChatMain
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener

class ChatMainAdapter(
    private val listener: MenuClickListener
) : RecyclerView.Adapter<ChatMainAdapter.ChatMainViewHolder>() {
    private val postItems = mutableListOf<ChatMain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMainViewHolder {
        return ChatMainViewHolder.from(parent)
    }

    override fun getItemCount(): Int = postItems.size

    override fun onBindViewHolder(holder: ChatMainViewHolder, position: Int) {
        holder.bind(postItems[position], listener)
    }

    fun update(newItems: List<ChatMain>) {
        val diffUtil = PostListDiffUtil(postItems, newItems)
        val result = DiffUtil.calculateDiff(diffUtil)
        postItems.clear()
        postItems.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    class PostListDiffUtil(
        private val oldItems: List<ChatMain>,
        private val newItems: List<ChatMain>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem.chatId == newItem.chatId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }

    }

    class ChatMainViewHolder(
        private val binding: ItemChatMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(postItem: ChatMain, listener: MenuClickListener) {
            binding.postItem = postItem
            binding.listener = listener
        }

        companion object {
            fun from(parent: ViewGroup): ChatMainViewHolder {
                return ChatMainViewHolder(
                    ItemChatMessageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}