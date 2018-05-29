package org.artempopov.client.world

import org.artempopov.client.gui.MainFrame

/**
 * This object periodically request server for
 * info about world state and update client model with this info
 *
 * @author Artem Popov
 */
object WorldUpdater {

    private var scene: MainFrame? = null
    private val updater = Thread(createUpdateTask())
    private var running = false

    private fun createUpdateTask(): Runnable {
        return Runnable {
            while (running) {

            }
        }
    }

    /**
     * Set scene, where info about world stored
     */
    fun setScene(scene: MainFrame) {
        this.scene = scene
    }

    /**
     * Start world updater thread
     */
    fun start() {
        updater.start()
    }

    /**
     * Stop world updater thread
     */
    fun stop() {

    }
}