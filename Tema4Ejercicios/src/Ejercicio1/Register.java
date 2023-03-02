package Ejercicio1;
/*
Una clase que contenga un main que realice el registro del usuario.
Se encargará de pedir por teclado el usuario y la contraseña y almacenará en fichero el usuario y el resumen de la contraseña.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Register {
    /**
     * Precondiciones: No tiene.
     * Main que se encarga de registrar un usuario y su contraseña en el fichero credenciales.cre.
     * Postcondiciones: Registra un usuario y su contraseña en el fichero credenciales.cre.
     */
    public static void main(String[] args) {
        //Preparo el Scanner para leer por teclado
        Scanner sc = new Scanner(System.in);
        //Muestro mensaje de registro
        System.out.println("Registro de usuario");
        System.out.println("Introduce el nombre de usuario:");
        //Leo el nombre de usuario
        String nombre = sc.nextLine();
        System.out.println("Introduce la contraseña:");
        //Leo la contraseña
        String password = sc.nextLine();
        System.out.println("Introduce la contraseña de nuevo:");
        //Leo la contraseña de nuevo
        String password2 = sc.nextLine();
        //Comprobamos que las contraseñas coinciden
        if (password.equals(password2)) {
            //Guardamos el nombre de usuario y el resumen de la contraseña en el fichero
            guardarCredenciales(nombre, password);
            //Mostramos mensaje de confirmación
            System.out.println("Usuario registrado correctamente");
        } else {
            System.out.println("Las contraseñas no coinciden");
        }

    }
    /**
     * Precondiciones: Recibe un nombre de usuario y un resumen de contraseña.
     * Guarda en el fichero credenciales.cre el nombre de usuario y el resumen de la contraseña.
     * Postcondiciones: Guarda en el fichero credenciales.cre el nombre de usuario y el resumen de la contraseña.
     * @param nombre String Nombre de usuario
     * @param password String Resumen de la contraseña
     */
    private static void guardarCredenciales(String nombre, String password) {
        //Obtenemos el resumen de la contraseña
        byte[] resumen = Coder.getDigest(password);
        //Convertimos el resumen a hexadecimal
        String passwordHash = String.format("%064x", new BigInteger(1, resumen));
        try {
            //Preparo el fichero para escribir
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Ejercicio1/credenciales.cre", true));
            //Escribo el nombre de usuario, el resumen de la contraseña y el resumen de la contraseña en hexadecimal separados por ";" para poder leerlos después
            bw.write(nombre + ";" + Arrays.toString(resumen) + ";" + passwordHash);
            //Salto de línea
            bw.newLine();
            //Cierro el fichero
            bw.close();
        } catch (IOException e) {
            System.err.println("Error al registrar el usuario");
            e.printStackTrace();
        }
    }
}