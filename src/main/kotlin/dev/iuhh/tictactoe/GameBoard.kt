package dev.iuhh.tictactoe

data class GameBoard(
  private val cells: String,
) {
  operator fun get(row: Int, col: Int) = this.cells[row * 3 + col]
  fun getCol(col: Int) = listOf(this[0, col], this[1, col], this[2, col])
  fun getRow(row: Int) = listOf(this[row, 0], this[row, 1], this[row, 2])
  fun count(symbol: Char) = this.cells.count { it == symbol }
  fun isAllCellFilled() = cells.all { it != Empty }
  override fun toString(): String {
    return """
      ${getRow(0)}
      ${getRow(1)}
      ${getRow(2)}
    """.trimIndent()
  }

  companion object {
    fun of(gameBoardInString: String): GameBoard {
      check(gameBoardInString.length == 9)
      check(gameBoardInString.all { ValidSymbols.contains(it) })
      return GameBoard(gameBoardInString)
    }
  }
}