package extension

import repository.FakeGitHubRepository
import com.geekbrains.tests.repository.RepositoryContract


fun createRepository(): RepositoryContract {
        return FakeGitHubRepository()
        }




