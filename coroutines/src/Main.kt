import kotlinx.coroutines.*
import kotlin.random.Random


suspend fun quickSort(arr: IntArray, left: Int = 0, right: Int = arr.size - 1): IntArray {
    delay(100)
    var start = left
    var end = right
    val pivot = arr[(left + right) / 2]

    while (start <= end) {
        while (arr[start] < pivot) {
            start++
        }
        while (arr[end] > pivot) {
            end--
        }
        if (start <= end) {
            val temp = arr[start]
            arr[start] = arr[end]
            arr[end] = temp
            start++
            end--
        }
    }

    if (left < end) {
        quickSort(arr, left, end)
    }
    if (start < right) {
        quickSort(arr, start, right)
    }
    return arr
}

suspend fun temp(): String{
    delay(1000)
    return "27"
}

fun main() {
    println("++1")
    runBlocking {
        report()

    }
    println("++2")
}

suspend fun report()= coroutineScope {

    val t: Deferred<String> = async{     temp()}
    println("${t.await()}")
    launch {
        val arr = IntArray(100) { Random.nextInt(0, 1000) }
        quickSort(arr.copyOf())
        println("sort end")
    }
    launch {
        println("+++")
    }

    
}