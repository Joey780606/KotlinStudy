package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // The current word, move from GameFragment.kt
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
       get() = _word

    // The current score, move from GameFragment.kt
    private var _score = MutableLiveData<Int>() //mutable(can't change) version
    val score: LiveData<Int>
       get() = _score

    // The list of words - the front of the list is the next word to guess, move from GameFragment.kt
    private lateinit var wordList: MutableList<String>

    init {
        resetList()  // move from GameFragment.kt
        nextWord()   // move from GameFragment.kt

        _word.value = "" // Java need use setValue()
        _score.value = 0
        Log.i("GameViewModel", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() { // move from GameFragment.kt
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    public fun onSkip() {
        _score.value = (score.value)?.minus(1)  // score 可能為 null, 這樣的寫法就可達到 null-safety 的作用
        nextWord()
    }

    public fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }
}