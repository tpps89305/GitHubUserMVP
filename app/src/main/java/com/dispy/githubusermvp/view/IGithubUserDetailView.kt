package com.dispy.githubusermvp.view

import com.dispy.githubusermvp.bean.User

interface IGithubUserDetailView {
    fun showLoading()
    fun hideLoading()
    fun getUser(user: User)
    fun showErrorMessage(message: String)
}