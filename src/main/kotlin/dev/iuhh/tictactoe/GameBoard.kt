package dev.iuhh.tictactoe

data class GameBoard(
  private val cells: String,
) {
  operator fun get(row: Int, col: Int) = this.cells[row * 3 + col]
  fun getCol(col: Int) = (0..2).map { this[it, col] }
  fun getRow(row: Int) = (0..2).map { this[row, it] }
  fun getDiagonalFromTopLeft() = (0..2).map { this[it, it] }
  fun getDiagonalFromTopRight() = (0..2).map { this[it, 2 - it] }
  fun count(symbol: Char) = this.cells.count { it == symbol }
  override fun toString(): String {
    return """
      ${getRow(0)}
      ${getRow(1)}
      ${getRow(2)}
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