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
        const val HISTORY_INSTRUCTION = "Окно \"История\". Здесь вы можете посмотреть историю ваших заявок с их актуальным статусом"
        const val UPDATE_HISTORY_INSTRUCTION = "Чтобы обновить историю вам необходимо провести пальцем вниз."
        const val MENU_HISTORY_INSTRUCTION = "Вы также можете обновить историю через меню. В дальнейшем вы сможете посмотреть инструкцию заново воспользовавшись меню."
        const val OPEN_DETAIL_INSTRUCTION = "Чтобы посмотреть детали заявки вы можете нажать на выделенную иконку."
        const val DETAIL_INSTRUCTION = "Окно детали. Здесь вы можете посмотреть подробные детали выбранной заявки."
        const val CREATE_NEW_LOAN_INSTRUCTION = "Чтобы создать новую заявку вам нужно будет нажать на выделенную кнопку."
        const val CONDITIONS_INSTRUCTION = "Перед оформлением заявки ознакомьтесь с условиями."
        const val FILL_FIELDS_INSTRUCTION =  "Чтобы оформить заявку вам нужно заполнить все поля ( Помните, что сумма должна быть больше нуля )."
        const val NEW_LOAN_INSTRUCTION = "Для создания заявки вам нужно будет нажать на выделенную кнопку."
        const val SUCCESS_LOAN_INSTRUCTION =  "В случае успешного создания заявки вы увидите окно с инструкциями по получению займа."
        const val END_INSTRUCTION = "Спасибо за прохождение обучения. Для продолжения работы нажмите на улыбку. Удачного пользования!!!"
        const val AMOUNT_ZERO = "Сумма должна быть больше нуля"
        const val EMPTY_FIELDS = "Пожалуйста заполните все поля"
        const val UNKNOWN_ERROR = "Что то пошло не так. Проверьте подключение к интернету"
        const val USER_NOT_FOUND = "Пользователя с введенными данными не существует"
        const val USERNAME_TAKEN = "Данное имя пользователя занято"
        const val USER_CREATE = "Пользователь успешно создан"
        const val INCORRECT_DATA = "Неверные данные"
    }
}