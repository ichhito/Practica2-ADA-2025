/**
 * @author ichhito
 * Implementacion de la clase Contenedor para la práctica 2
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase para almacenar la suma total del Contenedor y el id original.
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

    public int getIndice() {
        return this.idOriginal;
    }

    public ArrayList<Integer> getAtributos() {
        return this.atributos;
    }

    public int getDimension() {
        return this.atributos.size();
    }
    @Override
    public int compareTo(Contenedor otro) {
        return Long.compare(this.suma, otro.suma);
    }
}