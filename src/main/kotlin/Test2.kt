@file:Suppress("UnstableApiUsage")

import com.google.common.hash.Hashing
import io.github.rctcwyvrn.blake3.Blake3
import io.lktk.NativeBLAKE3
import kotlin.system.measureTimeMillis


fun main() {
    val entries = listOf<ByteArray>("salam".toByteArray())
    println("$entries")
    val blake3Native = NativeBLAKE3()
    blake3Native.initDefault()
    val blake3Java = Blake3.newInstance();
    val blake3KotlinFromJava = Blake3KotlinOptimizing.newInstance()
    val blake3KotlinOptimizing = Blake3KotlinOptimizing.newInstance()


    var blake3NativeTime: Long = -1
    var blake3JavaTime: Long = -1
    var blake3KotlinFromJavaTime: Long = -1
    var blake3KotlinOptimizingTime: Long = -1

    repeat(3) {

        blake3NativeTime = measureTimeMillis {
            for (entry in entries) {
                blake3Native.update(entry)
            }
        }
        blake3JavaTime = measureTimeMillis {
            for (entry in entries) {
                blake3Java.update(entry)
            }
        }
        blake3KotlinFromJavaTime = measureTimeMillis {
            for (entry in entries) {
                blake3KotlinFromJava.update(entry)
            }
        }
        blake3KotlinOptimizingTime = measureTimeMillis {
            for (entry in entries) {
                blake3KotlinOptimizing.update(entry)
            }
        }
    }

    println("blake3Native = $blake3NativeTime ms".padEnd(30))
    println("blake3Java = $blake3JavaTime ms".padEnd(30))
    println("blake3KotlinFromJava = $blake3KotlinFromJavaTime ms".padEnd(30))
    println("blake3KotlinOptimizing = $blake3KotlinOptimizingTime ms".padEnd(30))

    val output: ByteArray = blake3Native.output
    println(output)

}