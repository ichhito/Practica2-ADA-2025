import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Clase principal que maneja la entrada de datos y las llamadas a los
 * algoritmos.
 */
public class mainAlgoritmos {

    // Nombre del archivo de entrada
    public static String entrada = "";

    // Lista para almacenar todos los contenedores leídos
    private static ArrayList<Contenedor> contenedores;
    private static int k; // Cantidad de contenedores
    private static int n; // Número de atributos
    private static int reps = 10; // Número de atributos


    public static void main(String[] args) throws IOException {
        // Pedir nombre del fichero de la entrada por teclado al usuario
        System.out.println("Escriba el nombre del fichero de entrada: ");
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.next();
        teclado.close();

        // Carga los datos y verifica el éxito
        try {
            Scanner lector = new Scanner(new File(entrada));
            // Leer K y N (Primera línea)
            k = lector.nextInt(); // Cantidad de contenedores (k)
            n = lector.nextInt(); // Número de atributos(n)

            contenedores = new ArrayList<>(k);

            // Leer los K Contenedores (K líneas siguientes)
            for (int i = 0; i < k; i++) {
                ArrayList<Integer> atributos = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if (!lector.hasNextInt()) {
                        // i+2 porque empieza en la linea 0(+1) y porque la primera linea es la de k y n
                        // (+1)
                        System.err.println("Error: Faltan atributos en la línea " + (i + 2));
                        return;
                    }
                    // Se añaden al final de la lista los nuevos atributos leidos
                    atributos.add(lector.nextInt());
                }
                // Crear y añadir el objeto Contenedor. El índice comienza en 1.
                contenedores.add(new Contenedor(i + 1, atributos));
                /*PRINTS PARA HACER PRUEBAS CON ATRIBUTOS SOBRE LA LECTURA DEL FICHERO
                System.out.print(i + 1 + " - atributos: ");
                for (int a : atributos) {
                    System.out.print(a + " ");
                }
                System.out.println();
                */
            }

            lector.close();


        } catch (FileNotFoundException e) {
            throw new IOException("Fichero no encontrado", e);

        } catch (Exception e) {
            throw new IOException("No se pudo procesar el archivo", e);
        }

        System.out.println(k + " contenedores, " + n + " atributos por contenedor.\n");
            // Y AQUI YA LAS LLAMADAS A LOS METODOS DE CADA ALGORTIMO CON N Y CON K
            // Y LOS ATRIBUTOS ESTAN EN LA LISTA atributos.

        long totalTiempo = 0L;
        System.out.println("--- BACKTRAKING ---");


        for(int i=0; i<reps; i++){
            System.gc();        //para medir el tiempo
            Instant start = Instant.now();

            backtracking bt = new backtracking(k, n, contenedores);
            bt.empezar();

            Instant finish = Instant.now();
            totalTiempo += Duration.between(start, finish).toNanos();

            if (i == reps-1){
                bt.imprimirSolucion(); 
            }
        }
        System.out.println("El tiempo promedio para BACKTRAKING es: " + (totalTiempo/reps) + "ns");




            long totalTiempoVoraz = 0L;
            System.out.println("--- VORAZ ---");
        
            voraz algoritmoVoraz = new voraz(); 
            ArrayList<Contenedor> solucionVoraz = new ArrayList<>();

            for(int i = 0; i < reps; i++){
            //Creamos copia para que no tenga datos desordenados
            ArrayList<Contenedor> copiaDatos = new ArrayList<>(contenedores);

            System.gc();      
            Instant start = Instant.now();

            solucionVoraz = algoritmoVoraz.algoritmo(copiaDatos);

            Instant finish = Instant.now();
            totalTiempoVoraz += Duration.between(start, finish).toNanos();

            // Imprimir solo al final para
            if (i == reps - 1){
                voraz.imprimirSalidaEstandar(solucionVoraz);
            }
        }
        System.out.println("El tiempo promedio para VORAZ es: " + (totalTiempoVoraz/reps) + " ns\n");
    }



}

