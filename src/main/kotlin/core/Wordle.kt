package core

import data.Words
import models.Cell
import models.Color
import models.OutputMessage
import models.State
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
        print("${color.dir}[$letter]${Color.RESET.dir}")
    }

    private fun isLetterInWordForIndex(word: CharArray, input: Array<Cell>): Array<Boolean?> {
        val result = arrayListOf<Boolean?>()
        for ((i, c) in input.withIndex()) {
            if (word[i] == c.letter) {
                result.add(true)
                continue
            }
            if (word.contains(c.letter)) {
                result.add(false)
                continue
            }
            result.add(null)
        }

        return result.toTypedArray()
    }

    fun play() {

        val output = OutputMessage(english)
        val letters = Words.toLettersArray(Words.pickRandomWord())
        val word = String(letters.toCharArray())

        while (true) {

            printBoard(board.toTypedArray())

            print(output.onUserInputEvent)
            val input = readln()

            if (!isValidInput(input, output)) {
                continue
            }
            board[attempt] = Cell.stringToCellArray(input)

            val result = isLetterInWordForIndex(letters.toCharArray(), board[attempt])
            result.forEachIndexed { i, it ->
                when (it) {
                    true -> board[attempt][i].color = Color.GREEN
                    false -> board[attempt][i].color = Color.YELLOW
                    else -> board[attempt][i].color = Color.RESET
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
                println(output.onPlayerLose)
            }

            else -> {}
        }
    }
}