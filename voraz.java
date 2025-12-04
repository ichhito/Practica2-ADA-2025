
/**
 * @author ichhito
 * Implementacion del algoritmo voraz para la práctica 2
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class voraz {
    /**
     * Método principal del algoritmo voraz
     * @param contenedores Lista de contenedores
     * @param k Número de contenedores
     * @param n Número de atributos
     * @return Mejor solución encontrada
     */
    public ArrayList<Contenedor> algoritmo(ArrayList<Contenedor> contenedores, int k, int n){

        //1. Ordenamos la lista
        Collections.sort(contenedores);
        ArrayList<Contenedor> mejorSolucion = new ArrayList<>();

        //2. Iteramos para encontrar el siguiente intento
        for(int i = 0; i<k; i++){
            ArrayList<Contenedor> intentoActual = new ArrayList<>();
            Contenedor c = contenedores.get(i);
            intentoActual.add(c);

            // 3. Buscamos hacia delante el siguiente que encaje (voraz)
            for(int j = i+1; j < k; j++){
                Contenedor candidato = contenedores.get(j);

            if(esCompatible(c,candidato,n)){
                intentoActual.add(candidato);
                c = candidato;
            }
        }
            //4. Si la cadena encontrada en ese intento es mejor, se guarda
            if(intentoActual.size() > mejorSolucion.size()){
                mejorSolucion = intentoActual;
            }
            
        }
        

        return mejorSolucion;
    }



    /**
     * Comprueba si un contenedor "small" cabe en otro "big" 
     * @param small Contenedor que va dentro
     * @param big Contenedor que lo contiene
     * @param n Numero de atributos
     * @return true si small cabe en big, false en caso contrario
     */
    private boolean esCompatible(Contenedor small, Contenedor big, int n){
        List<Integer> atrSmall = small.getAtributos();
        List<Integer> atrBig = big.getAtributos();

        for(int i = 0; i < n; i++){
            if(atrSmall.get(i) >= atrBig.get(i)){
                return false;
            }
        }
        return true;
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

