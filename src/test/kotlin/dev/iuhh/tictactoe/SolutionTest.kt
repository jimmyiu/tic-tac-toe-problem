package dev.iuhh.tictactoe

import ArgProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.ArgumentsSource

class SolutionTest {
  @ParameterizedTest
  @ArgumentsSource(TestCase::class)
  fun `should return exact the same as input`(input: String) {
    // when
    val actual = Solution.run(input)
    // then
    assertThat(actual).isEqualTo(input)
  }

  private class TestCase : ArgProvider(
    arguments("a"),
    arguments("apple"),
  )
}