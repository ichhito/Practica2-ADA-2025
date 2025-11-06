
/**
 * @author ichhito
 * Implementacion del algoritmo voraz para la práctica 2
 */
import java.util.ArrayList;
import java.util.Collections;

public class voraz {
    
    public String algoritmo(ArrayList<ArrayList<Integer>> listaEntrada){
        ArrayList<Contenedor> contenedores = new ArrayList<>();

        int indice = 1; 
        for (ArrayList<Integer> datos : listaEntrada) {
            contenedores.add(new Contenedor(indice, datos));
            indice++;
        }

        Collections.sort(contenedores);

        //Primer contenedor itera hasta encontrar el mejor concatenable
        for(Contenedor c : contenedores){
            //1. Buscar contenedores compatibles
            //2. Buscar el mas ajustado (valores mas cercanos pero mas grandes)
            //3. Guardar el mas "ajustado"
        }

        return ""; //Temporal
    }
}


/**
 * Clase para almacenar la suma total del contenedor y el contenedor
 * Supuestamente la forma mas óptima
 */
class Contenedor implements Comparable<Contenedor> {
    int idOriginal;         
    ArrayList<Integer> atributos;
    long suma;              

    public Contenedor(int id, ArrayList<Integer> datos) {
        this.idOriginal = id;
        this.atributos = new ArrayList<>(datos);
        
        this.suma = 0;
        for (int val : this.atributos) {
            this.suma += val;
        }
        
      
        Collections.sort(this.atributos);
    }

    @Override
    public int compareTo(Contenedor otro) {
        return Long.compare(this.suma, otro.suma);
    }
}