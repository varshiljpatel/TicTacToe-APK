package com.varshiljpatel.tictactoe

class TicTacToeGame {
    private val board: Array<Array<String>> = Array(3) { arrayOf("", "", "") }
    private var currentPlayer: String = "X"
    private var gameWon: Boolean = false

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col].isEmpty() && !gameWon) {
            board[row][col] = currentPlayer
            checkWin(row, col)
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            return true
        }
        return false
    }

    private fun checkWin(row: Int, col: Int) {
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
            gameWon = true
        }
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
            gameWon = true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            gameWon = true
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            gameWon = true
        }
    }

    fun isGameOver(): Boolean {
        return gameWon || isBoardFull()
    }

    fun isBoardFull(): Boolean {
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                if (board[row][col].isEmpty()) {
                    return false
                }
            }
        }
        return true
    }

    fun getCurrentPlayer(): String {
        return currentPlayer
    }

    fun getBoard(): Array<Array<String>> {
        return board
    }

    fun resetGame() {
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                board[row][col] = ""
            }
        }
        currentPlayer = "X"
        gameWon = false
    }
}
