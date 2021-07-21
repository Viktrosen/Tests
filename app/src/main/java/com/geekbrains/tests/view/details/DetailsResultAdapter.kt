package com.geekbrains.tests.view.details

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.tests.R
import com.geekbrains.tests.model.room.entity.Repository
import kotlinx.android.synthetic.main.repository_list_item.view.*

class DetailsResultAdapter() :
    RecyclerView.Adapter<DetailsResultAdapter.DetailsResultViewHolder>() {
    private var results: List<Repository> = listOf()

    inner class DetailsResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: Repository) {
            itemView.ownerName.text = repository.userName
            itemView.repoName.text = repository.repositoryName
        }
    }

    fun updateResults(results: List<Repository>) {
        this.results = results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsResultViewHolder {
        return DetailsResultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repository_list_item, null)
        )
    }

    override fun onBindViewHolder(
        holder: DetailsResultViewHolder,
        position: Int
    ) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int {
        if (results.isEmpty()) {
            Log.d(javaClass.name, "База пустая или в массив ничего не попало")
        }
        return results.size
    }
}