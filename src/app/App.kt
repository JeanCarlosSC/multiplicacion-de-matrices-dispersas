package app

import lib.sRAD.gui.tool.setMainBar
import lib.sRAD.gui.tool.setProperties
import javax.swing.JFrame

class App: JFrame(){

    init {
        setMainBar("Multiplicación de matrices dispersas")
        setProperties()
    }

}