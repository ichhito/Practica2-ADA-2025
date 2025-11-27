/**
 * @author diegmar
 * Implementacion del algoritmo de backtracking para la práctica 2
 */
import java.util.ArrayList;
import java.util.Collections; 

public class backtracking {

    private int k; // Número de contenedores
    private int n; // Número de atributos
    private ArrayList<Contenedor> contenedores; // Lista de contenedores

    private ArrayList<Integer> mejorCaminoGlobal;
    private int mejorLongitud;

    /**
     * Constructor
     * @param k
     * @param n
     * @param contenedores
     */
    public backtracking(int k, int n, ArrayList<Contenedor> contenedores) {
        this.k = k; // Número de contenedores
        this.n = n; // Número de atributos
        this.contenedores = contenedores;   // Lista de contenedores

        // Inicializamos 
        this.mejorCaminoGlobal = new ArrayList<>();
        this.mejorLongitud = 0;
    }

    /**
     * Método principal que EMPIEZA el proceso.
     */
    public void empezar() {
        // Probar a iniciar una cadena desde CADA contenedor
        for (int i = 0; i < k; i++) {
            // Cadena de booleanos para marcar qué contenedores ya están dentro de la cadena actual y evitar bucles infinitos (como solo se cogen los que caben, no hace falta marcar los demas)
            boolean[] visitados = new boolean[k];
            ArrayList<Integer> caminoActual = new ArrayList<>();
            buscarCamino(i, caminoActual, visitados);
        }

        // imprimirSolucion(); // Imprimir salida
    }

    /**
     * Búsqueda recursiva del mejor camino desde el contenedor actual.
     * @param contenedorActual
     * @param caminoActual
     * @param visitados
     */
    private void buscarCamino(int contenedorActual, ArrayList<Integer> caminoActual, boolean[] visitados) {
        // Guardar datos para la recursividad
        visitados[contenedorActual] = true;   // Pone en contenedor actual "true" porque está en la cadena y evitar bucles infinitos
        caminoActual.add(contenedorActual);   // Y se añade el contenedor actual a la cadena que se esta comprobando

        // Guardar mejor solución hasta el mommento con mejorLongitud y mejorCaminoGlobal
        if (caminoActual.size() > mejorLongitud) {
            mejorLongitud = caminoActual.size();
            mejorCaminoGlobal = new ArrayList<>(caminoActual);
        }

        // Comprobar candidatos
        for (int j = 0; j < k; j++) {
            // Llamamos a la función esCompatible EN CADA PASO para comprobar si es podible continuar del contenedor actual al contenedor j
            // Siempre y cuando ese contenedor no esté en la cadena!!
            if (!visitados[j] && esCompatible(contenedores.get(contenedorActual), contenedores.get(j))) {
                // Y si es compatible, entonces seguimos buscando por donde sigue el mejor camino
                buscarCamino(j, caminoActual, visitados);
            }
        }

        // Volver hacia atras quitando y borrando las cosas
        visitados[contenedorActual] = false;
        caminoActual.remove(caminoActual.size() - 1);
    }

    /**
     * Comprueba si c1 puede contener a c2
     * @param c1
     * @param c2
     * @return
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
     * Imprime la salida obtenida
     */
    public void imprimirSolucion() {
        System.out.println(mejorLongitud); 
        // Se recorre la lista directamente
        for (int indice : mejorCaminoGlobal) {
            // Buscamos el ID original usando el índice
            int idOriginal = contenedores.get(indice).getIndice();
            // Y se imprime el indice original del contenedor
            System.out.print(idOriginal + " ");
        }
        System.out.println();
    }
}