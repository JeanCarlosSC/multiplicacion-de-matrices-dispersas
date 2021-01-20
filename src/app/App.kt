package app

import lib.sRAD.gui.resource.fontTitle
import lib.sRAD.gui.sComponent.SButton
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SMenuBar
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.tool.setMainBar
import lib.sRAD.gui.tool.setProperties
import javax.swing.*

class App: JFrame(){

    //DataPane
    private val tfMatriz = JTextField()
    private val tfFila = JTextField()
    private val tfColumna = JTextField()
    private val tfValor = JTextField()
    //Matriz A
    private var matrizA = MatrizDispersa()
    private val pMatrizA = SPanel(32, 180, 380, 500)
    private val taMatrizA = JTextArea()
    //Matriz B
    private var matrizB = MatrizDispersa()
    private val pMatrizB = SPanel(442, 180, 380, 500)
    private val taMatrizB = JTextArea()
    //Matriz C
    private var matrizC = MatrizDispersa()
    private val pMatrizC = SPanel(852, 180, 380, 500)
    private val taMatrizC = JTextArea()
    //Operation symbols
    private val lProduct = SLabel(417, 400, 20, 30, "X", fontTitle)
    private val lEqual = SLabel(827, 400, 20, 30, "=", fontTitle)

    init {
        addMenuBar()
        addDataPane()
        addMatrices()

        setMainBar("Multiplicación de matrices dispersas")
        setProperties()
    }

    private fun addMenuBar() {
        val menuBar = SMenuBar()
        add(menuBar)

    /*
        //File
        val file = JMenu("File")
        menuBar.add(file)

        val settings = JMenuItem("Settings...")
        file.add(settings)
    */
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

    private fun addDataPane() {
        val pData = SPanel(32, 90, 1200, 60)
        add(pData)

        val lText = SLabel(30, 20, 200, str="Insertar en matriz")
        pData.add(lText)

        tfMatriz.setProperties(172, 17, 60, hAlignment = JTextField.RIGHT)
        tfMatriz.addActionListener { insertar() }
        pData.add(tfMatriz)

        val lText1 = SLabel(240, 20, 50, str="fila")
        pData.add(lText1)

        tfFila.setProperties(270, 17, 60, hAlignment = JTextField.RIGHT)
        tfFila.addActionListener { insertar() }
        pData.add(tfFila)

        val lText2 = SLabel(340, 20, 70, str="columna")
        pData.add(lText2)

        tfColumna.setProperties(410, 17, 60, hAlignment = JTextField.RIGHT)
        tfColumna.addActionListener { insertar() }
        pData.add(tfColumna)

        val lText3 = SLabel(480, 20, 60, str="el valor")
        pData.add(lText3)

        tfValor.setProperties(540, 17, 60, hAlignment = JTextField.RIGHT)
        tfValor.addActionListener { insertar() }
        pData.add(tfValor)

        val bInsertar = SButton(680, 15, 100, 32, "Insertar")
        bInsertar.addActionListener { insertar() }
        pData.add(bInsertar)

        val bCalcular = SButton(800, 15, 100, 32, "Calcular")
        bCalcular.addActionListener { calcular() }
        pData.add(bCalcular)

        val bBorrar = SButton(920, 15, 100, 32, "Borrar")
        bBorrar.addActionListener { borrar() }
        pData.add(bBorrar)

    }

    private fun insertar() {
        try {
            val fila = tfFila.text.toInt()
            val columna = tfColumna.text.toInt()
            val valor = tfValor.text.toInt()

            when (tfMatriz.text) {
                "A" -> matrizA.insertar(fila, columna, valor)
                "B" -> matrizB.insertar(fila, columna, valor)
                else -> JOptionPane.showMessageDialog(null, "Ingrese en matriz A o B", "Error", JOptionPane.ERROR_MESSAGE)
            }
            updateUI()
        }
        catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Ingrese valores enteros válidos", "Error", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun calcular() {
        matrizC = multiplicar(matrizA, matrizB)
        updateUI()
    }

    private fun borrar() {
        matrizA = MatrizDispersa()
        matrizB = MatrizDispersa()
        matrizC = MatrizDispersa()
        updateUI()
    }

    private fun updateUI() {
        taMatrizA.text = matrizA.toString()

        taMatrizB.text = matrizB.toString()

        taMatrizC.text = matrizC.toString()

        repaint()
    }

    private fun addMatrices() {
        val lMatrizA = SLabel(130, 20, 200, 32, "Matriz A", fontTitle)
        pMatrizA.add(lMatrizA)

        taMatrizA.setProperties(50, 80, 300, 418, false, background = null, border = null)
        pMatrizA.add(taMatrizA)

        add(pMatrizA)

        val lMatrizB = SLabel(130, 20, 200, 32, "Matriz B", fontTitle)
        pMatrizB.add(lMatrizB)

        taMatrizB.setProperties(50, 80, 300, 418, false, background = null, border = null)
        pMatrizB.add(taMatrizB)

        add(pMatrizB)

        val lMatrizC = SLabel(130, 20, 200, 32, "Matriz C", fontTitle)
        pMatrizC.add(lMatrizC)

        taMatrizC.setProperties(50, 80, 300, 418, false, background = null, border = null)
        pMatrizC.add(taMatrizC)

        add(pMatrizC)

        add(lProduct)
        add(lEqual)

        updateUI()
    }

}