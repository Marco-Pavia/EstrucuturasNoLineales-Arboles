// Marco Antonio Pavia Flores
// ArbolBinarioBusqueda.java

public class ArbolBinarioBusqueda {
    // La raíz es el nodo principal (la cabeza del árbol). Si está en null, el árbol está completamente vacío.
    private Nodo raiz;

    //Constructor
    public ArbolBinarioBusqueda() {
        this.raiz = null; // Inicializamos el árbol vacío.
    }

    // ==========================================================================
    // 1. INSERCIÓN: Mete un número nuevo respetando las reglas del ABB (menores izq, mayores der).
    // ==========================================================================
    public void insertar(int valor) {
        // Mandamos a llamar al método recursivo y actualizamos la raíz (por si el árbol estaba vacío).
        this.raiz = insertarRecursivo(this.raiz, valor);
    }

    // definimos el metodo que nos regrese nodo. Los parametros actual es donde estamos parados
    // y valor es el que queremos insertar.
    private Nodo insertarRecursivo(Nodo actual, int valor) {
        // CASO BASE (El freno): Si chocamos contra un espacio vacío (null),
        // aquí mero se crea el nodo físicamente en memoria con el valor nuevo.
        if (actual == null) {
            return new Nodo(valor);
        }

        // Si el número que quieres meter es MENOR que el nodo actual, te mueves hacia la RAMA IZQUIERDA.
        if (valor < actual.valor) {
            actual.izq = insertarRecursivo(actual.izq, valor); // actualizamos

            //Ojo actual es nodo.
            // Si el número es MAYOR, te mueves hacia la RAMA DERECHA.
        } else if (valor > actual.valor) {
            actual.der = insertarRecursivo(actual.der, valor);
        }
        // (Si el número ya existe, no hace nada para evitar duplicados en un ABB puro).
        return actual; // Regresa el nodo enlazado para mantener unida la estructura.
    }

    // ==========================================================================
    // 3. BÚSQUEDA: Usa la técnica de "Divide y Vencerás" con complejidad O(log n).
    // ==========================================================================
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(Nodo actual, int valor) {
        // 1. Si llegamos a un hueco vacío, el número no existe
        if (actual == null) {
            return false;
        }

        // 2. ¡Le atinamos! El número está guardado en esta caja
        if (valor == actual.valor) {
            return true;
        }

        // 3. Si el número buscado es menor, búscale por el brazo izquierdo
        if (valor < actual.valor) {
            return buscarRecursivo(actual.izq, valor);
        }

        // 4. Si es mayor, búscale por el brazo derecho
        return buscarRecursivo(actual.der, valor);
    }

    // ==========================================================================
    // 4. ELIMINACIÓN: Maneja los 3 pinches casos posibles para borrar un nodo.
    // ==========================================================================
    public void eliminar(int valor) {
        // le enseñamos la raiz al metodo eliminar
        this.raiz = eliminarRecursivo(this.raiz, valor); // al ponerle this deja de ser una
        //Variable temporal y al momento de actualizar raiz se asegura uno que el cambio sea
        // permanente
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) return null; // Si llegó al final y no lo halló, no hace nada.

        // Buscando el nodo que vamos a eliminar:
        if (valor < actual.valor) {
            actual.izq = eliminarRecursivo(actual.izq, valor); // actualizamos el brazo izq
        } else if (valor > actual.valor) {
            actual.der = eliminarRecursivo(actual.der, valor); // Actualizamos el brazo der.
        } else {

            // CASO 1: Es una HOJA (0 hijos). No cuelga nada de él.
            if (actual.izq == null && actual.der == null) {
                return null; // Regresa null al padre, lo que corta el enlace y borra el nodo de la RAM.
            }

            // CASO 2: Tiene EXACTAMENTE 1 HIJO (o izquierdo o derecho). Abuelo -> Papá -> Hijo : Abuelo --> Hijo
            if (actual.izq == null) return actual.der; // Se salta al nodo actual y conecta directo con su hijo
            if (actual.der == null) return actual.izq; // Se salta al nodo actual y conecta directo con su hijo

            // CASO 3: Tiene 2 HIJOS
            // Aplicamos la regla de oro: 1 paso a la DERECHA y TODO A LA IZQUIERDA HASTA EL FONDO (Sucesor inorden)
            Nodo sucesor = obtenerMinimo(actual.der);
            // Reemplazamos el valor del nodo que queremos borrar con el valor del sucesor encontrado.
            actual.valor = sucesor.valor;
            // Mandamos a borrar de forma recursiva el nodo sucesor original de su posición allá abajo.
            actual.der = eliminarRecursivo(actual.der, sucesor.valor);
        }
        return actual;
    }

    // Método auxiliar exclusivo del Caso 3:
    // Da exactamente 1 paso a la derecha (actual.der) y baja TODO a la izquierda hasta tocar fondo.
    private Nodo obtenerMinimo(Nodo actual) {
        while (actual.izq != null) {
            actual = actual.izq; // Baja por la rama izquierda buscando el menor absoluto.
        }
        return actual; // Devuelve el sucesor inorden exacto.
    }

    ///////////////////////////////////////////////////////////////////////////
    // Método principal para mandar a imprimir el árbol de forma ordenada (InOrder)
    public void imprimirInOrder() {
        // Arranca el recorrido desde la mera raíz (la puerta principal)
        imprimirRec(this.raiz);
        // Salto de línea al final para que la consola no se amontone
        System.out.println();
    }

    // Método recursivo que hace el trabajo nodo por nodo
    private void imprimirRec(Nodo actual) {
        // Si la caja actual sí existe (no es un hueco vacío / null), avanzamos
        if (actual != null) {
            // 1. Izquierda: Primero se mete hasta el fondo del brazo izquierdo (los más chicos)
            imprimirRec(actual.izq);

            // 2. Imprimir: Imprime el valor de la caja en la que está parado ahorita
            System.out.print(actual.valor + " ");

            // 3. Derecha: Se pasa a revisar el brazo derecho (los más grandes)
            imprimirRec(actual.der);
        }
    }
}
