package br.keneitec.dio.apresentarrepositoriogithub.data.repositories

import br.keneitec.dio.apresentarrepositoriogithub.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user:String):Flow<List<Repo>>
}