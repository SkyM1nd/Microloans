package com.example.martynov.data.mapper

import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.entity.ResultStatus

class MapFlowResult {

    companion object {

        fun <T, V> mapFlowResult(
            result: RepositoryResult<T>,
            mapper: (T) -> V
        ): RepositoryResult<V> =
            when (result.status) {
                ResultStatus.SUCCESS -> RepositoryResult.Success(
                    mapper(
                        requireNotNull(
                            result.data
                        )
                    )
                )
                ResultStatus.ERROR -> RepositoryResult.Error(requireNotNull(result.message))
                ResultStatus.LOADING -> RepositoryResult.Loading(true)
            }
    }
}