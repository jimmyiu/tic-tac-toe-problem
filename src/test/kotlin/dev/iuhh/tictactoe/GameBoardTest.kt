package dev.iuhh.tictactoe

import ArgProvider
import dev.iuhh.tictactoe.builder.GameBoardBuilder
import dev.iuhh.tictactoe.builder.GameBoardStringBuilder
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class GameBoardTest {
  @ParameterizedTest
  @ValueSource(strings = ["XO=", "XX==1==OO", "===XXXOOO="])
  fun `given invalid string format, this game cannot be created`(invalidString: String) {
    // when
    val exception = catchException {
      GameBoard.of(invalidString)
    }
    // then
    assertThat(exception)
      .isInstanceOf(IllegalStateException::class.java)
  }
  @ParameterizedTest
  @ArgumentsSource(ValidGameBoardStringTestCases::class)
  fun `given valid string representation, the game can be created with expected cell symbols`(gameBoardInString: String) {
    // when
    val board = GameBoard.of(gameBoardInString)
    // then
    assertThat(board[0, 0]).isEqualTo('X')
    assertThat(board[0, 1]).isEqualTo('O')
    assertThat(board[0, 2]).isEqualTo('X')
    assertThat(board[1, 0]).isEqualTo('=')
    assertThat(board[1, 1]).isEqualTo('O')
    assertThat(board[1, 2]).isEqualTo('X')
    assertThat(board[2, 0]).isEqualTo('=')
    assertThat(board[2, 1]).isEqualTo('O')
    assertThat(board[2, 2]).isEqualTo('=')
  }
  @ParameterizedTest
  @CsvSource(
    "X,3",
    "O,2",
  )
  fun `count should return the number of symbol in the game board`(symbol: Char, expected: Int) {
    // given
    val board = GameBoardBuilder.ofRows(
      "XOX",
      "===",
      "OX=",
    )
    // when
    val actual = board.count(symbol)
    // then
    assertThat(actual).isEqualTo(expected)
  }
  @ParameterizedTest
  @CsvSource(
    "Row1,XXO",
    "Row2,OO=",
    "Row3,XOX",
    "Col1,XOX",
    "Col2,XOO",
    "Col3,O=X",
    "DiagonalTopLeft,XOX",
    "DiagonalTopRight,OOX",
  )
  fun `getBy should return the symbols of the given pattern`(pattern: WinningPattern, expected: String) {
    val actual = GameBoardBuilder.incomplete().getBy(pattern)
    assertThat(actual).containsExactlyElementsOf(expected.toList())
  }

  private class ValidGameBoardStringTestCases : ArgProvider(
    arguments(
      GameBoardStringBuilder.ofRows(
        "XOX",
        "=OX",
        "=O="
      )
    ),
    arguments(
      GameBoardStringBuilder.ofCols(
        "X==",
        "OOO",
        "XX="
      )
    )
  )
}

