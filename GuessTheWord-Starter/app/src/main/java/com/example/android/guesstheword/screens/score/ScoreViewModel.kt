package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {   //加入參數表示這是一個constructor的參數
    var score = finalScore

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")   // 重要
    }
}