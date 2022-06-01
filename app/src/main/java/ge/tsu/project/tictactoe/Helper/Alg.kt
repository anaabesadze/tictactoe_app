package ge.tsu.project.tictactoe.Helper

object Alg {
    class Move {
        var row = -1
        var col = -1
    }

    var player = 'O'
    var opponent = 'X'

    fun isMovesLeft(board: Array<CharArray>): Boolean? {
        for (i in 0..2)
            for (j in 0..2)
                if (board[i][j] == '_') return true
        return false
    }

    fun evaluate(b: Array<CharArray>): Int {
        for (row in 0..2) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == player) {
                    return +10
                } else if (b[row][0] == opponent) {
                    return -10
                }
            }
        }
        for (col in 0..2) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == player) {
                    return +10
                } else if (b[0][col] == opponent) {
                    return -10
                }
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player) {
                return +10
            } else if (b[0][0] == opponent) {
                return -10
            }
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player) {
                return +10
            } else if (b[0][2] == opponent) {
                return -10
            }
        }
        return 0
    }

    fun minimax(board: Array<CharArray>, depth: Int, isMax: Boolean): Int {
        val score = evaluate(board)
        if (score == 10) {
            return score
        }
        if (score == -10) {
            return score
        }
        if (isMovesLeft(board) == false) {
            return 0
        }
        return if (isMax) {
            var best = -1000
            for (i in 0..2) {
                for (j in 0..2) {
                    if (board[i][j] == '_') {
                        board[i][j] = player
                        best = best.coerceAtLeast(minimax(board, depth + 1, !isMax))
                        board[i][j] = '_'
                    }
                }
            }
            best
        } else {
            var best = 1000
            for (i in 0..2) {
                for (j in 0..2) {
                    if (board[i][j] == '_') {
                        board[i][j] = opponent
                        best = best.coerceAtMost(minimax(board, depth + 1, !isMax))
                        board[i][j] = '_'
                    }
                }
            }
            best
        }
    }

    fun findBestMove(board: Array<CharArray>): Move {
        var bestVal = -1000
        val bestMove = Move()

        bestMove.row = -1
        bestMove.col = -1
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == '_') {
                    board[i][j] = player
                    val moveVal = minimax(board, 0, false)
                    board[i][j] = '_'
                    if (moveVal > bestVal) {
                        bestMove.row = i
                        bestMove.col = j
                        bestVal = moveVal
                    }
                }
            }
        }

        return bestMove
    }


}