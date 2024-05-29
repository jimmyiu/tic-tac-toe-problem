package dev.iuhh.tictactoe.builder

internal object GameBoardStringBuilder {
  fun ofRows(row1: String, row2: String, row3: String) =
    "$row1$row2$row3"

  fun ofCols(col1: String, col2: String, col3: String): String {
    fun row(i: Int) = "${col1[i]}${col2[i]}${col3[i]}"
    return ofRows(row(0), row(1), row(2))
  }
}