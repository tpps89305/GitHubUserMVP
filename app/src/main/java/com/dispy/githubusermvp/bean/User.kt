package com.dispy.githubusermvp.bean

import com.google.gson.annotations.SerializedName

class User(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val name: String = "",
    val location: String = "",
    val url: String = "",
    val type: String = ""
)