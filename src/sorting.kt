import kotlin.system.measureTimeMillis
import kotlin.system.measureNanoTime
import kotlin.random.Random

fun bubbleSort(array: IntArray) {
    val n = array.size
    var swapped: Boolean

    for (i in 0 until n - 1) {
        swapped = false

        for (j in 0 until n - 1 - i) {
            if (array[j] > array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp

                swapped = true
            }
        }

        if (!swapped) break
    }
}

fun quickSort(arr: IntArray, left: Int = 0, right: Int = arr.size - 1): IntArray {
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

fun getBubleTime(arr: IntArray, print: Boolean = false): Unit{
    val bubbleSortTime = measureNanoTime {
        val resultBubble = bubbleSort(arr) 
        if(print){
            println("BubbleSort result: ${resultBubble.joinToString(" ")}")
        }
    }
    println("BubbleSort time: $bubbleSortTime ms")
}

fun getQuickTime(arr: IntArray, print: Boolean = false): Unit{
    val quickSortTime = measureNanoTime {
        val resultQuick = quickSort(arr) 
        if(print){
            println("QuickSort result: ${resultQuick.joinToString(" ")}")
        }
    }
    println("QuickSort time: $quickSortTime ms")
}

fun main(){
    val arr = IntArray(1000) { Random.nextInt(0, 1000) }
    getQuickTime(arr.copyOf(), true);
    getBubleTime(arr.copyOf());
}