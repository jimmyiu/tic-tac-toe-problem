package dev.iuhh.tictactoe

import dev.iuhh.tictactoe.WinningPattern.*

data class GameBoard(
  private val cells: String,
) {
  operator fun get(row: Int, col: Int) = this.cells[row * 3 + col]
  fun getBy(pattern: WinningPattern) = listOf(cells[pattern.positions[0]], cells[pattern.positions[1]], cells[pattern.positions[2]])
  fun count(symbol: Char) = this.cells.count { it == symbol }
  override fun toString(): String {
    return """
      ${getBy(Row1)}
      ${getBy(Row2)}
      ${getBy(Row3)}
    """.trimIndent()
  }

  companion object {
    fun of(gameBoardInString: String): GameBoard {
      check(gameBoardInString.length == 9) {
        "The game board string length != 9, game board = $gameBoardInString"
      }
      check(gameBoardInString.all { ValidSymbols.contains(it) }) {
        "The game board string contains invalid symbol, game board = $gameBoardInString"
      }
      return GameBoard(gameBoardInString)
    }
  }
}