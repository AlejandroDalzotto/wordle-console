package core

import data.Words
import models.*
import utils.isValidInput

class Wordle(private val english: Boolean = true) {

    private var attempt = 0
    private val board: ArrayList<Array<Cell>> = arrayListOf()
    private var gameState: State

    init {
        board.addAll(generateBoard())
        gameState = State.PLAYING
    }

    private fun generateBoard(): Array<Array<Cell>> {
        return arrayOf(
            arrayOf(Cell(' '), Cell(' '), Cell(' '), Cell(' '), Cell(' ')),
            arrayOf(Cell(' '), Cell(' '), Cell(' '), Cell(' '), Cell(' ')),
            arrayOf(Cell(' '), Cell(' '), Cell(' '), Cell(' '), Cell(' ')),
            arrayOf(Cell(' '), Cell(' '), Cell(' '), Cell(' '), Cell(' ')),
            arrayOf(Cell(' '), Cell(' '), Cell(' '), Cell(' '), Cell(' ')),
            arrayOf(Cell(' '), Cell(' '), Cell(' '), Cell(' '), Cell(' '))
        )
    }

    private fun printBoard(board: Array<Array<Cell>>) {
        board.forEach { word ->
            word.forEach { cell ->
                renderLetterCell(cell.letter, cell.color)
            }
            println()
        }
    }

    private fun renderLetterCell(letter: Char, color: Color = Color.WHITE) {
        print("${color.dir}[$letter]${Color.WHITE.dir}")
    }

    private fun isLetterInWordForIndex(word: CharArray, input: Array<Cell>): Array<Boolean?> {
        val result = Array<Boolean?>(word.size) { null }
        val unmatched = mutableListOf<Char>()

        for (i in word.indices) {
            if (word[i] == input[i].letter) {
                result[i] = true
            } else {
                unmatched.add(word[i])
            }
        }

        for (i in word.indices) {
            if (result[i] == null && unmatched.contains(input[i].letter)) {
                result[i] = false
                unmatched.remove(input[i].letter)
            }
        }

        return result
    }

    fun play() {
        val keyboard = Keys()
        val output = OutputMessage(english)
        val letters = Words.toLettersArray(Words.pickRandomWord())
        val word = String(letters.toCharArray())
        while (true) {
            printBoard(board.toTypedArray())
            println("-".repeat(35))
            keyboard.printKeys()
            print("\n${output.onUserInputEvent}")
            val input = readln().lowercase()

            if (!isValidInput(input, output)) {
                continue
            }
            board[attempt] = Cell.stringToCellArray(input)

            val result = isLetterInWordForIndex(letters.toCharArray(), board[attempt])
            result.forEachIndexed { i, it ->
                when (it) {
                    true -> {
                        board[attempt][i].color = Color.GREEN
                        keyboard.discoverKey(board[attempt][i].letter, KeyState.CORRECT)
                    }
                    false -> {
                        board[attempt][i].color = Color.YELLOW
                        keyboard.discoverKey(board[attempt][i].letter, KeyState.INCORRECT)
                    }
                    else -> {
                        board[attempt][i].color = Color.RESET
                        keyboard.discoverKey(board[attempt][i].letter, KeyState.NULL)
                    }
                }
            }

            if (input == word) {
                gameState = State.VICTORY
                break
            }

            attempt++

            if (attempt > 5) {
                gameState = State.LOSS
                break
            }
        }

        when(gameState) {
            State.VICTORY -> {
                println(output.onPlayerWin)
                printBoard(board.toTypedArray())
            }
            State.LOSS -> {
                printBoard(board.toTypedArray())
                println(output.onRevealWord(word))
                println(output.onPlayerLose)
            }

            else -> {}
        }
    }
}