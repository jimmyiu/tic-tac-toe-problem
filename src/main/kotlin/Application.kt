import dev.iuhh.tictactoe.GameBoard
import dev.iuhh.tictactoe.GameState
import dev.iuhh.tictactoe.TicTacToeEngine

fun main(args: Array<String>) {
  printCompleteGamesAnalysis()
  println()
  printIncompleteGamesAnalysis()
}

private fun printCompleteGamesAnalysis() {
  println("=== Analysis of file1.txt (complete games) ===")
  println(toGameBoards(CompleteGameInput)
    .map { TicTacToeEngine.determineGameState(it) }
    .groupingBy { it }
    .eachCount())
}

fun printIncompleteGamesAnalysis() {
  println("=== Analysis of file2.txt (incomplete games) ===")
  val boards = toGameBoards(IncompleteGameInput)
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
  val winningMoves = TicTacToeEngine.determineNextWinningMoveOf(board, nextMove)
  print(", the next player is ($nextMove) and winning moves are $winningMoves")
}

private fun toGameBoards(str: String) = str.split(System.lineSeparator()).map { GameBoard.of(it) }
val CompleteGameInput = getResourceAsText("file1.txt")
val IncompleteGameInput = getResourceAsText("file2.txt")
fun getResourceAsText(path: String) = object {}.javaClass.getResource(path)?.readText()!!