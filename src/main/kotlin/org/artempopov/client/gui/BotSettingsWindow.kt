package org.artempopov.client.gui

import org.artempopov.client.controller.BotController
import javax.swing.*

private const val WINDOW_TITLE = "Bot farm settings"
private const val WINDOW_HEIGHT = 500
private const val WINDOW_WIDTH = 500

/**
 * Settings window for bot farm
 */
class BotSettingsWindow: JFrame(WINDOW_TITLE) {

    private val numberOfBots = JTextField()

    init {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        this.isResizable = false
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        this.isVisible = true

        addComponents()
    }

    private fun addComponents() {
        this.add(createMainPanel())
    }

    private fun createMainPanel(): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.PAGE_AXIS)

        panel.add(JLabel("Choose bot count for farm"))
        panel.add(numberOfBots)
        panel.add(createButtonsPanel())

        return panel
    }

    private fun createButtonsPanel(): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.LINE_AXIS)

        panel.add(createOkButton())
        panel.add(createExitButton())

        return panel
    }

    private fun createOkButton(): JButton {
        val button = JButton("Start")

        button.addActionListener{
            BotController.botConfigurationComplete(Integer.parseInt(numberOfBots.text))
        }

        return button
    }

    private fun createExitButton(): JButton {
        val button = JButton("Exit")

        button.addActionListener{
            this.dispose()
        }

        return button
    }
}