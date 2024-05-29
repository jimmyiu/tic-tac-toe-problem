package dev.iuhh.tictactoe

object GameBoardStringBuilder {
  fun ofRows(row1: String = DEFAULT_VALUE, row2: String = DEFAULT_VALUE, row3: String = DEFAULT_VALUE) =
    "$row1$row2$row3"

  fun ofCols(col1: String = DEFAULT_VALUE, col2: String = DEFAULT_VALUE, col3: String = DEFAULT_VALUE): String {
    fun row(i: Int) = "${col1[i]}${col2[i]}${col3[i]}"
    return ofRows(row(0), row(1), row(2))
  }

  private const val DEFAULT_VALUE = "==="
}