import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() {
    var total = 0
    val mutex = Mutex()

    runBlocking(Dispatchers.Default) {
        val baker = launch {
            orders.take(50_000).forEach { item ->
                val good = bake(item)
                mutex.withLock { total += good.cost }
            }
        }

        val baker2 = launch {
            orders.drop(50_000).forEach { item ->
                val good = bake(item)
                mutex.withLock { total += good.cost }
            }
        }
    }

    println("Total income: $${String.format("%,d", total)}")
}
