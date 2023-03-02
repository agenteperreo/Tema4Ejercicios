package Ejercicio2;

import java.io.*;
import java.util.Scanner;

public class DescifrarMensaje {
    /**
     * Método principal que ejecuta el programa y hace las preguntas al usuario.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la clave de descifrado. Debe tener 16 caracteres");
        String clave = sc.nextLine();
        try {
            System.out.println("\033[93;1m"+leerTextCifrado(clave)+"\033[0m");
        } catch (Exception e) {
            System.err.println("Clave incorrecta");
            e.printStackTrace();
        }
        sc.close();
    }
    /**
     * Precondiciones: Debe recibir una clave para descifrar el texto.
     * Método que recibe una clave y descifra el texto cifrado que hay en un archivo de texto y lo muestra por pantalla.
     * Postcondiciones: Devuelve el texto descifrado en un String.
     * @param clave Clave de descifrado
     * @return Texto descifrado String
     */
    public static String leerTextCifrado(String clave){
        String textoCifrado = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Ejercicio2/textoCifrado.txt"));
            textoCifrado = br.readLine();
            br.close();
        } catch (IOException e) {
            System.err.println("No se ha podido recuperar el texto");
            e.printStackTrace();
        }
        return MaquinaEnigma.descifrar(textoCifrado, MaquinaEnigma.obtenerClave(clave));
    }
}