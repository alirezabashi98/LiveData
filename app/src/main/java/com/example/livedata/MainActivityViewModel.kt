package com.example.livedata

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer


    var finished = MutableLiveData<Boolean>()

    val timerValue = MutableLiveData<Long>()

    private val _seconds = MutableLiveData<Int>()
    fun seconds(): LiveData<Int> = _seconds

    fun startTimer() {

        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timerLeft = millisUntilFinished / 1000
                _seconds.value = timerLeft.toInt()
            }

            override fun onFinish() {
                finished.value = true
            }
        }.start()

    }

    fun stopTimer() {
        timer.cancel()
    }

}