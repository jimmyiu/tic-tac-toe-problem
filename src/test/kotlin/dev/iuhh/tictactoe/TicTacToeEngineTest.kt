package dev.iuhh.tictactoe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TicTacToeEngineTest {
  @ParameterizedTest
  @CsvSource(
    "XOX=OX=O=,CircleWin",
    "OXO=XO=X=,CrossWin",
    "XX=OOO===,CircleWin",
    "OO=XXX===,CrossWin",
    "O===O===O,CircleWin",
    "==O=O=O==,CircleWin",
    "X===X===X,CrossWin",
    "==X=X=X==,CrossWin",
  )
  fun `someone wins`(input: String, state: GameState) {
    //
    val actual = TicTacToeEngine.determineGameState(Game.of(input))
    //
    assertThat(actual).isEqualTo(state)
  }
  @Test
  fun `a row with the same symbol wins`() {
    val case1 = "XOX=OX=O=";
    //
    val actual = TicTacToeEngine.determineGameState(Game.of(case1))
    //
    assertThat(actual).isEqualTo(GameState.CircleWin)
  }
  @Test
  fun `if all cells are filled but no one wins, it is a draw`() {
    val case1 = "XXOOOXXOX";
    //
    val actual = TicTacToeEngine.determineGameState(Game.of(case1))
    //
    assertThat(actual).isEqualTo(GameState.Draw)
  }
  @Test
  fun `the game can be created correctly`() {
    val case1 = "XOX=OX=O=";
    //
    val game = Game.of(case1)
    //
    println(game)
    assertThat(game).isEqualTo(
      Game(
        listOf('X', 'O', 'X', '=', 'O', 'X', '=', 'O', '=')
      )
    )
  }

  // determine who is next
  @ParameterizedTest
  @CsvSource(
    "X=X=OX=O=,O",
    "XX=OO====,X",
  )
  fun `the one with fewer move is the next one to move`(input: String, expected: Char) {
    //
    val actual = TicTacToeEngine.determineWhoIsTheNext(Game.of(input))
    //
    assertThat(actual).isEqualTo(expected)
  }
}