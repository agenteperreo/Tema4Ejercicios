package rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.Scanner;

public class Cifrar {

    public static void main(String[] args) {
        //Array de bytes que almacenará el mensaje encriptado
        byte[] cifradoPrivada;
        byte[] cifradoPublica;
        try {
            //Almacenamos la clave privada del emisor en una variable
            PrivateKey privadaEmisor = ClavesEmisor.getClavePrivada();

            //Ciframos con RSA
            Cipher cifrarPrivada = Cipher.getInstance("RSA");

            //Iniciamos el cifrador de la clave privada
            cifrarPrivada.init(Cipher.ENCRYPT_MODE, privadaEmisor);

            //Almacenamos la clave publica del receptor en una variable
            PublicKey publicaReceptor = ClavesReceptor.getClavePublica();

            //La ciframos con RSA
            Cipher cifrarPublica = Cipher.getInstance("RSA");

            //Iniciamos el cifrador de la clave publica
            cifrarPublica.init(Cipher.ENCRYPT_MODE, publicaReceptor);

            //Guardamos el mensaje cifrado con la clave privada
            cifradoPrivada = cifrarPrivada.doFinal(leerFichero().readAllBytes());

            //Guardamos el mensaje cifrado con la clve privada y ahora cifrada otra vez con la publica
            cifradoPublica = cifrarContenido(cifradoPrivada, publicaReceptor);

            //Guardamos el mensaje encriptado en el fichero
            guardarFichero(cifradoPublica);

            //Mostramos que se ha encriptado correctamente
            System.out.println("Mensaje cifrado correctamente");

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
     * Metodo para recoger la ruta del fichero
     *
     * @return inputStream del fichero que tiene el mensaje
     */
    private static FileInputStream leerFichero() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del fichero a cifrar: ");
        String ruta = sc.nextLine();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(ruta);
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado");
            e.printStackTrace();
        }
        return fileInputStream;
    }

    /**
     * Metodo para insertar el mensaje codificado en la ruta seleccionada
     *
     * @param mensajeCifrado mensaje ya cifrado
     */
    private static void guardarFichero(byte[] mensajeCifrado) {
        try {
            FileOutputStream fos = new FileOutputStream("Tema4Ejercicios/src/mensajeCifrado.txt");
            fos.write(mensajeCifrado);
            fos.close();
        } catch (IOException e) {
            System.err.println("ERROR: Algo sucedió en la escritura del fichero");
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cifrar con una clave dada
     *
     * @param contenido mensaje a cifrar
     * @param clave     clave del emisor o receptor
     * @return un array de bytes con el mensaje encriptado
     * @throws Exception
     */
    public static byte[] cifrarContenido(byte[] contenido, Key clave) throws Exception {
        // Crear objeto Cipher
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Inicializar cifrador en modo cifrado con la clave proporcionada
        cifrador.init(Cipher.ENCRYPT_MODE, clave);

        // Calcular tamaño del bloque
        int tamanoBloque = (((RSAPublicKey) clave).getModulus().bitLength() + 7) / 8 - 11;

        // Inicializar buffer de salida
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();

        // Cifrar el contenido en bloques
        int offset = 0;
        while (offset < contenido.length) {
            int tamanoBloqueActual = Math.min(tamanoBloque, contenido.length - offset);
            byte[] bloqueCifrado = cifrador.doFinal(contenido, offset, tamanoBloqueActual);
            bufferSalida.write(bloqueCifrado);
            offset += tamanoBloqueActual;
        }

        // Devolver contenido cifrado completo
        return bufferSalida.toByteArray();
    }
}
