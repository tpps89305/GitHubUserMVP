package com.dispy.githubusermvp

interface IGithubCommand {

    fun getUsers(since: Int, listener: OnGithubUsersListener)

}