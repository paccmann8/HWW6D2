package com.example.hww6d2.network

import com.example.hww6d2.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface GitService {

    @GET("users/{user_name}/repos")
    fun getUsers (@Path("user_name")userName: String):
            Observable<List<Repository>>


    @GET("repos/{user_name}/{repo_name}/commits")
    fun getCommits(@Path("user_name")user : String, @Path("repo_name")repo: String):
            Observable<List<Repository>>

}