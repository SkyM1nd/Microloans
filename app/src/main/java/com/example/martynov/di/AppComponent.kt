package com.example.martynov.di

import com.example.martynov.ui.LoanActivity
import com.example.martynov.ui.LoginActivity
import com.example.martynov.ui.fragment.LoanHistoryFragment
import com.example.martynov.ui.fragment.NewLoanFragment
import com.example.martynov.ui.fragment.SuccessLoanCreateFragment
import com.example.martynov.ui.instruction.InstructionActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(loanActivity: LoanActivity)

    fun inject(instructionActivity: InstructionActivity)

    fun inject(loanHistoryFragment: LoanHistoryFragment)

    fun inject(newLoanFragment: NewLoanFragment)

    fun inject(successLoanCreateFragment: SuccessLoanCreateFragment)
}