// Marco Antonio Pavia Flores
// Main.java

public class Main {
    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // 1. Metemos unos cuantos números para armar el árbol
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);

        System.out.print("Árbol original (InOrder): ");
        arbol.imprimirInOrder(); // Debería salir: 20 30 40 50 70

        // 2. Probamos eliminar un nodo (por ejemplo el 20, que no tiene hijos - Escenario 1)
        System.out.println("\nEliminando el 20 (sin hijos)...");
        arbol.eliminar(20);

        System.out.print("Árbol actualizado: ");
        arbol.imprimirInOrder(); // Debería salir: 30 40 50 70

        // 3. Probamos eliminar el 30 (que tiene un solo hijo, el 40 - Escenario 2)
        System.out.println("\nEliminando el 30 (con 1 hijo)...");
        arbol.eliminar(30);

        System.out.print("Árbol actualizado final: ");
        arbol.imprimirInOrder(); // Debería salir: 40 50 70
    }
}
