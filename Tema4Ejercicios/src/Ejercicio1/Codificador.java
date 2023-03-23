package Ejercicio1;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que tiene los métodos de codificación y comparación de resúmenes para que las otras clases puedan usarlos.
 *
 * @author igarcia
 */
public class Codificador {
    /**
     * Obtiene el resumen del array de bytes proporcionado a partir del algoritmo elegido.
     *
     * @param mensaje String a codificar
     * @return byte[] resumen del mensaje
     */
    public static byte[] getDigest(String mensaje) {
        //Algoritmo de codificación
        MessageDigest algoritmo;

        //Array de bytes del mensaje
        byte[] arrayBytesMensaje;

        //Array de bytes del resumen
        byte[] resumen = new byte[0];

        try {
            //Saco los bytes del mensaje
            arrayBytesMensaje = mensaje.getBytes(StandardCharsets.UTF_8);

            //Obtengo el algoritmo de codificación
            algoritmo = MessageDigest.getInstance("SHA-256");

            //Reseteo el algoritmo
            algoritmo.reset();

            //Actualizo el algoritmo con el mensaje
            algoritmo.update(arrayBytesMensaje);

            //Guardo el resumen obtenido
            resumen = algoritmo.digest();

        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: El algoritmo seleccionado no existe");
            e.printStackTrace();
        }

        //Devuelvo el resumen
        return resumen;
    }

}