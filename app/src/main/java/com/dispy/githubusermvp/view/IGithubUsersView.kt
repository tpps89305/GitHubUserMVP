package com.dispy.githubusermvp.view

import com.dispy.githubusermvp.bean.User

interface IGithubUsersView {
    fun showLoading()
    fun hideLoading()
    fun getUsers(users: List<User>)
    fun showErrorMessage(message: String)
}