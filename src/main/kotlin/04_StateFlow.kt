import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val total = MutableStateFlow(0)

    runBlocking(Dispatchers.Default) {
        val baker = launch {
            orders.take(50_000).forEach { item ->
                val good = bake(item)
                total.update { it + good.cost }
            }
        }

        val baker2 = launch {
            orders.drop(50_000).forEach { item ->
                val good = bake(item)
                total.update { it + good.cost }
            }
        }
    }

    println("Total income: $${String.format("%,d", total.value)}")
}
