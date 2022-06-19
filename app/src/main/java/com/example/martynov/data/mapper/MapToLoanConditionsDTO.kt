package com.example.martynov.data.mapper

import com.example.martynov.data.model.LoanConditionsDTO
import com.example.martynov.domain.entity.LoanConditionsEntity

class MapToLoanConditionsDTO {

    companion object {

        operator fun invoke(loanConditionsEntity: LoanConditionsEntity): LoanConditionsDTO =
            LoanConditionsDTO(
                maxAmount = loanConditionsEntity.maxAmount,
                percent = loanConditionsEntity.percent,
                period = loanConditionsEntity.period
            )
    }
}