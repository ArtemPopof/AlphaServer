package org.artempopov.common.net

import org.apache.logging.log4j.LogManager
import java.io.BufferedInputStream
import java.net.Socket

const val BUFFER_SIZE = 1024
const val LOG_TAG = "NetUtil"
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
