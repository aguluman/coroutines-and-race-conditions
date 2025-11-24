import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    val total = runBlocking(Dispatchers.Default) {
        val baker = async {
            orders.take(50_000).sumOf { item -> bake(item).cost }
        }

        val baker2 = async {
            orders.drop(50_000).sumOf { item -> bake(item).cost }
        }

        baker.await() + baker2.await()
    }

    println("Total income: $${String.format("%,d", total)}")
}
