package com.dispy.githubusermvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private var totalItemsCount: Int = 0
    private var isLoading: Boolean = false
    private var userIdLastSeen: Int = 0
    private var currentPage: Int = 0

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
                val intent = Intent(applicationContext, UserDetailActivity::class.java)
                intent.putExtra("login", login)
                startActivity(intent)
            }

        })
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastItemsPosition = linearLayoutManager.findLastVisibleItemPosition()
                if (totalItemsCount <= lastItemsPosition + 1) {
                    if (!isLoading && currentPage < 20) {
                        Log.i("MainActivity", "Download more data, last user ID = $userIdLastSeen, page $currentPage")
                        presenter.getUsers(userIdLastSeen)
                        currentPage++
                    }
                    else if (currentPage >= 20) {
                        Log.i("MainActivity", "Page amount limit is 20, don't load more data.")
                        binding.recyclerView.removeOnScrollListener(this)
                    }
                }
            }
        })
        presenter.getUsers(0)
    }

    override fun showLoading() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }

    override fun getUsers(users: List<User>) {
        userAdapter.swapItems(users)
        userIdLastSeen = users.last().id
        totalItemsCount += users.size
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}