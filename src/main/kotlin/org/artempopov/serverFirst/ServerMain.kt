package org.artempopov.serverFirst

import org.artempopov.serverFirst.net.Dispatcher
import org.artempopov.serverFirst.net.WorldNotifier
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.WindowConstants

/**
 * Server entry point
 */
fun serverMain(args: Array<String>) {
    println("Starting server...")

    Dispatcher
    WorldNotifier

    createStubGUI()

    println("Server listening...")
}

private fun createStubGUI() {
    val frame = JFrame("Server")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.setSize(500, 500)
    frame.isResizable = false

    val panel = JPanel(BorderLayout())
    panel.background = Color.WHITE
    panel.add(JLabel("Server is working..."), BorderLayout.CENTER)

    frame.contentPane = panel
    frame.isVisible = true
}