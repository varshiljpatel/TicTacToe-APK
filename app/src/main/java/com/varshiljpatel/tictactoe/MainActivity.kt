package com.varshiljpatel.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var ticTacToeGame: TicTacToeGame
    private val buttons: Array<Array<Button?>> = Array(3) { arrayOfNulls(3) }
    lateinit var textView : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        ticTacToeGame = TicTacToeGame()

        // Initialize buttons
        buttons[0][0] = findViewById(R.id.Btn1)
        buttons[0][1] = findViewById(R.id.Btn2)
        buttons[0][2] = findViewById(R.id.Btn3)

        buttons[1][0] = findViewById(R.id.Btn4)
        buttons[1][1] = findViewById(R.id.Btn5)
        buttons[1][2] = findViewById(R.id.Btn6)

        buttons[2][0] = findViewById(R.id.Btn7)
        buttons[2][1] = findViewById(R.id.Btn8)
        buttons[2][2] = findViewById(R.id.Btn9)

        val resetBtn: ImageView = findViewById(R.id.resetBtn)
        textView = findViewById(R.id.textView)

        for (row in buttons.indices) {
            for (col in buttons[row].indices) {
                buttons[row][col]?.setOnClickListener { onButtonClick(row, col) }
            }
        }

//         Reset game button click listener
        resetBtn.setOnClickListener {
            resetGame()
            textView.text = ""
        }
    }

    private fun onButtonClick(row: Int, col: Int) {
        if (!ticTacToeGame.isGameOver()) {
            val button: Button? = buttons[row][col]
            if (button?.text.isNullOrEmpty()) {
                val currentPlayer = ticTacToeGame.getCurrentPlayer()
                button?.text = currentPlayer
                if (ticTacToeGame.makeMove(row, col)) {
                    if (ticTacToeGame.isGameOver()) {
                        showGameOverDialog()
                    }
                }
            }
        }
    }

    private fun resetGame() {
        ticTacToeGame.resetGame()
        for (row in buttons.indices) {
            for (col in buttons[row].indices) {
                buttons[row][col]?.text = ""
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showGameOverDialog() {
        val currentPlayer = ticTacToeGame.getCurrentPlayer()
        val message = if (ticTacToeGame.isBoardFull()) {
            textView.text = "It's a draw!"
        } else {
            textView.text = "Player $currentPlayer wins!"
        }
    }
}

