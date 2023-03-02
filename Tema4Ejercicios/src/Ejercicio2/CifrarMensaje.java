package Ejercicio2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CifrarMensaje {
    /**
     * Método principal que ejecuta el programa y hace las preguntas al usuario.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el texto a cifrar");
        String texto = sc.nextLine();
        System.out.println("Introduce la clave de cifrado. Debe tener 16 caracteres");
        String clave = sc.nextLine();
        try {
            guardarTextCifrado(texto, clave);
            System.out.println("Texto cifrado y guardado con éxito");
        } catch (Exception e) {
            System.err.println("Clave no válida");
            e.printStackTrace();
        }
        sc.close();
    }
    /**
     * Precondiciones: Debe recibir un texto y una clave.
     * Método que recibe un texto y una clave y lo cifra y lo guarda en un archivo de texto.
     * Postcondiciones: No tiene.
     * @param texto Texto a cifrar
     * @param clave Clave de cifrado
     */
    public static void guardarTextCifrado(String texto, String clave){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Ejercicio2/textoCifrado.txt"));
            bw.write(MaquinaEnigma.cifrar(texto, MaquinaEnigma.obtenerClave(clave)));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.err.println("No se ha podido guardar el texto");
            e.printStackTrace();
        }
    }
}