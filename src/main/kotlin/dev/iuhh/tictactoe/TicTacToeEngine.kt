package dev.iuhh.tictactoe

object TicTacToeEngine {
  fun determineGameState(game: GameBoard): GameState {
    // TODO #2 Handle both players win
    println("game board:")
    println(game)
    return if (game.isWonBy(Circle)) GameState.CircleWin
    else if (game.isWonBy(Cross)) GameState.CrossWin
    else if (game.isDrawGame()) GameState.Draw
    else GameState.Incomplete
  }

  fun determineNextPlayer(game: GameBoard) =
    if (game.count(Circle) < game.count(Cross)) Circle
    else Cross

  private fun GameBoard.isWonBy(symbol: Char) =
    isEntireColContains(symbol)
        || isEntireRowContains(symbol)
        || isEntireDiagonalContains(symbol)

  private fun GameBoard.isEntireColContains(symbol: Char) =
    getCol(0).allAre(symbol)
        || getCol(1).allAre(symbol)
        || getCol(2).allAre(symbol)

  private fun GameBoard.isEntireRowContains(symbol: Char) =
    getRow(0).allAre(symbol)
        || getRow(1).allAre(symbol)
        || getRow(2).allAre(symbol)

  private fun GameBoard.isEntireDiagonalContains(symbol: Char): Boolean {
    return getDiagonalFromTopLeft().allAre(symbol)
        || getDiagonalFromTopRight().allAre(symbol)
  }

  private fun List<Char>.allAre(char: Char) = all { it == char }
  private fun GameBoard.isDrawGame() = this.count(Empty) == 0 // because we determined the winner before this call
}

