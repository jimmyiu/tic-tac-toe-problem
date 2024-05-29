package dev.iuhh.tictactoe

object TicTacToeEngine {
  fun determineGameState(game: Game): GameState {
    // TODO #2 Handle both winners win
    println(game)
    if (game.isWinningBy(Circle)) return GameState.CircleWin
    if (game.isWinningBy(Cross)) return GameState.CrossWin
    if (game.isAllCellFilled()) return GameState.Draw
    return GameState.Incomplete
  }

  private fun Game.isWinningBy(player: Char) =
    isEntireColContains(player) || isEntireRowContains(player) || isEntireDiagonalContains(player)

  fun determineWhoIsTheNext(game: Game): Char {
    val numOfCross = game.cells.count { it == Cross }
    val numOfCircle = game.cells.count { it == Circle }
    return if (numOfCircle < numOfCross) Circle else Cross
  }

  private fun Game.isEntireColContains(char: Char): Boolean {
    for (col in 0..2) {
      if (getCol(col).all { it == char }) {
        return true
      }
    }
    return false
  }

  private fun Game.isEntireRowContains(char: Char): Boolean {
    for (row in 0..2) {
      if (getRow(row).all { it == char }) {
        return true
      }
    }
    return false
  }

  private fun Game.isEntireDiagonalContains(char: Char): Boolean {
    // TODO: give a better name
    val fromTopLeft = listOf(this[0, 0], this[1, 1], this[2, 2]).all { it == char }
    val fromTopRight = listOf(this[0, 2], this[1, 1], this[2, 0]).all { it == char }
    return fromTopRight || fromTopLeft
  }

  private fun Game.isAllCellFilled(): Boolean {
    return this.cells.all { it != Empty }
  }

  val Circle: Char = 'O'
  val Cross: Char = 'X'
  val Empty: Char = '='
}

enum class GameState {
  CrossWin,
  CircleWin,
  Draw,
  Incomplete,
}
