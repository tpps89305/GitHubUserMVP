package com.dispy.githubusermvp

import android.util.Log
import com.dispy.githubusermvp.bean.User
import com.dispy.githubusermvp.function.GithubService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubCommand : IGithubCommand {

    private val gitHubService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }
    private val gson by lazy {
        Gson()
    }

    override fun getUsers(since: Int, listener: OnGithubUsersListener) {
        val call: Call<ResponseBody> = gitHubService.getUsers(since)
        Log.d("Github users", call.request().toString())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val result = gson.fromJson<List<User>>(response.body()!!.string(), object : TypeToken<List<User>>() {}.type)
                listener.onSuccess(result)
                Log.i("Github users", "Success!")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Github users", "Error when get users!")
                Log.w("Github users", t.message!!)
                listener.onFailure()
            }

        })
    }

}