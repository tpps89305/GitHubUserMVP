package com.dispy.githubusermvp

import com.dispy.githubusermvp.bean.User

interface OnGithubUserDetailListener {
    fun onSuccess(result: User)
    fun onFailure()
}