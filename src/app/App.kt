package app

import lib.sRAD.gui.sComponent.SMenuBar
import lib.sRAD.gui.tool.setMainBar
import lib.sRAD.gui.tool.setProperties
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JOptionPane

class App: JFrame(){

    init {
        addMenuBar()

        setMainBar("Multiplicación de matrices dispersas")
        setProperties()
    }

    private fun addMenuBar() {
        val menuBar = SMenuBar()
        add(menuBar)

        //File
        val file = JMenu("File")
        menuBar.add(file)

        val settings = JMenuItem("Settings...")
        file.add(settings)

        //Help
        val help = JMenu("Help")
        menuBar.add(help)

        val about = JMenuItem("About")
        about.addActionListener {
            JOptionPane.showMessageDialog(null, "" +
                    "This software has been developed by Jean Carlos Santoya Cabrera." +
                    "\nReferences:" +
                    "\n - https://porcomputador.com/", "About", JOptionPane.INFORMATION_MESSAGE
            )
        }
        help.add(about)
    }

}