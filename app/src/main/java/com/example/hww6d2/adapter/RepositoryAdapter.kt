package com.example.hww6d2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hww6d2.R
import com.example.hww6d2.model.Repository
import org.w3c.dom.Text


class ReposAdaptor(val repoList: List<Repository> ):
    RecyclerView.Adapter<ReposAdaptor.RepoViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.repo_item_layout, parent, false)


        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

        holder.apply {
            repo_name.text = repoList[position].name

        }

        holder.itemView.setOnClickListener {
            holder.itemView.context.applicationContext.sendBroadcast(Intent("from.rv.view").also {
                it.putExtra("repositoryName", repoList[position].name)
            })
        }

    }

    override fun getItemCount(): Int {
        return repoList.size
    }


    inner class RepoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val repo_name: TextView = itemView.findViewById(R.id.repo_name_textvew)

    }

}
