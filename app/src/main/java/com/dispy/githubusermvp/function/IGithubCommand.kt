package com.dispy.githubusermvp.function

interface IGithubCommand {

    fun getUsers(since: Int, listener: OnGithubUsersListener)
    fun getUserDetail(login: String, listener: OnGithubUserDetailListener)

}