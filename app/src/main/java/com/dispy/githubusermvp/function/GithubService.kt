package com.dispy.githubusermvp.function

import com.dispy.githubusermvp.bean.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    fun getUsers(@Query("since") since: Int): Call<ResponseBody>

    @GET("users/{login}")
    fun getUserDetail(@Path("login") login: String): Call<User>
}