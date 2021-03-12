@file:Suppress("UnstableApiUsage")

import com.google.common.hash.Hashing
import io.github.rctcwyvrn.blake3.Blake3
import io.lktk.NativeBLAKE3
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    val entries = List(777) {
        val x = it + 1000
        Random.Default.nextBytes(x * it + 2000)
    }
    println("Collected the following sizes: from ${entries.map { it.size }.minOrNull()!! }" to "${entries.map { it.size }.maxOrNull()!!}")
    val sha1 = Hashing.sha1()
    val sha256 = Hashing.sha256()
    val sha512 = Hashing.sha512()
    val blake3Native = NativeBLAKE3()
    blake3Native.initDefault()
    val blake3Java = Blake3.newInstance();

    var sha1Time: Long = -1
    var sha256Time: Long = -1
    var sha512Time: Long = -1
    var blake3NativeTime: Long = -1
    var blake3JavaTime: Long = -1
    
    repeat(1) {
        sha1Time = measureTimeMillis {
            for (entry in entries) {
                sha1.hashBytes(entry).asBytes()
            }
        }
        sha256Time = measureTimeMillis {
            for (entry in entries) {
                sha256.hashBytes(entry).asBytes()
            }
        }
        sha512Time = measureTimeMillis {
            for (entry in entries) {
                sha512.hashBytes(entry).asBytes()
            }
        }
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
    }

    println("sha1   = $sha1Time ms")
    println("sha256 = $sha256Time ms")
    println("sh512 = $sha512Time ms")
    println("blake3 = $blake3NativeTime ms".padEnd(30))
    println("blake3java = $blake3JavaTime ms".padEnd(30))

}