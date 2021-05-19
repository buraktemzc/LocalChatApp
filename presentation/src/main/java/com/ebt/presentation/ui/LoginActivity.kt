package com.ebt.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ebt.common.extensions.toast
import com.ebt.common.utils.network.NetWorkStatus
import com.ebt.common.utils.network.Result
import com.ebt.presentation.R
import com.ebt.presentation.databinding.ActivityLoginBinding
import com.ebt.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.loginButton.setOnClickListener {
            if (NetWorkStatus.isNetworkAvailable(this)) {
                viewModel.checkNicknameValidationCorrect(
                    binding.nicknameEditText.text.toString().trim()
                )
            } else {
                toast(R.string.error_internet_connection)
            }
        }
    }

    private fun initObservers() {
        viewModel.sessionSaved.observe(this, Observer {
            if (it) {
                viewModel.getMessagesFromRemote()
            }
        })

        viewModel.nicknameValidationCorrect.observe(this, Observer {
            if (it)
                viewModel.saveCurrentSession(binding.nicknameEditText.text.toString())
            else
                toast(R.string.error_nickname_length)
        })

        viewModel.sessionExist.observe(this, Observer {
            if (it) {
                if (NetWorkStatus.isNetworkAvailable(this)) {
                    viewModel.getMessagesFromRemote()
                } else {
                    toast(getString(R.string.error_internet_connection) + " " + getString(R.string.session_cleared))
                    viewModel.clearUserSession()
                }
            }
        })

        viewModel.messages.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    hideProgressBar()
                    viewModel.saveMessages(it.data!!)
                }
                is Result.Error -> {
                    hideProgressBar()
                    toast("Error: $it")
                }
                is Result.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.dbSynced.observe(this, Observer {
            if (it) {
                binding.nicknameEditText.setText("")
                showMessagesScreen()
            }
        })
    }

    private fun showMessagesScreen() {
        val intent = Intent(this, MessagesActivity::class.java)
        intent.putExtra(MessagesActivity.ARG_USER_NICKNAME, viewModel.userNickname.value)
        startActivity(intent)
    }

    private fun showProgressBar() {
        binding.loginButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.loginButton.isEnabled = true
        binding.progressBar.visibility = View.INVISIBLE
    }
}