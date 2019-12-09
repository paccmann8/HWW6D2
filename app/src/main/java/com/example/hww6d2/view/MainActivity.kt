package com.example.hww6d2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hww6d2.R
import com.example.hww6d2.adapter.ReposAdaptor
import com.example.hww6d2.model.Repository
import com.example.hww6d2.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GitViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders
            .of(this)
            .get(GitViewModel::class.java)

        compositeDisposable.add(
            viewModel.getRepositories("Paccmann8")
                .subscribe({repository ->
                    displayRepositories(repository)
                },{throwable ->
                    Log.e("Error_Here", throwable.toString())

                })
        )


        compositeDisposable.add(
            viewModel.getCommits("Paccmann8", "AdapterApp")
                .subscribe({repository ->
                    displayCommits(repository)
                },{throwable ->
                    Log.e("Error_Here", throwable.toString())

                })
        )



    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun displayRepositories(repositories: List<Repository>){
        val repo = ReposAdaptor(repositories)
        repo_recycler.adapter = repo
        val linear = LinearLayoutManager(this)
        //linear.orientation = RecyclerView.HORIZONTAL
        repo_recycler.layoutManager = linear
    }


    fun displayCommits(commits: List<Repository>){
        val fragment = RepoFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, fragment)
            .addToBackStack(fragment.tag)
            .commit()

    }

}