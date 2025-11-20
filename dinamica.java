import java.util.ArrayList;
import java.util.Collections;

public class dinamica {

    private int k;
    private int n;
    private ArrayList<Contenedor> contenedores;

    // Tablas 
    private int[] memoria; // memo[i] guarda la longitud del camino más largo que EMPIEZA en el contenedor i
    private int[] siguiente; // Para reconstruir el camino: siguiente[i] guarda el índice del siguiente contenedor en la cadena

    private int mejorLongitudGlobal;
    private int inicioMejorCamino;


    /**
     * Constructor
     */
    public dinamica(int k, int n, ArrayList<Contenedor> contenedores) {
        this.k = k;
        this.n = n;
        this.contenedores = contenedores;
        
        // Inicializamos tablas con -1 (indicando que no se ha calculado)
        this.memoria = new int[k];
        this.siguiente = new int[k];
        for(int i=0; i<k; i++) {
            memoria[i] = -1; 
            siguiente[i] = -1;
        }
        this.mejorLongitudGlobal = 0;
        this.inicioMejorCamino = -1;

    }

    /**s
     * Método principal que EMPIEZA el proceso.
     */
    public void empezar() {

        // Calcula la longitud máxima empezando desde cada nodo y guarda la mejor 
        // Esto no repite cálculos.
        for (int i = 0; i < k; i++) {
            int longitudDesdeI = calcularLongitud(i);
            
            if (longitudDesdeI > mejorLongitudGlobal) {
                mejorLongitudGlobal = longitudDesdeI;
                inicioMejorCamino = i;
            }
        }
        
        // imprimirSolucion();
    }

    /**
     * Calcula la longitud desde un nodo recibido como parametro
     * Retorna la longitud del camino más largo que comienza en 'inicio'.
     */
    private int calcularLongitud(int inicio) {
        // Si ya está calculado, devolver el valor guardado 
        if (memoria[inicio] != -1) {
            return memoria[inicio];
        }

        // Calcular longitud base es 1 (el propio contenedor)
        int maxLongitud = 1;
        int mejorSiguiente = -1;

        // Prueba extender a todos los posibles siguientes
        for (int v = 0; v < k; v++) {
            // Si u es compatible con v...
            if (inicio != v && esCompatible(contenedores.get(inicio), contenedores.get(v))) {
                // Llamada recursiva
                int longitudV = calcularLongitud(v);
                
                // Si ir a "v" mejora la longitud actual, actualiza
                if (1 + longitudV > maxLongitud) {
                    maxLongitud = 1 + longitudV;
                    mejorSiguiente = v;
                }
            }
        }

        // Guardar en la tabla
        memoria[inicio] = maxLongitud;
        siguiente[inicio] = mejorSiguiente; // Guarda el puntero para reconstruir luego

        return maxLongitud;
    }

    /**
     * Misma función de compatibilidad (adaptada a ArrayList)
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
            if (a1.get(i) >= a2.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Imprime la salida
     */
    public void imprimirSolucion() {
        System.out.println(mejorLongitudGlobal); 
        // Se recorre la lista directamente
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