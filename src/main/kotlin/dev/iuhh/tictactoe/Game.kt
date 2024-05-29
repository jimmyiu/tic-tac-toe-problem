package dev.iuhh.tictactoe

data class Game(
  val cells: List<Char>,
) {
  operator fun get(row: Int, col: Int): Char {
    return this.cells[row * 3 + col]
  }

  fun getCol(col: Int) = listOf(this[0, col], this[1, col], this[2, col])
  fun getRow(row: Int) = listOf(this[row, 0], this[row, 1], this[row, 2])
  override fun toString(): String {
    return """
      ${getRow(0)}
      ${getRow(1)}
      ${getRow(2)}
    """.trimIndent()
  }

  companion object {
    fun of(input: String): Game {
      // assuming that input is always having length == 9
      return Game(input.toCharArray().toList())
    }
  }
}