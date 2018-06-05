package org.artempopov.common.util

/**
 * Sleep remaining time from last tick
 */
fun sleepRemainingTime(lastTime: Long, updatePeriod: Long) {
    val timeLeft = System.currentTimeMillis() - lastTime
    val diff = updatePeriod - timeLeft
    if (diff > 0) {
        Thread.sleep(diff)
    }
}