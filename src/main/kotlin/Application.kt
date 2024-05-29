import dev.iuhh.tictactoe.GameBoard
import dev.iuhh.tictactoe.GameState
import dev.iuhh.tictactoe.TicTacToeEngine
import dev.iuhh.tictactoe.TicTacToeEngine.determineNextWinningMoveOf

fun main(args: Array<String>) {
  printCompleteGamesAnalysis()
  println()
  printIncompleteGamesAnalysis()
}

private fun printCompleteGamesAnalysis() {
  println("=== Analysis of file1.txt (complete games) ===")
  println(toGames(CompleteGameInput)
    .map { TicTacToeEngine.determineGameState(it) }
    .groupingBy { it }
    .eachCount())
}

fun printIncompleteGamesAnalysis() {
  println("=== Analysis of file2.txt (incomplete games) ===")
  val boards = toGames(IncompleteGameInput)
  boards.forEachIndexed { index, board ->
    val state = TicTacToeEngine.determineGameState(board)
    print("Case ${index + 1}) game state is $state")
    if (state == GameState.Incomplete) printNextMoveAnalysis(board)
    println()
    println(board)
  }
}

private fun printNextMoveAnalysis(board: GameBoard) {
  val nextMove = TicTacToeEngine.determineNextPlayer(board)
  val winningMoves = board.determineNextWinningMoveOf(nextMove)
  print(", next player is ($nextMove), the winning moves are $winningMoves")
}

private fun toGames(str: String) = str.split(System.lineSeparator()).map { GameBoard.of(it) }
val CompleteGameInput = getResourceAsText("file1.txt")
val IncompleteGameInput = getResourceAsText("file2.txt")
fun getResourceAsText(path: String) = object {}.javaClass.getResource(path)?.readText()!!