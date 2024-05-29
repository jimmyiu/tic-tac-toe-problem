package dev.iuhh.tictactoe

object TicTacToeEngine {
  fun determineGameState(board: GameBoard): GameState {
    // TODO #2 Handle both players win
    return if (board.isWonBy(Circle)) GameState.CircleWin
    else if (board.isWonBy(Cross)) GameState.CrossWin
    else if (board.isDrawGame()) GameState.Draw
    else GameState.Incomplete
  }

  fun determineNextPlayer(board: GameBoard): Char {
    check(board.isDrawGame().not()) { "no more move is allowed" }
    return if (board.count(Circle) < board.count(Cross)) Circle
    else Cross
  }

  fun determineNextWinningMoveOf(board: GameBoard, symbol: Char) =
    WinningPattern.entries
      .map { pattern -> board.canWinByMove(pattern, symbol) }
      .filterNot { it == Move.NotAvailable }
      .distinct()

  private fun GameBoard.canWinByMove(pattern: WinningPattern, symbol: Char): Move {
    val currentSymbols = this.getBy(pattern)
    val emptyIndex = currentSymbols.indexOf(Empty)
    val potentialWinningPosition =
      if (emptyIndex >= 0 && currentSymbols.count(symbol) == 2) pattern.positions[emptyIndex] else -1
    return Move.ofPosition(potentialWinningPosition)
  }

  private fun GameBoard.isWonBy(symbol: Char) =
    WinningPattern.entries.any { getBy(it).allAre(symbol) }

  private fun List<Char>.allAre(char: Char) = all { it == char }
  private fun List<Char>.count(char: Char) = count { it == char }
  private fun GameBoard.isDrawGame() = this.count(Empty) == 0 // because we determined the winner before this call
}

