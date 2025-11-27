import java.util.ArrayList;
import java.util.Collections;

public class dinamica {

    private int k;      // Variables dadas en el main
    private int n;      // Variables dadas en el main
    private ArrayList<Contenedor> contenedores;

    private int[] memo;         // Tablas
    private int[] siguiente;    // Tablas
    
    private int mejorLongitudGlobal;        // Para guardar el resultado del cálculo y poder imprimirlo luego
    private int inicioMejorCamino;          // Para guardar el resultado del cálculo y poder imprimirlo luego

    /**
     * Constructor 
     * @param k
     * @param n
     * @param contenedores
     */
    public dinamica(int k, int n, ArrayList<Contenedor> contenedores) {
        this.k = k;
        this.n = n;
        this.contenedores = contenedores;
        
        this.memo = new int[k];
        this.siguiente = new int[k];

        // Inicializa a -1
        for(int i=0; i<k; i++) {
            memo[i] = -1; 
            siguiente[i] = -1;
        }
        
        this.mejorLongitudGlobal = 0;
        this.inicioMejorCamino = -1;
    }

    /**
     * Método principal que EMPIEZA el proceso.
     */
    public void empezar() {
        // Reinicia variables por si se llama varias veces
        mejorLongitudGlobal = 0;
        inicioMejorCamino = -1;

<<<<<<< HEAD
        // DP[i] almacena longitud de la cadena mas larga que termina en contenedor i
        int[] dp = new int[n];
        
        // PADRES[i] almacena el indice anterior del contenedor i en la cadena mas larga
        int[] padres = new int[n];
        // Inicializacion de DP y PADRES
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            padres[i] = -1; // -1 significa que no tiene padre (es el inicio de cadena)
        }
        return new ArrayList<Contenedor>();



=======
        for (int i = 0; i < k; i++) {
            int longitudDesdeI = calcularLongitud(i);
            
            // Actualiza el MEJOR global almacenado en la clase
            if (longitudDesdeI > mejorLongitudGlobal) {
                mejorLongitudGlobal = longitudDesdeI;
                inicioMejorCamino = i;
            }
        }
>>>>>>> 35e598a0ff0bb6e2b1291e657cb6e515624ef75f
    }

    /**
     * Calcula la longitud de la cadena actual desde u
     * @param u
     * @return longitud máxima desde u
     */
    private int calcularLongitud(int u) {
        if (memo[u] != -1) return memo[u];

        int maxLongitud = 1;
        int mejorSiguiente = -1;

        for (int i = 0; i < k; i++) {
            // Si los nodos son diferentes y ambos contenedores SI son compatibles (cabe uno dentro del otro)
            if (u != i && esCompatible(contenedores.get(u), contenedores.get(i))) {
                // Se sigue calculando la longitud
                int longitudV = calcularLongitud(i);
                // Y se actualiza la longitud máxima
                if (1 + longitudV > maxLongitud) {
                    maxLongitud = 1 + longitudV;
                    mejorSiguiente = i;
                }
            }
        }
        // Y se almacenan de nuevo los datos en las tablas
        memo[u] = maxLongitud;
        siguiente[u] = mejorSiguiente;
        return maxLongitud;
    }

    /**
     * Comprueba si c1 puede contener a c2
     * @param c1
     * @param c2
     * @return booleano Indica si c1 puede contener a c2
     */
    private boolean esCompatible(Contenedor c1, Contenedor c2) {
        // Un contenedor NO es compatible consigo mismo!!!!
        if (c1.getIndice() == c2.getIndice()) {
            return false;
        }
        // Crea nuevas listas copiando los datos originales para no desordenar los atributos originales del contenedor
        ArrayList<Integer> a1 = new ArrayList<>(c1.getAtributos());
        ArrayList<Integer> a2 = new ArrayList<>(c2.getAtributos());
        
        // ORDENAR con Collections.sort() DE MOMENTO, TAL VEZ TEMPORAL
        Collections.sort(a1);
        Collections.sort(a2);

        // Se compara para ver si cabe dentro (que a1 sea mas grande en todos los atributos que a2)
        for (int i = 0; i < n; i++) {
            if (a1.get(i) >= a2.get(i)) return false;
        }
        return true;
    }

    /**
     * Imprime la solución óptima calculada
     */
    public void imprimirSolucion() {
        System.out.println(mejorLongitudGlobal); 
        
        // Reconstruir el camino desde inicioMejorCamino
        if (inicioMejorCamino != -1) {
            int actual = inicioMejorCamino;
            // Imprime los índices de los contenedores en la solución
            while (actual != -1) {
                System.out.print(contenedores.get(actual).getIndice() + " ");
                actual = siguiente[actual];
            }
        }
        System.out.println();
    }
}