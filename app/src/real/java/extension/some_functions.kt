package extension

import com.geekbrains.tests.repository.GitHubApi
import repository.GitHubRepository
import com.geekbrains.tests.repository.RepositoryContract
import com.geekbrains.tests.view.search.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


 fun createRepository(): RepositoryContract {
        return GitHubRepository(createRetrofit().create(GitHubApi::class.java))
        }

private fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(MainActivity.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


