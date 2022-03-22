package br.keneitec.dio.apresentarrepositoriogithub.domain

import br.keneitec.dio.apresentarrepositoriogithub.core.UseCase
import br.keneitec.dio.apresentarrepositoriogithub.data.model.Repo
import br.keneitec.dio.apresentarrepositoriogithub.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(
    private val repository: RepoRepository
) : UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}