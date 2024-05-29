package dev.iuhh.tictactoe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoveTest {
  @ParameterizedTest
  @CsvSource(
    "-1,-1,-1",
    "0,0,0",
    "1,0,1",
    "2,0,2",
    "3,1,0",
    "4,1,1",
    "5,1,2",
    "6,2,0",
    "7,2,1",
    "8,2,2",
    "9,-1,-1",
  )
  fun `given a winning pattern underlying position, should return an expected Move`(
    position: Int,
    expectedRow: Int,
    expectedCol: Int
  ) {
    val actual = Move.ofPosition(position)
    assertThat(actual).isEqualTo(Move(expectedRow, expectedCol))
  }
}