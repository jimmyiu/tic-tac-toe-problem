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
  println(toGames(CompleteGameInput)
    .map { TicTacToeEngine.determineGameState(it) }
    .groupingBy { it }
    .eachCount())
}

fun printIncompleteGamesAnalysis() {
  println("=== Analysis of file2.txt (incomplete games) ===")
  val boards = toGames(IncompleteGameInput)
  val result = boards.map {
    object {
      val board = it
      val state = TicTacToeEngine.determineGameState(board)
    }
  }
  result.forEachIndexed { index, obj ->
    print("Case ${index + 1}), game state = ${obj.state}")
    if (obj.state != GameState.Incomplete) println(System.lineSeparator() + obj.board)
    else {
      val nextMove = TicTacToeEngine.determineNextPlayer(obj.board)
      println(", next player is ($nextMove)")
    }
  }
}

private fun toGames(str: String) = str.split(System.lineSeparator()).map { GameBoard.of(it) }
val CompleteGameInput = getResourceAsText("file1.txt")
val IncompleteGameInput = getResourceAsText("file2.txt")
fun getResourceAsText(path: String) = object {}.javaClass.getResource(path)?.readText()!!