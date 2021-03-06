package org.artempopov.common.net

import org.apache.logging.log4j.LogManager
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.Socket

private const val BUFFER_SIZE = 1024 * 10
private const val LOG_TAG = "NetUtil"
const val REQUEST_PACKET_VERSION = 0x102
// common net util functions

private val LOG = LogManager.getLogger()

fun readSocketData(socket: Socket): ByteArray {
    val bis = BufferedInputStream(socket.getInputStream())

    val buffer = ByteArray(BUFFER_SIZE)

    var byte = bis.read()
    var counter = 0
    while (byte != -1) {
        buffer[counter++] = byte.toByte()
        byte = try {
            bis.read()
        } catch (e: Exception) {
            LOG.error(LOG_TAG, "Error while reading data from socket: " + e)
            throw e
        }
    }

    val data = ByteArray(counter)

    System.arraycopy(buffer, 0, data, 0, counter)

    return data
}

/**
 * Send data to socket and shutdown output
 */
fun send(byteArray: ByteArray, socket: Socket) {
    val outStream = BufferedOutputStream(socket.getOutputStream())

    outStream.write(byteArray)
    outStream.flush()
    socket.shutdownOutput()
}
