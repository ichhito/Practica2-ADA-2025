import java.util.ArrayList;
import java.util.Collections; 

public class dinamica {

    private int k;
    private int n;
    private ArrayList<Contenedor> contenedores; 

    /**
     * Constructor
     */
    public dinamica(int k, int n, ArrayList<Contenedor> contenedores) {
        this.k = k; // Numero de contenedores
        this.n = n; // Numero de atributos
        this.contenedores = contenedores;   // Lista de contenedores
    }

    /**
     * Metodo para lanzar el algoritmo
     */
    public ArrayList<Contenedor> empezar() {
        Collections.sort(contenedores);
        int n = contenedores.size();
        if (n == 0) return new ArrayList<>();

        // DP[i] almacena longitud de la cadena mas larga que termina en contenedor i
        int[] dp = new int[n];
        
        // PADRES[i] almacena el indice anterior del contenedor i en la cadena mas larga
        int[] padres = new int[n];
        return new ArrayList<Contenedor>();
    }




}