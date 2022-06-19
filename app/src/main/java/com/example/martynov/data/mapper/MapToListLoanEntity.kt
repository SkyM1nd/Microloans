package com.example.martynov.data.mapper

import com.example.martynov.data.model.LoanDTO
import com.example.martynov.domain.entity.LoanEntity

class MapToListLoanEntity {

    companion object {

        operator fun invoke(list: List<LoanDTO>): List<LoanEntity> =
            list.map {
                MapToLoanEntity(it)
            }
    }
}