package com.goorm.kkiri.ui.chatting.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.databinding.ItemChatMainMessageBinding
import com.goorm.kkiri.domain.model.response.ChatRoom
import com.goorm.kkiri.domain.model.response.ChatRoomItem
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener

class ChatRoomAdapter(
) : RecyclerView.Adapter<ChatRoomAdapter.ChatRoomViewHolder>() {
    private val postItems = mutableListOf<ChatRoomItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        return ChatRoomViewHolder.from(parent)
    }

    override fun getItemCount(): Int = postItems.size

    override fun onBindViewHolder(holder:ChatRoomViewHolder, position: Int) {
        val chatRoomItem = postItems[position]
        holder.bind(chatRoomItem)
    }

    fun update(newItems: List<ChatRoomItem>) {
        val diffUtil = PostListDiffUtil(postItems, newItems)
        val result = DiffUtil.calculateDiff(diffUtil)
        postItems.clear()
        postItems.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }



    class PostListDiffUtil(
        private val oldItems: List<ChatRoomItem>,
        private val newItems: List<ChatRoomItem>
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

    class ChatRoomViewHolder(
        private val binding: ItemChatMainMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(postItem: ChatRoomItem) {
            binding.postItem = postItem
            if (postItem.stateProduct) {
                // True 경우, 왼쪽 뷰 표시
                binding.tvSendMessage.text = postItem.expReceiver
                binding.clSendMessage.visibility = View.VISIBLE
                binding.clReceiveMessage.visibility = View.GONE
                binding.tvSendTime.text = postItem.dateTime
            } else {
                // False 경우, 오른쪽 뷰 표시
                binding.clSendMessage.visibility = View.GONE
                binding.clReceiveMessage.visibility = View.VISIBLE
                binding.tvReceiveTime.text = postItem.dateTime
            }
        }
        companion object {
            fun from(parent: ViewGroup): ChatRoomViewHolder {
                return ChatRoomViewHolder(
                    ItemChatMainMessageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}