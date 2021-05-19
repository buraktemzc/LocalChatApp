package com.ebt.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebt.presentation.databinding.ActivityMessagesBinding
import com.ebt.presentation.viewmodel.MessagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesActivity : AppCompatActivity() {
    companion object {
        const val ARG_USER_NICKNAME = "arg-nickname"
    }

    private lateinit var binding: ActivityMessagesBinding
    private lateinit var messagesAdapter: MessagesAdapter
    private val viewModel: MessagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userNickname = intent.getStringExtra(ARG_USER_NICKNAME)

        initListeners()
        initObservers()

        userNickname?.let {
            viewModel.setActiveUser(userNickname)
        }
    }

    override fun onBackPressed() {
        viewModel.clearUserSession()
    }

    private fun initRecyclerView() {
        val activeUser = viewModel.activeUser.value
        activeUser?.let {
            messagesAdapter = MessagesAdapter(activeUser.id)
            binding.apply {
                recyclerView.apply {
                    adapter = messagesAdapter
                    layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        true
                    ).apply { stackFromEnd = true }
                }
            }
        }
    }

    private fun initListeners() {
        binding.backButton.setOnClickListener {
            viewModel.clearUserSession()
        }

        binding.sendButton.setOnClickListener {
            viewModel.saveMessage(binding.messageEditText.text.toString().trim())
        }
    }

    private fun initObservers() {
        viewModel.sessionCleared.observe(this, Observer {
            if (it)
                super.onBackPressed()
        })

        viewModel.getAllMessagesFromDB().observe(this, Observer {
            messagesAdapter.submitList(it)
            binding.recyclerView.postDelayed(
                { binding.recyclerView.smoothScrollToPosition(0) },
                300
            )
        })

        viewModel.activeUser.observe(this, Observer {
            if (it != null) {
                binding.userTextView.text = it.nickname
                initRecyclerView()
            }
        })

        viewModel.newMessageSent.observe(this, Observer {
            if (it) {
                binding.messageEditText.setText("")
            }
        })
    }

}