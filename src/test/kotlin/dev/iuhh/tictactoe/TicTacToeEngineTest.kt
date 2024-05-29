package dev.iuhh.tictactoe

import ArgProvider
import dev.iuhh.tictactoe.builder.GameBoardBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.CsvSource

class TicTacToeEngineTest {
  @Nested
  inner class DetermineGameStateTest {
    @ParameterizedTest
    @ArgumentsSource(WinByRowTestCases::class)
    fun `given a row of the same symbol, should the symbol player win`(board: GameBoard, expected: GameState) {
      val actual = TicTacToeEngine.determineGameState(board)
      assertThat(actual).isEqualTo(expected)
    }
    @ParameterizedTest
    @ArgumentsSource(WinByColumnTestCases::class)
    fun `given a column of the same symbol, should the symbol player win`(board: GameBoard, expected: GameState) {
      val actual = TicTacToeEngine.determineGameState(board)
      assertThat(actual).isEqualTo(expected)
    }
    @ParameterizedTest
    @ArgumentsSource(WinByDiagonalTestCases::class)
    fun `given a diagonal of the same symbol, should the symbol player wins`(board: GameBoard, expected: GameState) {
      //
      val actual = TicTacToeEngine.determineGameState(board)
      //
      assertThat(actual).isEqualTo(expected)
    }
    @Test
    fun `given all cells are filled but no one wins, should return Draw`() {
      val actual = TicTacToeEngine.determineGameState(GameBoardBuilder.draw())
      assertThat(actual).isEqualTo(GameState.Draw)
    }
    @Test
    fun `given no one wins and the game board has not been filled up, should return Incomplete`() {
      val actual = TicTacToeEngine.determineGameState(GameBoardBuilder.incomplete())
      assertThat(actual).isEqualTo(GameState.Incomplete)
    }
  }

  private class WinByRowTestCases : ArgProvider(
    arguments(GameBoardBuilder.ofRows("OOO", "XX=", "==="), GameState.CircleWin),
    arguments(GameBoardBuilder.ofRows("XX=", "OOO", "==="), GameState.CircleWin),
    arguments(GameBoardBuilder.ofRows("XX=", "===", "OOO"), GameState.CircleWin),
    arguments(GameBoardBuilder.ofRows("XXX", "OO=", "==="), GameState.CrossWin),
    arguments(GameBoardBuilder.ofRows("OO=", "XXX", "==="), GameState.CrossWin),
    arguments(GameBoardBuilder.ofRows("OO=", "===", "XXX"), GameState.CrossWin),
  )

  private class WinByColumnTestCases : ArgProvider(
    arguments(GameBoardBuilder.ofCols("OOO", "XX=", "==="), GameState.CircleWin),
    arguments(GameBoardBuilder.ofCols("XX=", "OOO", "==="), GameState.CircleWin),
    arguments(GameBoardBuilder.ofCols("XX=", "===", "OOO"), GameState.CircleWin),
    arguments(GameBoardBuilder.ofCols("XXX", "OO=", "==="), GameState.CrossWin),
    arguments(GameBoardBuilder.ofCols("OO=", "XXX", "==="), GameState.CrossWin),
    arguments(GameBoardBuilder.ofCols("OO=", "===", "XXX"), GameState.CrossWin),
  )

  private class WinByDiagonalTestCases : ArgProvider(
    arguments(
      GameBoardBuilder.ofRows(
        "O==",
        "=O=",
        "XXO"
      ), GameState.CircleWin
    ),
    arguments(
      GameBoardBuilder.ofRows(
        "XXO",
        "=O=",
        "O=="
      ), GameState.CircleWin
    ),
    arguments(
      GameBoardBuilder.ofRows(
        "X==",
        "=X=",
        "OOX"
      ), GameState.CrossWin
    ),
    arguments(
      GameBoardBuilder.ofRows(
        "OOX",
        "=X=",
        "X=="
      ), GameState.CrossWin
    ),
  )
  @Nested
  inner class DetermineNextPlayerTest {
    @ParameterizedTest
    @CsvSource(
      "X=X=OX=O=,O",
      "O=O=X====,X",
    )
    fun `the one with fewer move is the next one to move`(input: String, expected: Char) {
      val actual = TicTacToeEngine.determineNextPlayer(GameBoard.of(input))
      assertThat(actual).isEqualTo(expected)
    }
    @Test
    fun `given number of moves of the two players are the same, return the first player (X)`() {
      val game = GameBoardBuilder.ofRows(
        "OO=",
        "===",
        "XX=",
      )
      val actual = TicTacToeEngine.determineNextPlayer(game)
      assertThat(actual).isEqualTo(Cross)
    }
  }
}