package com.example.martynov.data.mapper

import com.example.martynov.data.model.NewLoanDTO
import com.example.martynov.domain.entity.NewLoanEntity

class MapToNewLoanDTO {

    companion object {
        operator fun invoke(newLoanEntity: NewLoanEntity): NewLoanDTO =
            NewLoanDTO(
                amount = newLoanEntity.amount,
                firstName = newLoanEntity.firstName,
                lastName = newLoanEntity.lastName,
                period = newLoanEntity.period,
                percent = newLoanEntity.percent,
                phoneNumber = newLoanEntity.phoneNumber
            )
    }
}