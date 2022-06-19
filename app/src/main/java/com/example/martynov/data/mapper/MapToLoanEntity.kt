package com.example.martynov.data.mapper

import com.example.martynov.data.model.LoanDTO
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.State

class MapToLoanEntity {

    companion object {
        operator fun invoke(loanDTO: LoanDTO): LoanEntity =
            LoanEntity(
                amount = loanDTO.amount,
                date = loanDTO.date,
                firstName = loanDTO.firstName,
                id = loanDTO.id,
                lastName = loanDTO.lastName,
                percent = loanDTO.percent,
                period = loanDTO.period,
                phoneNumber = loanDTO.phoneNumber,
                state = mapToResultStatus(loanDTO.state)
            )

        private fun mapToResultStatus(state: String): State = when (state) {
            "APPROVED" -> State.APPROVED
            "REGISTERED" -> State.REGISTERED
            "REJECTED" -> State.REJECTED
            else -> throw IllegalStateException()
        }
    }
}