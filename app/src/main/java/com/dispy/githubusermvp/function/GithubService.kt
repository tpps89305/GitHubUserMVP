package com.dispy.githubusermvp.function

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    fun getUsers(@Query("since") since: Int): Call<ResponseBody>
}