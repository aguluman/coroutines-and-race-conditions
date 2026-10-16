import kotlin.collections.random
import kotlin.random.Random

private val random = Random(seed = 546)
private val menu = listOf("🎂", "🥧", "🧁")

val orders = List(100_000) { menu.random(random) }

fun bake(item: String): BakedGood = when (item) {
    "🎂"  -> BakedGood.CAKE
    "🥧"  -> BakedGood.PIE
    "🧁"  -> BakedGood.CUPCAKE
    else -> throw kotlin.IllegalArgumentException()
}

enum class BakedGood(val cost: Int) {
    CAKE(15),
    PIE(10),
    CUPCAKE(5)
}
