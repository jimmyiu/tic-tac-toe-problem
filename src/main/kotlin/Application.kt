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
  println(analysisGameState(toGames(CompleteGameInput)))
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
    println("Case ${index + 1}), game state = ${obj.state}")
    if (obj.state != GameState.Incomplete) println(obj.board)
  }
}

private fun analysisGameState(boards: List<GameBoard>) =
  boards
    .map { TicTacToeEngine.determineGameState(it) }
    .groupingBy { it }
    .eachCount()

private fun toGames(str: String) = str.split(System.lineSeparator()).map { GameBoard.of(it) }
val CompleteGameInput = getResourceAsText("file1.txt")
val IncompleteGameInput = getResourceAsText("file2.txt")
fun getResourceAsText(path: String) = object {}.javaClass.getResource(path)?.readText()!!
//const val IncompletedGamesStr = """X=X=OX=O=
//XX=OO====
//XOO=XXOXO
//OOXX=X==O
//XOXX=X=OO
//XOOOOXXX=
//OOXXOX===
//OXXO=OXX=
//===OX=OX=
//=XXOOXXOO
//=XXXOOOOX
//XX=XXO=OO
//=XO=XO=OX
//OO=XX===O
//O==O===XX
//OX=O===XO
//=XOO=XXXO
//=====X==O
//=XXOX=OO=
//O=OXX==X=
//XXOO=XXOX
//XOXOXOX=O
//=XX==OOO=
//OOXO=OXX=
//====OXX=O
//O=XOO=XX=
//XOOOXXO==
//OOXOXX=XO
//OXXO=OXOX
//XO=XX==OO
//O=OXXOXOX
//=X=OOXO=X
//XOOOX=XO=
//OO=OXOXX=
//OXXOO=XOX
//OXOOX=XOO
//=O===OXX=
//OXO=OXXOX
//X=OXO==OX
//OX=XOXXOX
//XOOOOX=XX
//X=O=X===O
//XO=XO====
//X=XO==OOX
//=OXOXOOXX
//==X=XX=OO
//X=XXO=O=O
//XXOOX=OOX
//O=XOO=XOX
//XO=XOOOOX
//=O=OXXOXO
//==XX==OO=
//XXOO=XXOX
//XXOOOXXOX
//O=X=XXO=O
//XOXOXOO=X
//OXOX=XXOO
//O==XOX===
//XXO=OO=XX
//OXXX=OXOO
//XX=OXXOO=
//XXO=OXOOX
//XOOXXO=O=
//=OXOX=OXX
//OXX==OO=X
//X=XXOXOXO
//=XOO=O=XX
//OO==XXXOO
//XOOXOO=XX
//OXX=XXOO="""