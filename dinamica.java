import java.util.ArrayList;
import java.util.Collections;

public class dinamica {

    // Variables recibidas
    private int k;
    private int n;
    private ArrayList<Contenedor> contenedores;

    // Tablas para Programación Dinámica
    private int[] memo; 
    private int[] siguiente;
    
    // NUEVO: Variables para guardar el resultado del cálculo y poder imprimirlo luego
    private int mejorLongitudGlobal;
    private int inicioMejorCamino;

    public dinamica(int k, int n, ArrayList<Contenedor> contenedores) {
        this.k = k;
        this.n = n;
        this.contenedores = contenedores;
        
        this.memo = new int[k];
        this.siguiente = new int[k];
        // Inicializamos a -1
        for(int i=0; i<k; i++) {
            memo[i] = -1; 
            siguiente[i] = -1;
        }
        
        this.mejorLongitudGlobal = 0;
        this.inicioMejorCamino = -1;
    }

    /**
     * SOLO CALCULA. No imprime nada.
     * Rellena la tabla memo y encuentra el nodo de inicio óptimo.
     */
    public void empezar() {
        // Reiniciamos variables por si se llama varias veces
        mejorLongitudGlobal = 0;
        inicioMejorCamino = -1;

        for (int i = 0; i < k; i++) {
            int longitudDesdeI = calcularLongitud(i);
            
            // Actualizamos el mejor global almacenado en la clase
            if (longitudDesdeI > mejorLongitudGlobal) {
                mejorLongitudGlobal = longitudDesdeI;
                inicioMejorCamino = i;
            }
        }
        // YA NO LLAMAMOS A IMPRIMIR AQUÍ
    }

    private int calcularLongitud(int u) {
        if (memo[u] != -1) return memo[u];

        int maxLongitud = 1;
        int mejorSiguiente = -1;

        for (int v = 0; v < k; v++) {
            if (u != v && esCompatible(contenedores.get(u), contenedores.get(v))) {
                int longitudV = calcularLongitud(v);
                if (1 + longitudV > maxLongitud) {
                    maxLongitud = 1 + longitudV;
                    mejorSiguiente = v;
                }
            }
        }

        memo[u] = maxLongitud;
        siguiente[u] = mejorSiguiente;
        return maxLongitud;
    }

    private boolean esCompatible(Contenedor c1, Contenedor c2) {
        ArrayList<Integer> a1 = new ArrayList<>(c1.getAtributos());
        ArrayList<Integer> a2 = new ArrayList<>(c2.getAtributos());
        Collections.sort(a1);
        Collections.sort(a2);

        for (int i = 0; i < n; i++) {
            if (a1.get(i) >= a2.get(i)) return false;
        }
        return true;
    }

    /**
     * Método PÚBLICO para imprimir cuando el Main lo decida.
     * Usa las variables guardadas en resolver().
     */
    public void imprimirSolucion() {
        System.out.println(mejorLongitudGlobal); 
        
        if (inicioMejorCamino != -1) {
            int actual = inicioMejorCamino;
            while (actual != -1) {
                System.out.print(contenedores.get(actual).getIndice() + " ");
                actual = siguiente[actual];
            }
        }
        System.out.println();
    }
}