import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase auxiliar para representar un Contenedor.
 * Incluye el índice original y los atributos ordenados (normalizados).
 */
class Contenedor {
    private int indice; // Índice original, comenzando desde 1 (requisito de salida)
    private int[] atributos;

    public Contenedor(int indice, int[] atributos) {
        this.indice = indice;
        // Creamos una copia para evitar modificar el array original
        this.atributos = atributos;
    }

    public int getIndice() {
        return indice;
    }

    public int[] getAtributos() {
        return atributos;
    }

    public int getDimension() {
        return atributos.length;
    }
}

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
                int[] atributos = new int[n];
                for (int j = 0; j < n; j++) {
                    if (!lector.hasNextInt()) {
                        // i+2 porque empieza en la linea 0(+1) y porque la primera linea es la de k y n
                        // (+1)
                        System.err.println("Error: Faltan atributos en la línea " + (i + 2));
                        return;
                    }
                    atributos[j] = lector.nextInt();
                }
                // Crear y añadir el objeto Contenedor. El índice comienza en 1.
                contenedores.add(new Contenedor(i + 1, atributos));
                System.out.print(i + 1 + " - atributos: ");
                for (int a : atributos) {
                    System.out.print(a + " ");
                }
                System.out.println();
            }

            lector.close();

            // Y AQUI YA LAS LLAMADAS A LOS METODOS DE CADA ALGORTIMO CON N Y CON K
            // Y LOS ATRIBUTOS ESTAN EN LA LISTA atributos.


        } catch (FileNotFoundException e) {
            throw new IOException("Fichero no encontrado", e);

        } catch (Exception e) {
            throw new IOException("No se pudo procesar el archivo", e);
        }

        System.out.println(k + " contenedores, " + n + " atributos por contenedor.");

    }
}