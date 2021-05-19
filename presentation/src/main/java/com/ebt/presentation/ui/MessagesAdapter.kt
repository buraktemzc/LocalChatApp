package com.ebt.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebt.common.utils.date.DateUtil
import com.ebt.domain.entity.MessageEntity
import com.ebt.presentation.R
import com.ebt.presentation.databinding.ItemLeftMessageBinding
import com.ebt.presentation.databinding.ItemRightMessageBinding

class MessagesAdapter(private val activeUserId: String) :
    ListAdapter<MessageEntity, RecyclerView.ViewHolder>(DiffCallback()) {
    companion object {
        private const val VIEW_TYPE_LEFT = 0
        private const val VIEW_TYPE_RIGHT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_LEFT) {
            return LeftSideViewHolder(
                ItemLeftMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return RightSideViewHolder(
                ItemRightMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is LeftSideViewHolder)
            holder.bind(item)
        else if (holder is RightSideViewHolder)
            holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        val messageEntity = currentList[position]
        return if (messageEntity.user.id == activeUserId) {
            VIEW_TYPE_RIGHT
        } else {
            VIEW_TYPE_LEFT
        }
    }

    class LeftSideViewHolder(private val binding: ItemLeftMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageEntity) {
            binding.apply {
                binding.messageTextView.text = message.text
                binding.userNameTextView.text = message.user.nickname
                binding.messageTimestamp.text = DateUtil.formatDate(message.timestamp)
                Glide.with(userImageView.context)
                    .load(message.user.avatarURL)
                    .circleCrop()
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .error(R.drawable.ic_profile_placeholder)
                    .into(userImageView)
            }
        }
    }

    class RightSideViewHolder(private val binding: ItemRightMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageEntity) {
            binding.apply {
                binding.messageTextView.text = message.text
                binding.userNameTextView.text = message.user.nickname
                binding.messageTimestamp.text = DateUtil.formatDate(message.timestamp)
                Glide.with(userImageView.context)
                    .load(message.user.avatarURL)
                    .circleCrop()
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .error(R.drawable.ic_profile_placeholder)
                    .into(userImageView)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MessageEntity>() {
        override fun areItemsTheSame(oldItem: MessageEntity, newItem: MessageEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MessageEntity, newItem: MessageEntity): Boolean =
            oldItem == newItem
    }
}