package dev.iuhh.tictactoe

enum class GameState {
  CrossWin,
  CircleWin,
  Draw,
  Incomplete,
}

data class Move(val row: Int, val col: Int) {
  companion object {
    fun ofPosition(pos: Int): Move = PossibleMoves.getOrElse(pos) { NotAvailable }
    val NotAvailable = Move(-1, -1)
    private val PossibleMoves = listOf(
      Move(0, 0),
      Move(0, 1),
      Move(0, 2),
      Move(1, 0),
      Move(1, 1),
      Move(1, 2),
      Move(2, 0),
      Move(2, 1),
      Move(2, 2),
    )
  }
}

enum class WinningPattern(vararg val positions: Int) {
  Row1(0, 1, 2),
  Row2(3, 4, 5),
  Row3(6, 7, 8),
  Col1(0, 3, 6),
  Col2(1, 4, 7),
  Col3(2, 5, 8),
  DiagonalTopLeft(0, 4, 8),
  DiagonalTopRight(2, 4, 6);
}