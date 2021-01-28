package app

import javax.swing.JOptionPane

class MatrizDispersa {

    internal val nodosColumna = mutableListOf<NodoColumna>()

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

        //si el valor de la columna es menor que algún nodo columna
        for (i in nodosColumna.indices) {
            if (columna < nodosColumna[i].columna) {
                nodosColumna.add(i, nodo)

                return
            }
        }

        //si el valor de la columna es el mayor
        nodosColumna.add(nodo)
    }

    private fun eliminar(fila: Int, columna: Int) {
        //elimina si el nodo existe
        for (i in nodosColumna.indices) {
            if (nodosColumna[i].columna == columna) {
                nodosColumna[i].eliminar(fila)
                //si el nodo queda vacío, se retira
                if (nodosColumna[i].getSize() == 0)
                    nodosColumna.removeAt(i)
                return
            }
        }
    }

    fun obtenerValor(fila: Int, columna: Int): Int {
        for (i in nodosColumna) {
            if (i.columna == columna)
                return i.obtenerValor(fila)
        }
        return 0
    }

    override fun toString(): String {
        var datos = "Fila      Columna   Valor"

        if (nodosColumna.isEmpty())
            return "La matriz está vacía"

        for (j in nodosColumna) {
            for (i in j.nodosFila) {
                datos += "\n${"%7d   %7d   %7d".format(i.fila, j.columna, i.valor)}"
            }
        }

        return datos
    }

}

class NodoFila (
    val fila: Int,
    var valor: Int
    )

class NodoColumna(val columna: Int) {
    internal val nodosFila = mutableListOf<NodoFila>()

    fun eliminar (fila: Int) {
        for (i in nodosFila.indices) {
            if (nodosFila[i].fila == fila) {
                nodosFila.removeAt(i)
                break
            }
        }
    }

    fun insertar (fila: Int, valor: Int){
        //si el nodo existe
        for (i in nodosFila) {
            if (i.fila == fila) {
                i.valor = valor
                return
            }
        }

        //crea nodo
        val nodo = NodoFila(fila, valor)

        //si es menor que alguno
        for (i in nodosFila.indices) {
            if (fila < nodosFila[i].fila) {
                nodosFila.add(i, nodo)
                return
            }
        }

        //si es el mayor
        nodosFila.add(nodo)
    }

    fun obtenerValor (fila: Int): Int {
        for (i in nodosFila) {
            if(i.fila == fila) {
                return i.valor
            }
        }
        return 0
    }

    fun getSize (): Int {
        return nodosFila.size
    }

}

fun multiplicar(matrizA: MatrizDispersa, matrizB: MatrizDispersa): MatrizDispersa {
    val result = MatrizDispersa()

    //apunta a cada valor de la matriz a
    for (j in matrizA.nodosColumna) {
        for (i in j.nodosFila) {
            //apunta a todas las columnas de la matriz b por cada valor de a
            for (b in matrizB.nodosColumna) {
                //suma el producto al resultado
                result.insertar(i.fila, b.columna,
                    result.obtenerValor(i.fila, b.columna) + i.valor*b.obtenerValor(j.columna))
            }
        }
    }

    return result
}
