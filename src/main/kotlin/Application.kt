
import dev.iuhh.tictactoe.GameBoard

fun main(args: Array<String>) {
  val completedGames = toGames(IncompletedGamesStr)
//  val result = completedGames
//    .mapIndexed { indexed, it -> it.cells to TicTacToeEngine.determineGameState(it) }
//    .filter { it.second != GameState.Incomplete }
//    .groupingBy { it }
//    .eachCount()
//  println(result)
//  val incompletedGames = toGames(CompletedGamesStr)
//  val whoIsNextResult = incompletedGames.map { TicTacToeEngine.determineWhoIsTheNext(it) }
//  println(whoIsNextResult)
}

private fun toGames(str: String) = str.split(System.lineSeparator()).map { GameBoard.of(it) }
const val CompletedGamesStr = """XOX=OX=O=
XX=OOO===
XOOOXXOXO
OOXXXX==O
XOXX=XOOO
XOOOOXXXO
OOXXOX==X
OXXOOOXX=
O==OX=OX=
XXXOOXXOO
OXXXOOOOX
XX=XXOOOO
XXO=XO=OX
OO=XXX==O
O==O==OXX
OX=OX==XO
XXOOOXXXO
OO=XXX==O
XXXOX=OO=
OOOXX==X=
XXOOOXXOX
XOXOXOXXO
XXX==OOO=
OOXOXOXX=
O===OXX=O
O=XOO=XXO
XOOOXXO=X
OOXOXXOXO
OXXOXOXOX
XO=XX=XOO
OOOXXOXOX
OX=OOXO=X
XOOOX=XOX
OOXOXOXX=
OXXOOOXOX
OXOOXXXOO
=O===OXXX
OXOOOXXOX
X=OXO=XOX
OXOXOXXOX
XOOOOXOXX
X=O=XO==O
XO=XO=X==
X=XO=XOOX
XOXOXOOXX
==X=XXOOO
XXXXO=O=O
XXOOXXOOX
O=XOOXXOX
XOXXOOOOX
OOXOXXOXO
X=XX==OOO
XXOOOXXOX
XXOOOXXOX
O=XXXXO=O
XOXOXOO=X
OXOXOXXOO
O==XOX==O
XXOOOO=XX
OXXXXOXOO
XX=OXXOOO
XXOOOXOOX
XOOXXOXO=
OOXOX=OXX
OXXO=OO=X
XOXXOXOXO
=XOO=OXXX
OOX=XXXOO
XOOXOOOXX
OXX=XXOOO"""
const val IncompletedGamesStr = """X=X=OX=O=
XX=OO====
XOO=XXOXO
OOXX=X==O
XOXX=X=OO
XOOOOXXX=
OOXXOX===
OXXO=OXX=
===OX=OX=
=XXOOXXOO
=XXXOOOOX
XX=XXO=OO
=XO=XO=OX
OO=XX===O
O==O===XX
OX=O===XO
=XOO=XXXO
=====X==O
=XXOX=OO=
O=OXX==X=
XXOO=XXOX
XOXOXOX=O
=XX==OOO=
OOXO=OXX=
====OXX=O
O=XOO=XX=
XOOOXXO==
OOXOXX=XO
OXXO=OXOX
XO=XX==OO
O=OXXOXOX
=X=OOXO=X
XOOOX=XO=
OO=OXOXX=
OXXOO=XOX
OXOOX=XOO
=O===OXX=
OXO=OXXOX
X=OXO==OX
OX=XOXXOX
XOOOOX=XX
X=O=X===O
XO=XO====
X=XO==OOX
=OXOXOOXX
==X=XX=OO
X=XXO=O=O
XXOOX=OOX
O=XOO=XOX
XO=XOOOOX
=O=OXXOXO
==XX==OO=
XXOO=XXOX
XXOOOXXOX
O=X=XXO=O
XOXOXOO=X
OXOX=XXOO
O==XOX===
XXO=OO=XX
OXXX=OXOO
XX=OXXOO=
XXO=OXOOX
XOOXXO=O=
=OXOX=OXX
OXX==OO=X
X=XXOXOXO
=XOO=O=XX
OO==XXXOO
XOOXOO=XX
OXX=XXOO="""