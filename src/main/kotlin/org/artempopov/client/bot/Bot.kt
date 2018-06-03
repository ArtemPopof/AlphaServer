package org.artempopov.client.bot

import org.artempopov.client.graphics.Drawable
import org.artempopov.client.net.Connection
import org.artempopov.client.net.getAllShapesFromServer

const val updatePeriod = 1000 / 15

/**
 * Class for bot simulation
 */
class Bot(private val connection: Connection) {

    private var shapes: List<Drawable> = ArrayList()
    private val worldUpdaterThread = Thread(createUpdaterTask(), "WorldUpdater $connection")

    init {
        worldUpdaterThread.start()
    }

    private fun createUpdaterTask(): Runnable {
        return Runnable {
            var startTime = System.currentTimeMillis()

            while (!Thread.currentThread().isInterrupted) {
                shapes = getAllShapesFromServer(connection)
                sleepRemainingTimeInLoop(startTime)
                startTime = System.currentTimeMillis()
            }
        }
    }

    private fun sleepRemainingTimeInLoop(startTime: Long) {
        val cycleTime = System.currentTimeMillis() - startTime

        val timeReminder = updatePeriod - cycleTime
        if (timeReminder > 0) {
            Thread.sleep(timeReminder)
        }
    }
}
