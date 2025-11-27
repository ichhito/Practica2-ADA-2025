import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Hecho con IA porque no se va a evaluar
 */
public class GeneradorCasos {

    public static void main(String[] args) {
        // --- CONFIGURACIÓN DE LA PRUEBA ---
        int k = 1000;      // Número de contenedores (máx 1000 según pdf)
        int n = 50;        // Número de atributos (máx 100 según pdf)
        int maxValor = 100; // Valor máximo para un atributo
        String nombreFichero = "entrada_generada.txt";
        
        // Llamamos al método para generar
        generarFichero(k, n, maxValor, nombreFichero);
    }

    /**
     * Genera un caso de prueba y lo guarda en un fichero.
     * Usa StringBuilder para construir el contenido eficientemente.
     */
    public static void generarFichero(int k, int n, int maxValor, String nombre) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        // 1. Primera línea: K y N
        sb.append(k).append(" ").append(n).append("\n");

        // 2. Generar K líneas de contenedores
        for (int i = 0; i < k; i++) {
            // Generar N atributos para este contenedor
            for (int j = 0; j < n; j++) {
                // Generamos número entre 1 y maxValor
                int atributo = rand.nextInt(maxValor) + 1; 
                sb.append(atributo);
                
                // Añadir espacio si no es el último atributo
                if (j < n - 1) {
                    sb.append(" ");
                }
            }
            // Salto de línea al terminar el contenedor
            sb.append("\n");
        }

        // 3. Escribir el contenido del StringBuilder al fichero
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombre))) {
            writer.print(sb.toString());
            System.out.println("Fichero '" + nombre + "' generado con éxito.");
            System.out.println("Contenedores: " + k + ", Atributos: " + n);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}