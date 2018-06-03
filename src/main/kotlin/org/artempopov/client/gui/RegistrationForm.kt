package org.artempopov.client.gui

import org.apache.logging.log4j.LogManager
import org.artempopov.client.controller.DefaultController
import org.artempopov.client.net.Connection
import org.artempopov.client.registration.RegistrationException
import org.artempopov.client.registration.RegistrationManager
import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import javax.swing.*

private const val TITLE = "Регистрация"
private const val LOG_TAG = "RegistrationForm"
private const val WINDOW_WIDTH = 500
private const val WINDOW_HEIGHT = 500

class RegistrationForm(connection: Connection): JFrame(TITLE) {

    private val LOG = LogManager.getLogger()

    private val connection: Connection = connection
    private val colorComboBox = createColorComboBox()
    private val shapeComboBox = createShapeComboBox()
    private val okButton = JButton("Create")
    private val exitButton = JButton("Exit")

    init {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        this.isResizable = false
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        this.isVisible = true

        fillForm()
        setupActions()
    }

    private fun createColorComboBox(): JComboBox<String> {
        val array = arrayOf("RED", "GREEN", "BLUE", "BLACK", "ORANGE", "VIOLET")

        return JComboBox(array)
    }

    private fun createShapeComboBox(): JComboBox<String> {
        return JComboBox(arrayOf("SQUARE", "CIRCLE", "TRIANGLE"))
    }

    private fun fillForm() {
        this.add(createMainPanel())
    }

    private fun createMainPanel(): JPanel {
        val panel = JPanel()

        panel.add(JLabel("Choose color:"))
        panel.add(colorComboBox)
        panel.add(JLabel("Choose shape:"))
        panel.add(shapeComboBox)
        panel.add(createButtonsPanel())

        return panel
    }

    private fun createButtonsPanel(): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.LINE_AXIS)

        panel.add(okButton)
        panel.add(exitButton)

        return panel
    }

    private fun setupActions() {
        okButton.addActionListener{
            performRegistration()
        }
        exitButton.addActionListener{
            this.dispose()
        }
    }

    private fun performRegistration() {
        try {
            RegistrationManager.register(getChosenShape(), getChoosenColor(), connection)

            LOG.info("Registered!")
            JOptionPane.showMessageDialog(this, "Registered Successful",
                    "Registration", JOptionPane.INFORMATION_MESSAGE)

            this.dispose()
            DefaultController.registrationCompleted()
        } catch (e: RegistrationException) {
            LOG.error(LOG_TAG, "Registration failed")
            JOptionPane.showMessageDialog(this, "Registration failed",
                    "Registration", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun getChosenShape(): ShapeType {
        val selectedItem = shapeComboBox.selectedItem as String

        return when (selectedItem) {
            "SQUARE" -> ShapeType.SQUARE
            "TRIANGLE" -> ShapeType.TRIANGLE
            "CIRCLE" -> ShapeType.CIRCLE
            else -> throw IllegalStateException("Invalid shape: $selectedItem")
        }
    }

    private fun getChoosenColor(): ShapeColor {
        val selectedItem = colorComboBox.selectedItem as String

        return when (selectedItem) {
            "BLACK" -> ShapeColor.BLACK
            "BLUE" -> ShapeColor.BLUE
            "ORANGE" -> ShapeColor.ORANGE
            "VIOLET" -> ShapeColor.VIOLET
            "RED" -> ShapeColor.RED
            "GREEN" -> ShapeColor.GREEN
            else -> throw IllegalStateException("Invalid color: $selectedItem")
        }
    }
}