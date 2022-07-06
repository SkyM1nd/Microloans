package com.example.martynov.utils

class Constants {

    companion object {
        const val SHARED_PREFERENCES_NAME = "Flag"
        const val TOKEN_KEY = "token"
        const val IS_FIRST_LOGIN_KEY = "firstLogin"
        const val URI = "https://shiftlab.cft.ru:7777"
        const val DATE_FORMAT_PATTERN = "d MMMM yyyy"
        const val LANGUAGE = "RUS"
        const val LOAN_DETAIL = "LoanDetail"
        const val ADD_NEW_LOAN_FRAGMENT = "Add NewLoanFragment"
        const val ADD_LOAN_DETAIL_FRAGMENT = "Add LoanDetailFragment"
        const val ADD_SUCCESS_LOAN_CREATE_FRAGMENT = "Add SuccessLoanCreateFragment"
        const val ADD_LOAN_HISTORY_FRAGMENT = "Add LoanHistoryFragment"
        const val INSTRUCTION_TITLE = " ( Инструкция )"
        const val AMOUNT_ZERO = "Сумма должна быть больше нуля"
        const val EMPTY_FIELDS = "Пожалуйста заполните все поля"
        const val UNKNOWN_ERROR = "Что то пошло не так. Проверьте подключение к интернету"
        const val USER_NOT_FOUND = "Пользователя с введенными данными не существует"
        const val USERNAME_TAKEN = "Данное имя пользователя занято"
        const val USER_CREATE = "Пользователь успешно создан"
        const val INCORRECT_DATA = "Неверные данные"
    }
}