@file:Suppress("UnstableApiUsage")

import com.google.common.hash.Hashing
import io.github.rctcwyvrn.blake3.Blake3
import io.lktk.NativeBLAKE3
import kotlin.random.Random
import kotlin.system.measureTimeMillis


fun main() {
    val entries = List(257) {
        val x = it + 1000
        Random.Default.nextBytes(x * it + 2000)
    }
    println("Collected the following sizes: from ${entries.map { it.size }.minOrNull()!! }" to "${entries.map { it.size }.maxOrNull()!!}")

    val blake3Native = NativeBLAKE3()
    blake3Native.initDefault()
    val blake3Java = Blake3.newInstance();
    val blake3KotlinFromJava = Blake3KotlinOptimizing.newInstance()
    val blake3KotlinOptimizing = Blake3KotlinOptimizing.newInstance()
    val smlblake3 = SMLBLAKE3.newInstance()

    var blake3NativeTime: Long = -1
    var blake3JavaTime: Long = -1
    var blake3KotlinFromJavaTime: Long = -1
    var blake3KotlinOptimizingTime: Long = -1
    var smlblake3Time: Long = -1

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
        smlblake3Time = measureTimeMillis {
            for (entry in entries) {
                smlblake3.update(entry)
            }
        }
    }

    println("blake3Native = $blake3NativeTime ms".padEnd(30))
    println("blake3Java = $blake3JavaTime ms".padEnd(30))
    println("blake3KotlinFromJava = $blake3KotlinFromJavaTime ms".padEnd(30))
    println("blake3KotlinOptimizing = $blake3KotlinOptimizingTime ms".padEnd(30))
    println("smlblake3 = $smlblake3Time ms".padEnd(30))
    println("--------------------------------------------------")
    println("blake3Native = ${blake3Native.output.size}")
    blake3Native.output.map {
        print(it)
    }
    println()
    println("blake3Java = ${blake3Java.digest().size}")
    blake3Java.digest().map {
        print(it)
    }
    println()
    println("blake3KotlinFromJava = ${blake3KotlinFromJava.digest().size}")
    blake3KotlinFromJava.digest().map {
        print(it)
    }
    println()
    println("blake3KotlinOptimizing = ${blake3KotlinOptimizing.digest().size}")
    blake3KotlinOptimizing.digest().map {
        print(it)
    }
    println()
    println("smlblake3 = ${smlblake3.digest().size}")
    smlblake3.digest().map {
        print(it)
    }

}