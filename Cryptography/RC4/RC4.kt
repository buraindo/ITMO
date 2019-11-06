package rc4

import java.io.File
import kotlin.experimental.xor

private fun ByteArray.swap(l: Int, r: Int) {
    val temp = this[l]
    this[l] = this[r]
    this[r] = temp
}

class RC4(key: ByteArray) {
    private val s = ByteArray(256)
    private var x = 0
    private var y = 0

    init {
        val keyLength = key.size
        for (i in 0 until 256) {
            s[i] = i.toByte()
            if (s[i] < 0) s[i] = (s[i] + 128).toByte()
        }
        var j = 0
        for (i in 0 until 256) {
            j = (j + s[i] + key[i % keyLength]) % 256
            s.swap(i, j)
        }
    }

    fun encode(bytes: ByteArray): ByteArray {
        val cipher = ByteArray(bytes.size)
        for (m in bytes.indices) {
            cipher[m] = bytes[m] xor keyItem()
        }
        return cipher
    }

    fun decode(bytes: ByteArray): ByteArray {
        return encode(bytes)
    }

    private fun keyItem(): Byte {
        x = (x + 1) % 256
        y = (y + s[x]) % 256
        s.swap(x, y)
        return s[(s[x] + s[y]) % 256]
    }
}

fun main(args: Array<String>) {
    File("res/rc4/plain.txt").inputStream().use { input ->
        File("res/rc4/encoded.txt").outputStream().use { output ->
            val rc4 = RC4(args[0].toByteArray())
            val bytes = ByteArray(16)
            while (input.read(bytes) > 0) {
                output.write(rc4.encode(bytes))
            }
        }
    }
    File("res/rc4/encoded.txt").inputStream().use { input ->
        File("res/rc4/decoded.txt").outputStream().use { output ->
            val rc4 = RC4(args[0].toByteArray())
            val bytes = ByteArray(16)
            while (input.read(bytes) > 0) {
                output.write(rc4.decode(bytes))
            }
        }
    }
}