package com.dispy.githubusermvp

interface IGithubCommand {

    fun getUsers(since: Int, listener: OnGithubUsersListener)
    fun getUserDetail(login: String, listener: OnGithubUserDetailListener)

}