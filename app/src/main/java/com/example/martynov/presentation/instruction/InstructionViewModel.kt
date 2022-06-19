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
        anim1: ObjectAnimator,
        anim2: ObjectAnimator,
        anim3: ObjectAnimator,
        anim4: ObjectAnimator
    ) {
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            anim1,
            anim2,
            anim3,
            anim4
        )
        _historyAnimatorSet.value = animatorSet
        _historyAnimatorSet.value!!.start()

        anim2.doOnEnd {
            _historyAnimStepTwo.value = anim2
        }

        anim3.doOnEnd {
            _historyAnimStepThree.value = anim3
        }

        anim4.doOnEnd {
            _historyAnimStepFour.value = anim4
        }

        animatorSet.doOnEnd {
            animatorSet.start()
        }
    }

    fun stopAnimatorSet() {
        if (_historyAnimatorSet.value != null) {
            _historyAnimatorSet.value!!.removeAllListeners();
            _historyAnimatorSet.value!!.end();
            _historyAnimatorSet.value!!.cancel();
        }
    }
}