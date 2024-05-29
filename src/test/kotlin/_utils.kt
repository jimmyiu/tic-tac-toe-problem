import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

open class ArgProvider(private vararg val data: Arguments) : ArgumentsProvider {
  override fun provideArguments(
    context: ExtensionContext?
  ): Stream<out Arguments> = Stream.of(*data)
}

fun String.toIntArray(delimiter: Char = '-') = split(delimiter).map { it.toInt() }.toIntArray()