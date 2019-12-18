package cubehash

private val internalState = IntArray(32)

fun hash(message: ByteArray): ByteArray {
    internalState[0] = 64
    internalState[1] = 32
    internalState[2] = 16
    rounds(160)
    var inputSize = message.size
    inputSize++
    inputSize += 32 - inputSize % 32
    val inputBytes = ByteArray(inputSize)
    System.arraycopy(message, 0, inputBytes, 0, message.size)
    inputBytes[message.size] = 128.toByte()
    val input = IntArray(inputSize / 4)
    var i = 0
    while (i < inputSize) {
        input[i / 4] = (inputBytes[i].toInt() and 0xFF) or
                ((inputBytes[i + 1].toInt() and 0xFF) shl 8) or
                ((inputBytes[i + 2].toInt() and 0xFF) shl 16) or
                ((inputBytes[i + 3].toInt() and 0xFF) shl 24)
        i += 4
    }
    var block = 0
    while (block < input.size) {
        for (j in 0..7) {
            internalState[j] = internalState[j] xor input[block + j]
        }
        rounds(16)
        block += 8
    }
    internalState[31] = internalState[31] xor 1
    rounds(160)
    val result = ByteArray(64)
    for (j in 0..15) {
        result[j * 4] = internalState[j].toByte()
        result[j * 4 + 1] = (internalState[j] ushr 8).toByte()
        result[j * 4 + 2] = (internalState[j] ushr 16).toByte()
        result[j * 4 + 3] = (internalState[j] ushr 24).toByte()
    }
    return result
}

fun rounds(n: Int) {
    for (i in 0 until n) {
        addRotate(7, 25)
        swapXorSwap(8, 2)
        addRotate(11, 21)
        swapXorSwap(4, 1)
    }
}

fun addRotate(r: Int, s: Int) {
    for (i in 0..15) {
        internalState[16 + i] += internalState[i]
        internalState[i] = internalState[i] shl r xor (internalState[i] ushr s)
    }
}

fun swapXorSwap(firstSwapBits: Int, secondSwapBits: Int) {
    var tmp: Int
    var j: Int
    for (i in 0..15) {
        if (i and firstSwapBits != 0) {
            j = i xor firstSwapBits
            tmp = internalState[i] xor internalState[j + 16]
            internalState[i] = internalState[j] xor internalState[i + 16]
            internalState[j] = tmp
        }
    }
    for (i in 16..31) {
        if (i and secondSwapBits != 0) {
            j = i xor secondSwapBits
            tmp = internalState[i]
            internalState[i] = internalState[j]
            internalState[j] = tmp
        }
    }
}

private val HEX_ARRAY = "0123456789abcdef".toCharArray()
fun bytesToHex(bytes: ByteArray): String? {
    val hexChars = CharArray(bytes.size * 2)
    for (j in bytes.indices) {
        val v: Int = bytes[j].toInt() and 0xFF
        hexChars[j * 2] = HEX_ARRAY[v ushr 4]
        hexChars[j * 2 + 1] = HEX_ARRAY[v and 0x0F]
    }
    return String(hexChars)
}

fun main() {
    println(bytesToHex(hash("The quick brown fox jumps over the lazy dog".toByteArray())))
}