package com.dispy.githubusermvp.function

import android.util.Log
import com.dispy.githubusermvp.bean.User
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

                if (response.errorBody() != null) {
                    if (response.errorBody()!!.string().contains("API rate limit exceeded")) {
                        Log.e("Github users", "API rate limit exceeded")
                        listener.onFailure("API rate limit exceeded")
                    }
                }

                if (response.body() != null) {
                    val result = gson.fromJson<List<User>>(response.body()!!.string(), object : TypeToken<List<User>>() {}.type)
                    listener.onSuccess(result)
                    Log.i("Github users", "Success when get users!")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Github users", "Error when get users!")
                Log.w("Github users", t.message!!)
                listener.onFailure("Error when get users!")
            }

        })
    }

    override fun getUserDetail(login: String, listener: OnGithubUserDetailListener) {
        val call: Call<User> = gitHubService.getUserDetail(login)
        Log.d("Github users", call.request().toString())
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.errorBody() != null) {
                    if (response.errorBody()!!.string().contains("API rate limit exceeded")) {
                        Log.e("Github user detail", "API rate limit exceeded")
                        listener.onFailure("API rate limit exceeded")
                    }
                }

                if (response.body() != null) {
                    listener.onSuccess(response.body()!!)
                    Log.i("Github user detail", "Success to get $login's detail!")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.w("Github user detail", "Error when get $login's detail!")
                Log.w("Github user detail", t.message!!)
                listener.onFailure("Error when get $login's detail!")
            }

        })
    }

}