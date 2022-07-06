package com.example.martynov.di

import com.example.martynov.ui.LoginActivity
import com.example.martynov.ui.fragment.LoanHistoryFragment
import com.example.martynov.ui.fragment.NewLoanFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(loanHistoryFragment: LoanHistoryFragment)

    fun inject(newLoanFragment: NewLoanFragment)
}