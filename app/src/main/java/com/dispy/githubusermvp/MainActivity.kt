package com.dispy.githubusermvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.githubusermvp.bean.User
import com.dispy.githubusermvp.databinding.ActivityMainBinding
import com.dispy.githubusermvp.presenter.GithubUsersPresenter
import com.dispy.githubusermvp.ui.UserAdapter
import com.dispy.githubusermvp.view.IGithubUsersView

class MainActivity : AppCompatActivity(), IGithubUsersView {

    private val userAdapter: UserAdapter =
        UserAdapter(this, ArrayList())
    private lateinit var binding: ActivityMainBinding
    private val presenter: GithubUsersPresenter by lazy {
        GithubUsersPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = userAdapter
        userAdapter.setOnClickListener(object : UserAdapter.OnClickListener {
            override fun onItemClick(login: String) {
                // TODO: Github user detail
            }

        })

        presenter.getUsers(0)
    }

    override fun showLoading() {
        // TODO: Show Loading Dialog
    }

    override fun hideLoading() {
        // TODO: Hide Loading Dialog
    }

    override fun getUsers(users: List<User>) {
        userAdapter.swapItems(users)
    }

    override fun showErrorMessage() {
        Toast.makeText(applicationContext, "Error when get users!", Toast.LENGTH_LONG).show()
    }
}