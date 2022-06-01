package ge.tsu.project.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ge.tsu.project.tictactoe.Helper.Alg


class MainActivity : AppCompatActivity() {
    var player1 = true
    var isGameOn = true

    val buttons = mutableListOf<TextView>()
    var board = arrayOf(
        charArrayOf('_', '_', '_'),
        charArrayOf('_', '_', '_'),
        charArrayOf('_', '_', '_')
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun initialize(){
            buttons.add(findViewById(R.id.button1))
            buttons.add(findViewById(R.id.button2))
            buttons.add(findViewById(R.id.button3))
            buttons.add(findViewById(R.id.button4))
            buttons.add(findViewById(R.id.button5))
            buttons.add(findViewById(R.id.button6))
            buttons.add(findViewById(R.id.button7))
            buttons.add(findViewById(R.id.button8))
            buttons.add(findViewById(R.id.button9))
        }

        @SuppressLint("SetTextI18n")
        fun checkStates(player: String) {
            var res: Boolean = false
            if (board[0][0] != '_' && board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == board[2][0])
                res = true
            if (board[0][1] != '_' && board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == board[2][1])
                res = true
            if (board[0][2] != '_' && board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == board[2][2])
                res = true
            if (board[0][0] != '_' && board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == board[0][2])
                res = true
            if (board[1][0] != '_' && board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == board[1][2])
                res = true
            if (board[2][0] != '_' && board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == board[2][2])
                res = true
            if (board[0][0] != '_' && board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == board[2][2])
                res = true
            if (board[2][0] != '_' && board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == board[0][2])
                res = true

            if (Alg.isMovesLeft(board) == false) {
                findViewById<TextView>(R.id.status).text = "Draw!!!"
                isGameOn = false
            } else if (res){
                isGameOn = false
                findViewById<TextView>(R.id.status).text = if (player == "X") "You Won!!!" else "You Lose!!! :(("
            }
        }

        fun setOnClicks(){
            buttons.map { item ->
                item.setOnClickListener {
                    val index = Integer.parseInt( item.tag.toString()) - 1
                    val x = index / 3;
                    val y = index % 3;

                    if (isGameOn){
                        if (board.get(x).get(y) == '_'){
                            if (player1) {
                                item.text = "X"
                                board[x][y] = 'X'

                                checkStates("X")

                                val move = Alg.findBestMove(board)
                                if (Alg.isMovesLeft(board) == true){
                                    board[move.row][move.col] = 'O'
                                    val inddd = move.row*3 + move.col
                                    buttons.get(inddd).text = "O"
                                    checkStates("O")
                                }

                            }

                        }
                    }
                }
            }
        }

        findViewById<Button>(R.id.reset).setOnClickListener{
            buttons.map { item -> item.text = "" }
            board = arrayOf(
                charArrayOf('_', '_', '_'),
                charArrayOf('_', '_', '_'),
                charArrayOf('_', '_', '_')
            )
            isGameOn = true
            findViewById<TextView>(R.id.status).text = ""
        }

        initialize();
        setOnClicks();
    }
}