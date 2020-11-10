package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // The current word, move from GameFragment.kt
    var word = ""

    // The current score, move from GameFragment.kt
    var score = 0

    // The list of words - the front of the list is the next word to guess, move from GameFragment.kt
    private lateinit var wordList: MutableList<String>

    init {
        resetList()  // move from GameFragment.kt
        nextWord()   // move from GameFragment.kt

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
            word = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    public fun onSkip() {
        score--
        nextWord()
    }

    public fun onCorrect() {
        score++
        nextWord()
    }
}