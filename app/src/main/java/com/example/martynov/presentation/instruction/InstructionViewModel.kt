package com.example.martynov.presentation.instruction

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.core.animation.doOnEnd
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionViewModel : ViewModel() {

    private val _historyAnimStepTwo = MutableLiveData<ObjectAnimator>()
    val historyAnimStepTwo: LiveData<ObjectAnimator> = _historyAnimStepTwo

    private val _historyAnimStepThree = MutableLiveData<ObjectAnimator>()
    val historyAnimStepThree: LiveData<ObjectAnimator> = _historyAnimStepThree

    private val _historyAnimStepFour = MutableLiveData<ObjectAnimator>()
    val historyAnimStepFour: LiveData<ObjectAnimator> = _historyAnimStepFour

    private val _historyAnimatorSet = MutableLiveData<AnimatorSet>()
    val historyAnimatorSet: LiveData<AnimatorSet> = _historyAnimatorSet

    fun startAnimatorSet(
        animHandAlphaToMax: ObjectAnimator,
        animHandDown: ObjectAnimator,
        animHandAlphaToMin: ObjectAnimator,
        animHandUp: ObjectAnimator
    ) {
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            animHandAlphaToMax,
            animHandDown,
            animHandAlphaToMin,
            animHandUp
        )
        _historyAnimatorSet.value = animatorSet
        _historyAnimatorSet.value!!.start()

        animHandDown.doOnEnd {
            _historyAnimStepTwo.value = animHandDown
        }

        animHandAlphaToMin.doOnEnd {
            _historyAnimStepThree.value = animHandAlphaToMin
        }

        animHandUp.doOnEnd {
            _historyAnimStepFour.value = animHandUp
        }

        animatorSet.doOnEnd {
            animatorSet.start()
        }
    }

    fun stopAnimatorSet() {
        if (_historyAnimatorSet.value != null) {
            _historyAnimatorSet.value!!.removeAllListeners()
            _historyAnimatorSet.value!!.end()
            _historyAnimatorSet.value!!.cancel()
        }
    }
}