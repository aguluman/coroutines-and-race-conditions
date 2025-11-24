import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    var total = 0

    runBlocking(Dispatchers.Default) {
        val baker = launch {
            orders.forEach { item ->
                val good = bake(item)
                total += good.cost
            }
        }
    }

    println("Total income: $${String.format("%,d", total)}")
}
