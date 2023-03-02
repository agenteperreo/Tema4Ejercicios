package rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Descifrar {

    public static void main(String[] args) {
        //Arrays de bytes que almacenará el mensaje descifrado
        byte[] descifradoPrivada;
        byte[] descifradoPublica;
        try {
            //Almacenamos la clave publica del emisor en una variable a partir de una funcion en ClavesEmisor
            PublicKey publicaEmisor = ClavesEmisor.getClavePublica();

            //Desencriptamos con RSA
            Cipher cifrarPublica = Cipher.getInstance("RSA");

            //Empezamos a desencriptar el mensaje con la clave publica del emisor
            cifrarPublica.init(Cipher.DECRYPT_MODE, publicaEmisor);

            //Alamcenamos la clave privada del receptor en una variable a partir de una funcion en ClavesReceptor
            PrivateKey cifrarPrivada = ClavesReceptor.getClavePrivada();

            //Desencriptamos con RSA
            Cipher cifradorReceptor = Cipher.getInstance("RSA");

            //Empezamos a desencriptar con la clave privada del receptor
            cifradorReceptor.init(Cipher.DECRYPT_MODE, cifrarPrivada);

            //Guardamos el mensaje descecriptado por la clave del receptor
            descifradoPrivada = cifradorReceptor.doFinal(leerFichero().readAllBytes());

            //Guardamos el mensaje desencriptado por la clave del emisor
            descifradoPublica = cifrarPublica.doFinal(descifradoPrivada);

            //Mostramos el mensaje
            System.out.println("Este es el mensaje: ");
            System.out.println(new String(descifradoPublica, StandardCharsets.UTF_8));

            //Control de excepciones
        } catch (NoSuchPaddingException e) {
            System.err.println("ERROR: No existe el padding seleccionado");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: Algoritmo seleccionado no existente");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("ERROR: Tamaño del bloque ilegal");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("ERROR: El padding utilizado es erróneo");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("ERROR: Clave no valida");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR: Algo sucedió en la lectura del fichero");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("ERROR: Algo no ha salido como debía");
            e.printStackTrace();
        }
    }


    /**
     * Metodo que lee la ruta del fichero con el mensaje encriptado
     *
     * @return InputStream del fichero que almacena el mensaje encriptado
     */
    private static FileInputStream leerFichero() {
        //Abrimos el escaner
        Scanner sc = new Scanner(System.in);

        //Pedimos la ruta al usuario
        System.out.println("Introduce la ruta del fichero a descifrar: ");
        String ruta = sc.nextLine();

        //Inicializamos el fileInputStream
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(ruta);
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Fichero no encontrado");
            e.printStackTrace();
        }
        return fileInputStream;
    }

}
