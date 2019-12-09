package com.example.hww6d2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hww6d2.model.Second
import com.example.hww6d2.model.Repository
import com.example.hww6d2.network.GitFactory
import com.example.hww6d2.network.SecondFactory
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GitViewModel: ViewModel() {

    private val gitFactory = GitFactory()



    fun getRepositories(user:String):Observable<List<Repository>>{

        return gitFactory.getRepositpories(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    fun getCommits(user: String, repo: String):Observable<List<Repository>>{

        return gitFactory.getCommits(user, repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}