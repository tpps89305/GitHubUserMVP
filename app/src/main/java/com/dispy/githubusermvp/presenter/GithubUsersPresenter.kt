package com.dispy.githubusermvp.presenter

import com.dispy.githubusermvp.GithubCommand
import com.dispy.githubusermvp.OnGithubUsersListener
import com.dispy.githubusermvp.bean.User
import com.dispy.githubusermvp.view.IGithubUsersView

class GithubUsersPresenter(iView: IGithubUsersView) {

    private var mView: IGithubUsersView = iView
    private val command: GithubCommand by lazy {
        GithubCommand()
    }

    fun getUsers(since: Int) {
        mView.showLoading()
        command.getUsers(since, object : OnGithubUsersListener{
            override fun onSuccess(result: List<User>) {
                mView.hideLoading()
                mView.getUsers(result)
            }

            override fun onFailure(message: String) {
                mView.hideLoading()
                mView.showErrorMessage(message)
            }

        })
    }

}