package dev.iuhh.tictactoe

import ArgProvider
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class GameBoardTest {
  @ParameterizedTest
  @ValueSource(strings = ["XO=", "XX==1==OO"])
  fun `this game cannot be created if the given string format is invalid`(invalidString: String) {
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
  fun `the game can be created correctly by given string representation`(gameBoardInString: String) {
    // when
    val game = GameBoard.of(gameBoardInString)
    // then
    assertThat(game[0, 0]).isEqualTo('X')
    assertThat(game[0, 1]).isEqualTo('O')
    assertThat(game[0, 2]).isEqualTo('X')
    assertThat(game[1, 0]).isEqualTo('=')
    assertThat(game[1, 1]).isEqualTo('O')
    assertThat(game[1, 2]).isEqualTo('X')
    assertThat(game[2, 0]).isEqualTo('=')
    assertThat(game[2, 1]).isEqualTo('O')
    assertThat(game[2, 2]).isEqualTo('=')
  }
  @Test
  fun `getCol should return expected column of the game board`() {
    val actual = GameBoardBuilder.default().getCol(0)
    assertThat(actual).containsExactly(Circle, Empty, Circle)
  }
  @Test
  fun `getRow should return expected row of the game board`() {
    val actual = GameBoardBuilder.default().getRow(1)
    assertThat(actual).containsExactly(Empty, Cross, Empty)
  }
  @ParameterizedTest
  @CsvSource(
    "X,3",
    "O,2",
  )
  fun `count should return the number of symbol in the game board`(symbol: Char, expected: Int) {
    // given
    val game = GameBoardBuilder.ofRows(
      "XOX",
      "===",
      "OX=",
    )
    // when
    val actual = game.count(symbol)
    // then
    assertThat(actual).isEqualTo(expected)
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

