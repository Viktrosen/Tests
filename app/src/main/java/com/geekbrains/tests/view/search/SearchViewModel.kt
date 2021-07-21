package com.geekbrains.tests.view.search

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.tests.model.api.entity.SearchResult
import com.geekbrains.tests.model.room.database.AppDatabase
import com.geekbrains.tests.model.room.entity.Repository
import com.geekbrains.tests.repository.RepositoryContract
import com.geekbrains.tests.repository.DatabaseRepository
import kotlinx.coroutines.*

class SearchViewModel(val repository: RepositoryContract ,val repositoryDB: DatabaseRepository) : ViewModel() {

    private val _liveData = MutableLiveData<ScreenState>()
    private val liveData: LiveData<ScreenState> = _liveData
    @SuppressLint("StaticFieldLeak")
    private lateinit var context:Context
    private val viewModelCoroutineScope1 = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> handleError(throwable) })

    fun subscribeToLiveData() = liveData
    fun setContext(context: Context){
        this.context = context
    }

    fun searchGitHub(searchQuery: String) {
        _liveData.value = ScreenState.Loading
        viewModelCoroutineScope1.launch {
            val searchResponse = repository.searchGithubUser(repository.searchGithubAsync(searchQuery).items[0].login)
            val searchResults = repository.searchGithubAsync(searchQuery)


            if (searchResults != null ) {
                _liveData.value = ScreenState.Working(searchResponse)
            } else {
                _liveData.value =
                    ScreenState.Error(Throwable("Search results or total count are null"))
            }
        }
    }

    suspend fun addRepos(repository: Repository){
        repositoryDB.insertData(repository)
    }
    fun downloadFile(url:String,fileName:String){
        val downloadmanager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setTitle(fileName)
        request.setDescription("Download")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"$fileName.zip")
        downloadmanager!!.enqueue(request)
    }

    private fun handleError(error: Throwable) {
        _liveData.value =
            ScreenState.Error(
                Throwable(
                    error.message+javaClass.canonicalName ?: "Response is null or unsuccessful"
                )
            )
    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope1.coroutineContext.cancelChildren()
    }
}
sealed class ScreenState {
    object Loading : ScreenState()
    data class Working(val searchResponse: List<SearchResult>) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
}
