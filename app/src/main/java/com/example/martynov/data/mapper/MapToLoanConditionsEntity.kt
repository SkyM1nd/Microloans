package com.example.martynov.data.mapper

import com.example.martynov.data.model.LoanConditionsDTO
import com.example.martynov.domain.entity.LoanConditionsEntity

class MapToLoanConditionsEntity {

    companion object {

        operator fun invoke(loanConditionsDTO: LoanConditionsDTO): LoanConditionsEntity =
            LoanConditionsEntity(
                maxAmount = loanConditionsDTO.maxAmount,
                percent = loanConditionsDTO.percent,
                period = loanConditionsDTO.period
            )
    }
}