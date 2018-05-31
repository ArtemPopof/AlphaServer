package org.artempopov.client.gui

import org.artempopov.client.world.WorldUpdater

/**
 * Switches windows when it needed
 */
object WindowManager {

    /**
     * Registration completed
     */
    fun registrationCompleted() {
        val scene = MainFrame()
        val worldUpdater = WorldUpdater
        worldUpdater.setScene(scene)
        worldUpdater.start()
    }

}
