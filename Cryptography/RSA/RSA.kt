package rsa

import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom

data class Holder(var x: BigInteger, var y: BigInteger)

val random = Random.asJavaRandom()

fun multiply(lhs: BigInteger, rhs: BigInteger, modulo: BigInteger): BigInteger {
    var result = BigInteger.ZERO
    var a = lhs
    var b = rhs
    while (b > BigInteger.ZERO) {
        if (b % BigInteger.TWO == BigInteger.ONE) {
            result = (result + a) % modulo
        }
        a = (a + a) % modulo
        b /= BigInteger.TWO
    }
    return result
}

fun moduloPower(number: BigInteger, exp: BigInteger, modulo: BigInteger): BigInteger {
    var result = BigInteger.ONE
    var e = exp
    var a = number
    while (e > BigInteger.ZERO) {
        if ((e and BigInteger.ONE) > BigInteger.ZERO) {
            result = multiply(result, a, modulo)
        }
        a = multiply(a, a, modulo)
        e = e shr 1
    }
    return result
}

fun gcd(a: BigInteger, b: BigInteger, h: Holder): BigInteger {
    if (a == BigInteger.ZERO) {
        h.x = BigInteger.ZERO
        h.y = BigInteger.ONE
        return b
    }
    val h1 = Holder(BigInteger.ZERO, BigInteger.ZERO)
    val d = gcd(b % a, a, h1)
    h.x = h1.y - (b / a) * h1.x
    h.y = h1.x
    return d
}

fun millerRabin(n: BigInteger): Boolean {
    if (n == BigInteger.TWO || n == BigInteger.valueOf(3)) return true
    if (n < BigInteger.TWO || n % BigInteger.TWO == BigInteger.ZERO) return false
    var t = n - 1
    var s = 0L
    while ((t and BigInteger.ONE) == BigInteger.ZERO) {
        t /= BigInteger.TWO
        ++s
    }
    for (i in 0 until 2) {
        val a = BigInteger.valueOf(random.nextDouble().toLong()) % (n - 2) + BigInteger.TWO
        var x = moduloPower(a, t, n)
        if (x == BigInteger.ONE || x == n - 1) continue
        for (r in 1 until s) {
            x = moduloPower(x, BigInteger.TWO, n)
            if (x == BigInteger.ONE) return false
            if (x == n - 1) break
        }
        if (x != n - 1) return false
    }
    return true
}

fun generateLargePrime(): BigInteger {
    val p = BigInteger(1024, random)
    return p.nextProbablePrime()
}

fun send(m: BigInteger, e: BigInteger, n: BigInteger): BigInteger {
    return moduloPower(m, e, n)
}

fun receive(c: BigInteger, d: BigInteger, n: BigInteger): BigInteger {
    return moduloPower(c, d, n)
}

fun main() {
    val p = generateLargePrime()
    val q = generateLargePrime()
    val n = p * q
    val phi = (p - 1) * (q - 1)
    val e = BigInteger.valueOf(12077010341437)
    val h = Holder(BigInteger.ZERO, BigInteger.ZERO)
    gcd(e, phi, h)
    val d = (h.x % phi + phi) % phi
    val message = BigInteger.valueOf(123456789L)
    val ciphered = send(message, e, n)
    val original = receive(ciphered, d, n)
    println("Bob sent $message, ciphered into $ciphered")
    println("Alice received $ciphered, deciphered into $original")
}

private operator fun BigInteger.minus(i: Long): BigInteger {
    return this.minus(BigInteger.valueOf(i))
}

private operator fun BigInteger.plus(i: Long): BigInteger {
    return this.plus(BigInteger.valueOf(i))
}
