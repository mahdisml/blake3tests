import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlinx.io.*
import kotlin.io.*

fun main (){
    fun printIntArray(data:IntArray){
        println()
        data.map {
            print(it)
        }
    }


    fun wordsFromLEBytes(bytes: ByteArray): IntArray {
        val words = IntArray(bytes.size / 4)
        val buf = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN)
        for (i in words.indices) {
            words[i] = buf.int
        }
        return words
    }
    fun ktwordsFromLEBytes(bytes: IntArray): IntArray {
        val words = IntArray(bytes.size / 4)
        words.mapIndexed { index, _ ->
            words[index] = (bytes[0] or
                        (bytes[1] shl 8) or
                        (bytes[2] shl 16) or
                        (bytes[3] shl 24))
        }
        return words
    }

    printIntArray(wordsFromLEBytes(
        byteArrayOf(1,4,6,1,3)
    ))

    printIntArray(ktwordsFromLEBytes(
        intArrayOf(1,4,6,1,3)
    ))



}