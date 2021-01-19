package app

import javax.swing.JOptionPane

class MatrizDispersa {

    private val nodosColumna = mutableListOf<NodoColumna>()

    fun insertar(fila: Int, columna: Int, valor: Int) {
        //verifica que la posición sea válida
        if (columna < 0 || fila < 0) {
            JOptionPane.showMessageDialog(
                null,
                "Las posiciones negativas en la matriz, no están permitidas",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
            return
        }

        //si el valor es cero, elimina
        if (valor == 0) {
            eliminar(fila, columna)
            return
        }

        //si no hay valores
        if (nodosColumna.isEmpty()) {
            val nodo = NodoColumna(columna)
            nodo.insertar(fila, valor)
            nodosColumna.add(nodo)

            return
        }

        //si el nodo columna existe
        for (i in nodosColumna) {
            if (i.columna == columna) {
                i.insertar(fila, valor)
                return
            }
        }

        //se crea el nodo
        val nodo = NodoColumna(columna)
        nodo.insertar(fila, valor)

        //si el valor de la columna es mayor o igual que algún nodo columna
        for (i in nodosColumna.indices) {
            if (columna >= nodosColumna[i].columna) {
                nodosColumna.add(i, nodo)

                return
            }
        }

        //si el valor de la columna es el mayor
        nodosColumna.add(nodo)
    }

    private fun eliminar(fila: Int, columna: Int) {
        for (i in nodosColumna.indices) {
            if (nodosColumna[i].columna == columna) {
                nodosColumna[i].eliminar(fila)
                if (nodosColumna[i].getSize() == 0)
                    nodosColumna.removeAt(columna)
            }
        }
    }

}

class NodoFila (fila: Int) {
    val fila = fila
}

class NodoColumna(columna: Int) {
    val columna = columna

    private val nodosFila = mutableListOf<NodoFila>()

    fun eliminar (fila: Int) {
        for (i in nodosFila.indices) {
            if (nodosFila[i].fila == fila) {
                nodosFila.removeAt(i)
                break
            }
        }
    }

    fun insertar (fila: Int, columna: Int){

    }

    fun getSize (): Int {
        return nodosFila.size
    }

}