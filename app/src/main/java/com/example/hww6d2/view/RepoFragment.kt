package com.example.hww6d2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hww6d2.R
import com.example.hww6d2.adapter.CommitAdapter
import com.example.hww6d2.model.Commit
import com.example.hww6d2.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.repo_fragment_layout.*

class RepoFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var viewModel: GitViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GitViewModel::class.java)

        arguments?.let {
            title_textview.text = it.getString("repositoryName")
            compositeDisposable.add(
                viewModel.getCommits(
                    "Dalo-Chinkhwangwa-Prof",
                    it.getString("repositoryName") ?: ""
                ).subscribe({
                    displayResults(it)
                }, { throwable ->
                    Log.e("TAG_ERROR", throwable.message)
                })
            )
        }
    }

    private fun displayResults(commitList: List<Commit>){

        commit_recyclerview.adapter = CommitAdapter(commitList)
        commit_recyclerview.layoutManager = LinearLayoutManager(activity)
    }

}