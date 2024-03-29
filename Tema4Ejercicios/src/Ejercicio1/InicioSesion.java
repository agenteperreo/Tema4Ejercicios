package Ejercicio1;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Clase en la que pedimos el nombre de usuario y contraseña a un usuario y
 * comprobamos que ambos valores son equivalentes a alguno que esta en el fichero
 *
 * @author igarcia
 */
public class InicioSesion {

    /**
     * Main que pide usuario y contraseña y los valida a partir de las funciones
     */
    public static void main(String[] args) {

        //Abrimos el escaner
        Scanner sc = new Scanner(System.in);

        // Validamos el usuario y la contraseña
        System.out.println("Introduce tu nombre de usuario:");
        String nombre = sc.nextLine();

        System.out.println("Introduce la contraseña:");
        String password = sc.nextLine();


        if (validarUsuarioHash(nombre, password)) {
            System.out.println("Acceso permitido");
        } else {
            System.err.println("Acceso denegado, usuario o contraseña incorrectos");
        }
    }

    /**
     * Comprobamos si el usuario y la contraseña son correctos comparandolos con el fichero credenciales.cre
     *
     * @param nombre     String Nombre de usuario
     * @param contraseña String Contraseña
     * @return boolean validado
     */
    private static boolean validarUsuarioHash(String nombre, String contraseña) {
        //Línea del fichero
        String linea;

        //Booleano que indica si el usuario y la contraseña son correctos
        boolean validado = false;

        // Creamos un array para guardar el split
        String[] arrayDividido;

        try {
            //Paso la contraseña a resumen con el método getDigest de la clase Coder
            byte[] resumen = Codificador.getDigest(contraseña);

            //Paso el resumen a hexadecimal para poder compararlo con el resumen (hash) del fichero
            String passEncript = String.format("%064x", new BigInteger(1, resumen));

            //Creo un BufferedReader para leer el fichero
            BufferedReader br = new BufferedReader(new FileReader("Tema4Ejercicios/src/Ejercicio1/credenciales.cre"));

            //Leo la primera línea
            linea = br.readLine();

            //Mientras no sea null, leo el fichero
            while (linea != null) {

                // Inicializamos el array con el split de linea
                arrayDividido = linea.split(";");

                //Si el nombre de usuario coincide con el nombre de usuario de la línea
                if (arrayDividido[0].equals(nombre)) {
                    //Saco el resumen (hash) de la línea
                    String pass = arrayDividido[1];

                    //Si el resumen (hash) de la línea coincide con el resumen (hash) de la contraseña
                    if (passEncript.equals(pass)) {
                        //Validado pasa a true
                        validado = true;
                        //Salgo del bucle para que no tenga que recorrer el fichero completo si ya ha encontrado el usuario y la contraseña
                        break;
                    }
                }
                //Leo la siguiente línea
                linea = br.readLine();

            }
            // Control de excepciones
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error de lectura");
            e.printStackTrace();
        }

        //Devuelvo si se ha validado o no
        return validado;
    }
}