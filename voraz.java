
/**
 * @author ichhito
 * Implementacion del algoritmo voraz para la práctica 2
 */
import java.util.ArrayList;
import java.util.Collections;

public class Voraz {
    
    public ArrayList<Contenedor> algoritmo(ArrayList<ArrayList<Integer>> listaEntrada){
        ArrayList<Contenedor> contenedores = new ArrayList<>();

        int indice = 1; 
        //Para cada contenedor, ordenamos sus atributos y lo almacenamos
        //Menos lio posteriormente para sacar si es concatenable
        for (ArrayList<Integer> datos : listaEntrada) {
            Collections.sort(datos);
            contenedores.add(new Contenedor(indice, datos));
            indice++;   
        }

        Collections.sort(contenedores);
        ArrayList<Contenedor> mejorSolucion = new ArrayList<>();
        for(int i = 0; i<contenedores.size(); i++){
            ArrayList<Contenedor> intentoActual = new ArrayList<>();
            Contenedor c = contenedores.get(i);
            intentoActual.add(c);
            for(int j = i+1; j < contenedores.size(); j++){
                Contenedor candidato = contenedores.get(j);
                boolean concatenable = true;
                for(int k = 0; k < c.atributos.size(); k++){
                    int valorMenor = c.atributos.get(k);
                    int valorMayor = candidato.atributos.get(k);
                    if(valorMenor >= valorMayor){
                        concatenable = false;
                        break;
                    }
                }
                if(concatenable){
                    intentoActual.add(candidato);
                    c = candidato;
                }
            }
            if(intentoActual.size() > mejorSolucion.size()){
                mejorSolucion = intentoActual;
            }
            
        }
        

        return mejorSolucion;
    }
}


