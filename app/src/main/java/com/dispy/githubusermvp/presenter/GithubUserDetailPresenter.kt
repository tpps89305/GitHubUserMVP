package com.dispy.githubusermvp.presenter

import com.dispy.githubusermvp.GithubCommand
import com.dispy.githubusermvp.OnGithubUserDetailListener
import com.dispy.githubusermvp.bean.User
import com.dispy.githubusermvp.view.IGithubUserDetailView

class GithubUserDetailPresenter(iView: IGithubUserDetailView) {

    private var mView = iView
    private val command: GithubCommand by lazy {
        GithubCommand()
    }

    fun getUserDetail(login: String) {
        mView.showLoading()
        command.getUserDetail(login, object : OnGithubUserDetailListener {
            override fun onSuccess(result: User) {
                mView.hideLoading()
                mView.getUser(result)
            }

            override fun onFailure() {
                mView.hideLoading()
                mView.showErrorMessage()
            }

        })
    }
}