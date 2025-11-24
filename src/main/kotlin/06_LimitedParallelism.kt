import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    var total = 0
    val synchronized = Dispatchers.Default.limitedParallelism(1)

    runBlocking(Dispatchers.Default) {
        val baker = launch {
            orders.take(50_000).forEach { item ->
                val good = bake(item)
                withContext(synchronized) { total += good.cost }
            }
        }

        val baker2 = launch {
            orders.drop(50_000).forEach { item ->
                val good = bake(item)
                withContext(synchronized) { total += good.cost }
            }
        }
    }

    println("Total income: $${String.format("%,d", total)}")
}
