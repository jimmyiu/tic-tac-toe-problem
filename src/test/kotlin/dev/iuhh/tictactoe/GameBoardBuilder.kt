package dev.iuhh.tictactoe

object GameBoardBuilder {
  /**
   * Returns a default valid game board of:
   * ```
   * O==
   * =X=
   * OX=
   * ```
   */
  fun default() = GameBoard.of(
    GameBoardStringBuilder.ofRows(
      "O==",
      "=X=",
      "OX="
    )
  )
  /**
   * Returns a draw state game board of:
   * ```
   * XXO
   * OOX
   * XOX
   * ```
   */
  fun draw() = GameBoard.of(
    GameBoardStringBuilder.ofRows(
      "XXO",
      "OOX",
      "XOX"
    )
  )

  fun ofRows(row1: String, row2: String, row3: String) =
    GameBoard.of(GameBoardStringBuilder.ofRows(row1, row2, row3))

  fun ofCols(col1: String, col2: String, col3: String) =
    GameBoard.of(GameBoardStringBuilder.ofCols(col1, col2, col3))
}