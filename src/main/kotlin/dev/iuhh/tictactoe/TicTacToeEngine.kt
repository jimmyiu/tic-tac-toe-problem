package dev.iuhh.tictactoe

object TicTacToeEngine {
  fun determineGameState(game: GameBoard): GameState {
    // TODO #2 Handle both players win
    if (game.isWinningBy(Circle)) return GameState.CircleWin
    if (game.isWinningBy(Cross)) return GameState.CrossWin
    if (game.isDrawGame()) return GameState.Draw
    return GameState.Incomplete
  }

  private fun GameBoard.isWinningBy(player: Char) =
    isEntireColContains(player)
        || isEntireRowContains(player)
        || isEntireDiagonalContains(player)

  fun determineWhoIsTheNext(game: GameBoard): Char {
    val numOfCross = game.count(Cross)
    val numOfCircle = game.count(Circle)
    return if (numOfCircle < numOfCross) Circle else Cross
  }

  private fun GameBoard.isEntireColContains(char: Char): Boolean {
    for (col in 0..2) {
      if (getCol(col).all { it == char }) {
        return true
      }
    }
    return false
  }

  private fun GameBoard.isEntireRowContains(char: Char): Boolean {
    for (row in 0..2) {
      if (getRow(row).all { it == char }) {
        return true
      }
    }
    return false
  }

  private fun GameBoard.isEntireDiagonalContains(char: Char): Boolean {
    // TODO: give a better name
    val fromTopLeft = listOf(this[0, 0], this[1, 1], this[2, 2]).all { it == char }
    val fromTopRight = listOf(this[0, 2], this[1, 1], this[2, 0]).all { it == char }
    return fromTopRight || fromTopLeft
  }

  private fun GameBoard.isDrawGame() = this.isAllCellFilled()
}

