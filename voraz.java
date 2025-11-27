
/**
 * @author ichhito
 * Implementacion del algoritmo voraz para la práctica 2
 */
import java.util.ArrayList;
import java.util.Collections;

public class voraz {
    /**
     * Método principal del algoritmo voraz
     * @param contenedores Lista de contenedores
     * @return Mejor solución encontrada
     */
    public ArrayList<Contenedor> algoritmo(ArrayList<Contenedor> contenedores){
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
                    int valorMenor = c.getAtributos().get(k);
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

    /**
     * Imprime la solución en la salida estándar
     * @param solucion Mejor solución encontrada
     */
        public static void imprimirSalidaEstandar(ArrayList<Contenedor> solucion) {
        System.out.println(solucion.size());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < solucion.size(); i++) {
            sb.append(solucion.get(i).getIndice());
            if (i < solucion.size() - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}


