package dev.iuhh.tictactoe

import ArgProvider
import dev.iuhh.tictactoe.TicTacToeEngine.determineNextWinningMoveOf
import dev.iuhh.tictactoe.builder.GameBoardBuilder
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchException
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
    fun `given an incomplete game, should return the one with fewer move as the next player`(
      input: String,
      expected: Char
    ) {
      val actual = TicTacToeEngine.determineNextPlayer(GameBoard.of(input))
      assertThat(actual).isEqualTo(expected)
    }
    @Test
    fun `given an incomplete game, and number of moves of the two players are the same, should return the first player (X)`() {
      val game = GameBoardBuilder.ofRows(
        "OO=",
        "===",
        "XX=",
      )
      val actual = TicTacToeEngine.determineNextPlayer(game)
      assertThat(actual).isEqualTo(Cross)
    }
    @Test
    fun `given a draw game, should throw exception as no next player is available`() {
      val exception = catchException { TicTacToeEngine.determineNextPlayer(GameBoardBuilder.draw()) }
      assertThat(exception)
        .isInstanceOf(IllegalStateException::class.java)
        .hasMessage("no more move is allowed")
    }
  }
  @Nested
  inner class DetermineNextWinningMoveOfTest {
    @ParameterizedTest
    @ArgumentsSource(DetermineNextWinningMoveOfCompleteGameTestCases::class)
    fun `given a board is a complete game, should return empty list`(board: GameBoard, symbol: Char) {
      val actual = board.determineNextWinningMoveOf(symbol)
      assertThat(actual).hasSize(0)
    }
    @ParameterizedTest
    @ArgumentsSource(DetermineNextWinningMoveOfIncompleteGameTestCases::class)
    fun `given a board which is possible to win by the next move, should return the winning moves`(
      board: GameBoard,
      symbol: Char,
      expected: List<Move>
    ) {
      val actual = board.determineNextWinningMoveOf(symbol)
      assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)
    }
  }

  private class DetermineNextWinningMoveOfCompleteGameTestCases : ArgProvider(
    arguments(GameBoardBuilder.draw(), Cross),
    arguments(GameBoardBuilder.draw(), Circle),
    arguments(GameBoardBuilder.circleWins(), Cross),
    arguments(GameBoardBuilder.circleWins(), Circle),
    arguments(GameBoardBuilder.crossWins(), Cross),
    arguments(GameBoardBuilder.crossWins(), Circle),
  )

  private class DetermineNextWinningMoveOfIncompleteGameTestCases : ArgProvider(
    arguments(GameBoardBuilder.default(), Cross, listOf(Move(0, 1))),
    arguments(GameBoardBuilder.default(), Circle, listOf(Move(1, 0))),
    arguments(
      GameBoardBuilder.ofRows(
        "XX=",
        "OXO",
        "O==",
      ),
      Cross,
      listOf(Move(0, 2), Move(2, 1), Move(2, 2))
    ),
    arguments(
      GameBoardBuilder.ofRows(
        "XX=",
        "OXO",
        "O==",
      ),
      Circle,
      emptyList<Move>()
    ),
    arguments(
      GameBoardBuilder.ofRows(
        "XOO",
        "X=X",
        "OOX",
      ),
      Circle,
      listOf(Move(1, 1))
    ),
  )
}