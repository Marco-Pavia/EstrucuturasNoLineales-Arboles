// Marco Antonio Pavia Flores
// Nodo.java

public class Nodo {
    int valor;  // El dato numérico que almacena el nodo
    Nodo izq;   // Apuntador a la rama izquierda (valores menores)
    Nodo der;   // Apuntador a la rama derecha (valores mayores)

    // Constructor para inicializar el nodo
    public Nodo(int valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
    }
}
