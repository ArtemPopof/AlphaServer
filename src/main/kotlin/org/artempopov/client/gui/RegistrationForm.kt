package org.artempopov.client.gui

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import javax.swing.*

const val TITLE = "Регистрация"
private const val WINDOW_WIDTH = 500
private const val WINDOW_HEIGHT = 500

class RegistrationForm(): JFrame(TITLE) {

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
            //RegistrationManager.register(getChosenShape(), getChoosenColor())
        }
        exitButton.addActionListener{
            this.dispose()
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