package com.example.martynov.di

import com.example.martynov.ui.LoginActivity
import com.example.martynov.ui.fragments.LoanDetailFragment
import com.example.martynov.ui.fragments.LoanHistoryFragment
import com.example.martynov.ui.fragments.NewLoanFragment
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