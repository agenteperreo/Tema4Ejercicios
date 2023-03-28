package Ejercicio2;

import java.io.*;
import java.util.Scanner;

public class DescifrarMensaje {
    /**
     * Método principal que ejecuta el programa y hace las preguntas al usuario.
     */
    public static void main(String[] args) {

        //Abrimos el escaner
        Scanner sc = new Scanner(System.in);

        //Pido la clave de descifrado
        System.out.println("Introduce la clave de descifrado. Debe tener 16 caracteres");
        String clave = sc.nextLine();

        try {
            //Mostramos el mensaje descifrado
            System.out.println("El mensaje descifrado es: "+"\033[93;1m" + leerTextCifrado(clave) + "\033[0m");
        } catch (Exception e) {
            System.err.println("Clave incorrecta");
            e.printStackTrace();
        }
        sc.close();
    }

    /**
     * Método que recibe una clave y descifra el texto cifrado que hay en un archivo de texto y lo muestra por pantalla.
     *
     * @param clave Clave de descifrado
     * @return Texto descifrado String
     */
    public static String leerTextCifrado(String clave) {

        //Creamos la variable que va a contener el texto cifrado y la inicializamos a null
        String textoCifrado = null;

        try {
            //Leemos las lineas del texto cifrado del fichero que almacena el texto cifrado y lo guardamos en la variable creada anteriormente
            BufferedReader br = new BufferedReader(new FileReader("Tema4Ejercicios/src/Ejercicio2/textoCifrado.txt"));
            textoCifrado = br.readLine();
            br.close();

            //Control de excepciones
        } catch (IOException e) {
            System.err.println("No se ha podido recuperar el texto");
            e.printStackTrace();
        }

        //Devolvemos el codigo descifradocon el mtodo descifrar de la clase maquina enigma y como parametros el tesxto cifrado
        // y la clave que obtengo de otro metodo en la clase maquina enigma
        return MaquinaEnigma.descifrar(textoCifrado, MaquinaEnigma.obtenerClave(clave));
    }
}