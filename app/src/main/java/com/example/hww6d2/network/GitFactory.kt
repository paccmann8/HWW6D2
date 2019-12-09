package com.example.hww6d2.network

import com.example.hww6d2.model.Repository
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitFactory {

    val BASE_URL = "https://api.github.com"
    lateinit var gitService: GitService

    init {
        gitService = createService(retrofitInstance())
    }

    private fun retrofitInstance(): Retrofit{

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createService(retrofit: Retrofit): GitService{
        return retrofit.create(GitService::class.java)
    }

    fun getRepositpories(username: String): Observable<List<Repository>>{

        return gitService.getUsers(username)
    }

    fun getCommits(user: String, repoName: String):Observable<List<Repository>>{

        return gitService.getCommits(user, repoName)
    }

}