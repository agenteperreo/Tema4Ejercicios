package Ejercicio1;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Clase que registrará al usuario con su contraseña en el fichero credenciales.cre
 */
public class Registrarse {

    /**
     * Main en el que guardamos el usuario y la contraseña encriptada en el fichero credenciales.cre.
     */
    public static void main(String[] args) {

        // Preparo el Scanner para leer por teclado
        Scanner sc = new Scanner(System.in);

        // Pedimos el nombre de usuario
        System.out.println("Registro de usuario");
        System.out.println("Introduce el nombre de usuario:");
        String nombre = sc.nextLine();

        // Pedimos la contraseña del usuario introducido
        System.out.println("Introduce la contraseña:");
        String password = sc.nextLine();

        // Guardamos el nombre de usuario y el resumen de la contraseña en el fichero
        guardarCredenciales(nombre, password);

        // Mostramos mensaje de confirmación
        System.out.println("Usuario registrado correctamente");

    }

    /**
     * Guarda en el fichero credenciales.cre el nombre de usuario y el hash de la contraseña.
     *
     * @param nombre   String Nombre de usuario
     * @param password String Contraseña
     */
    private static void guardarCredenciales(String nombre, String password) {

        // Obtenemos el resumen de la contraseña
        byte[] resumen = Codificador.getDigest(password);

        // Convertimos el resumen a hexadecimal
        String contraseñaEncriptada = String.format("%064x", new BigInteger(1, resumen));

        try {
            // Abrimos el buffered para escribir
            BufferedWriter bw = new BufferedWriter(new FileWriter("Tema4Ejercicios/src/Ejercicio1/credenciales.cre", true));

            // Escribo el nombre de usuario y la contraseña encriptada separados por el ; para poder leerlo despues mas facilmente
            bw.write(nombre + ";" + contraseñaEncriptada);

            // Salto de línea
            bw.newLine();

            // Cierro el fichero
            bw.close();

            // Control de excepciones
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Fallo interno");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al registrar el usuario");
            e.printStackTrace();
        }
    }
}