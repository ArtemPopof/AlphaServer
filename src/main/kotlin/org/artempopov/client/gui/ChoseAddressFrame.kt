package org.artempopov.client.gui

import javax.swing.*

private const val TITLE = "Set server address"

private const val WINDOW_WIDTH = 500
private const val WINDOW_HEIGHT = 500

/**
 * Frame for choosing server address
 */
class ChoseAddressFrame: JFrame(TITLE) {

    private val hostTf = JTextField()
    private val portTf = JTextField()

    init {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        this.isResizable = false
        this.defaultCloseOperation = javax.swing.WindowConstants.EXIT_ON_CLOSE
        this.isVisible = true

        setComponents()
    }

    private fun setComponents() {
        this.add(createMainPanel())
    }

    private fun createMainPanel(): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.PAGE_AXIS)

        panel.add(JLabel("Host"))
        panel.add(hostTf)
        panel.add(JLabel("Port"))
        panel.add(portTf)

        panel.add(createOkButton())
        panel.add(createExitButton())

        return panel
    }

    private fun createOkButton(): JButton {
        val button = JButton("Ok")

        button.addActionListener{
            WindowManager.addressIsSet(hostTf.text, Integer.parseInt(portTf.text), this)
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