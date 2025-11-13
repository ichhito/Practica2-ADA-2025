
/**
 * @author ichhito
 * Implementacion del algoritmo voraz para la práctica 2
 */
import java.util.ArrayList;
import java.util.Collections;

public class Voraz {
    
    public String algoritmo(ArrayList<ArrayList<Integer>> listaEntrada){
        ArrayList<ContenedorVoraz> ContenedorVorazes = new ArrayList<>();

        int indice = 1; 
        //Para cada ContenedorVoraz, ordenamos sus atributos y lo almacenamos
        //Menos lio posteriormente para sacar si es concatenable
        for (ArrayList<Integer> datos : listaEntrada) {
            Collections.sort(datos);
            ContenedorVorazes.add(new ContenedorVoraz(indice, datos));
            indice++;   
        }

        Collections.sort(ContenedorVorazes);
        ArrayList<ContenedorVoraz> mejorSolucion = new ArrayList<>();
        for(int i = 0; i<ContenedorVorazes.size(); i++){
            ArrayList<ContenedorVoraz> intentoActual = new ArrayList<>();
            ContenedorVoraz c = ContenedorVorazes.get(i);
            intentoActual.add(c);
            for(int j = i+1; j < ContenedorVorazes.size(); j++){
                ContenedorVoraz candidato = ContenedorVorazes.get(j);
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
        

        return ""; //Temporal
    }
}


/**
 * Clase para almacenar la suma total del ContenedorVoraz y el ContenedorVoraz
 * Supuestamente la forma mas óptima
 */
class ContenedorVoraz implements Comparable<ContenedorVoraz> {
    int idOriginal;         
    ArrayList<Integer> atributos;
    long suma;              

    public ContenedorVoraz(int id, ArrayList<Integer> datos) {
        this.idOriginal = id;
        this.atributos = new ArrayList<>(datos);
        
        this.suma = 0;
        for (int val : this.atributos) {
            this.suma += val;
        }
        
      
        Collections.sort(this.atributos);
    }

    @Override
    public int compareTo(ContenedorVoraz otro) {
        return Long.compare(this.suma, otro.suma);
    }
}