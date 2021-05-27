package com.dispy.githubusermvp.function

import com.dispy.githubusermvp.bean.User

interface OnGithubUsersListener {
    fun onSuccess(result: List<User>)
    fun onFailure(message: String)
}