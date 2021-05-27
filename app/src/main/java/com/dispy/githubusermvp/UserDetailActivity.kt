package com.dispy.githubusermvp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.dispy.githubusermvp.bean.User
import com.dispy.githubusermvp.databinding.ActivityUserDetailBinding
import com.dispy.githubusermvp.presenter.GithubUserDetailPresenter
import com.dispy.githubusermvp.view.IGithubUserDetailView

class UserDetailActivity: AppCompatActivity(), IGithubUserDetailView {

    private lateinit var binding: ActivityUserDetailBinding
    private val presenter: GithubUserDetailPresenter by lazy {
        GithubUserDetailPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.getUserDetail(intent.getStringExtra("login")!!)
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    override fun showLoading() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar2.visibility = View.GONE
    }

    override fun getUser(user: User) {
        binding.imageAvatar.load(user.avatarUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.textName.text = user.name
        binding.textBio.text = user.bio
        binding.textPerson.text = user.login
        binding.textLocation.text = user.location
        binding.textLink.text = user.url
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}