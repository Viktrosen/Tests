package com.geekbrains.tests.view.search

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.tests.R
import com.geekbrains.tests.model.api.entity.SearchResult
import com.geekbrains.tests.model.room.entity.Repository
import com.geekbrains.tests.view.search.SearchResultAdapter.SearchResultViewHolder
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.*


internal class SearchResultAdapter(val context: Context, val viewModel: SearchViewModel) :
    RecyclerView.Adapter<SearchResultViewHolder>() {

    private var results: List<SearchResult> = listOf()
    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> (throwable) })

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        )
    }

    override fun onBindViewHolder(
        holder: SearchResultViewHolder,
        position: Int
    ) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun updateResults(results: List<SearchResult>) {
        this.results = results
        notifyDataSetChanged()
    }

    inner class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(searchResult: SearchResult) {
            itemView.repositoryName.text = searchResult.fullName
            itemView.load_img.visibility = if (!searchResult.private!!) View.VISIBLE else View.GONE
            itemView.repositoryName.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse("https://github.com/" + searchResult.fullName)
                context.startActivity(i)
                Toast.makeText(
                    itemView.context,
                    searchResult.fullName + "ADAPTER",
                    Toast.LENGTH_SHORT
                ).show()
            }
            itemView.load_img.setOnClickListener {
                viewModelCoroutineScope.launch {
                    val owner = searchResult.owner.login
                    val name = searchResult.name
                    viewModel.downloadFile(
                        "https://api.github.com/repos/$owner/$name/zipball",
                        name ?: ""
                    )
                    viewModel.addRepos(
                        Repository(
                            searchResult.id ?: 0,
                            searchResult.owner.login,
                            searchResult.name
                        )
                    )
                }
            }
        }
    }
}
