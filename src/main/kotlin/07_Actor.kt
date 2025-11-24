import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor

sealed interface TotalMessage {
    data class Increase(val amount: Int) : TotalMessage
    data object Print : TotalMessage
}

@OptIn(ObsoleteCoroutinesApi::class)
fun main() = runBlocking<Unit>(Dispatchers.Default) {
    val total = actor<TotalMessage> {
        var amount = 0

        for (message in channel) {
            when (message) {
                is TotalMessage.Increase -> amount += message.amount
                is TotalMessage.Print    -> println("Total income: $${String.format("%,d", amount)}")
            }
        }
    }

    coroutineScope {
        val baker = launch {
            orders.take(50_000).forEach { item ->
                val good = bake(item)
                total.send(TotalMessage.Increase(good.cost))
            }
        }

        val baker2 = launch {
            orders.drop(50_000).forEach { item ->
                val good = bake(item)
                total.send(TotalMessage.Increase(good.cost))
            }
        }
    }

    total.send(TotalMessage.Print)
    total.close()
}
