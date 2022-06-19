package com.example.martynov.data.mapper

import com.example.martynov.data.model.LoanDTO
import com.example.martynov.domain.entity.LoanEntity

class MapToLoanDTO {

    companion object {

        operator fun invoke(loanEntity: LoanEntity): LoanDTO =
            LoanDTO(
                amount = loanEntity.amount,
                date = loanEntity.date,
                firstName = loanEntity.firstName,
                id = loanEntity.id,
                lastName = loanEntity.lastName,
                percent = loanEntity.percent,
                period = loanEntity.period,
                phoneNumber = loanEntity.phoneNumber,
                state = loanEntity.state.toString()
            )
    }
}