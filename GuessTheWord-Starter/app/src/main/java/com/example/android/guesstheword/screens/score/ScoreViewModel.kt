package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {   //加入參數表示這是一個constructor的參數
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
       get() = _score

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")   // 重要
        _score.value = finalScore
    }
}