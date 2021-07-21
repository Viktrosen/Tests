package com.geekbrains.tests.view.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.tests.R
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModel()
    private val adapter = DetailsResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setRecyclerView()
        viewModel.getData()
        viewModel.subscribeToLiveData().observe(this) { onStateChange(it) }
    }

    private fun onStateChange(screenState: ScreenState) {
        when (screenState) {
            is ScreenState.Working -> {
                val dbResponse = screenState.databaseResponse
                detailsProgressBar.visibility = View.GONE
                adapter.updateResults(dbResponse)
            }
            is ScreenState.Loading -> {
                detailsProgressBar.visibility = View.VISIBLE
            }
            is ScreenState.Error -> {
                detailsProgressBar.visibility = View.GONE
                Log.d("DetailsActivity", screenState.error.message.toString())
                Toast.makeText(this, screenState.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRecyclerView() {
        detailsRecyclerView.setHasFixedSize(true)
        detailsRecyclerView.adapter = adapter
    }
}
