package dev.iuhh.tictactoe

object TicTacToeEngine {
  fun determineGameState(game: Game): GameState {
    if (game.isEntireColContains(Circle) || game.isEntireRowContains(Circle) || game.isEntireDiagonalContains(Circle)) {
      return GameState.CircleWin
    }
    if (game.isEntireColContains(Cross) || game.isEntireRowContains(Cross) || game.isEntireDiagonalContains(Cross)) {
      return GameState.CrossWin
    }
    if (game.isAllCellFilled()) {
      return GameState.Draw
    }
    return GameState.Incomplete
  }

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
    val fromTopLeft = listOf(this.getCell(0, 0), this.getCell(1, 1), this.getCell(2, 2)).all { it == char }
    val fromTopRight = listOf(this.getCell(0, 2), this.getCell(1, 1), this.getCell(2, 0)).all { it == char }
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

data class Game(
  val cells: List<Char>,
) {
  fun getCell(row: Int, col: Int): Char {
    return this.cells[row * 3 + col]
  }

  fun getCol(col: Int) =
    listOf(this.getCell(0, col), this.getCell(1, col), this.getCell(2, col))

  fun getRow(row: Int) =
    listOf(this.getCell(row, 0), this.getCell(row, 1), this.getCell(row, 2))

  companion object {
    fun of(input: String): Game {
      // assuming that input is always having length == 9
      return Game(input.toCharArray().toList())
    }
  }
}