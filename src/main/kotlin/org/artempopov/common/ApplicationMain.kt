package org.artempopov.common

import com.sun.xml.internal.fastinfoset.util.StringArray
import org.artempopov.client.botMain
import org.artempopov.client.clientMain
import org.artempopov.serverFirst.serverMain
import java.awt.BorderLayout
import java.awt.Frame
import javax.swing.*

/**
 * Entry point
 */
fun main(args: Array<String>) {
    val frame = JFrame("AlphaServer")
    frame.setSize(500, 500)
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.isResizable = false

    frame.contentPane.add(createMainPanel(args, frame), BorderLayout.CENTER)

    frame.isVisible = true
}

private fun createMainPanel(args: Array<String>, frame: JFrame): JPanel {
    val panel = JPanel()
    panel.layout = BoxLayout(panel, BoxLayout.PAGE_AXIS)

    panel.add(JLabel("Choose entry point"))
    panel.add(createButtonPanel(args, frame))

    return panel
}

private fun createButtonPanel(args: Array<String>, owner: JFrame): JPanel {
    val panel = JPanel()
    panel.layout = BoxLayout(panel, BoxLayout.LINE_AXIS)

    val serverButton = JButton("Server application")
    val clientButton = JButton("Client application")
    val botButton = JButton("Bot farm")

    serverButton.addActionListener{
        serverMain(args)
        owner.dispose()
    }

    clientButton.addActionListener{
        clientMain(args)
        owner.dispose()
    }

    botButton.addActionListener{
        botMain(args)
        owner.dispose()
    }

    panel.add(serverButton)
    panel.add(clientButton)
    panel.add(botButton)

    return panel
}