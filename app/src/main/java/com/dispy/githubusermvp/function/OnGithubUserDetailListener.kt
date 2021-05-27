package com.dispy.githubusermvp.function

import com.dispy.githubusermvp.bean.User

interface OnGithubUserDetailListener {
    fun onSuccess(result: User)
    fun onFailure(message: String)
}