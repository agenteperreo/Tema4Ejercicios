package Ejercicio1;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
Una clase que se encargue del proceso de cálculo de hash. Las otras dos clases harán uso de esta.
Esta clase contendrá dos métodos:
getDigest: Obtiene el resumen del array de bytes proporcionado a partir del algoritmo proporcionado.
compararResumenes: compara dos arrays de bytes (resúmenes) indicando si son o no iguales.
 */
/**
 * Clase que tiene los métodos de codificación y comparación de resúmenes para que las otras clases puedan usarlos.
 */
public class Coder {
    /**
     * Precondiciones: No tiene.
     * Obtiene el resumen del array de bytes proporcionado a partir del algoritmo elegido.
     * @param input String a codificar
     * @return byte[] resumen del mensaje
     */
    public static byte[] getDigest(String input) {
        //Algoritmo de codificación
        MessageDigest algoritmo;
        //Array de bytes del mensaje
        byte[] byteinput;
        //Array de bytes del resumen
        byte[] resumen = new byte[0];
        try {
            //Saco los bytes del mensaje
            byteinput = input.getBytes(StandardCharsets.UTF_8);
            //Obtengo el algoritmo de codificación
            algoritmo = MessageDigest.getInstance("SHA-256");
            //Reseteo el algoritmo
            algoritmo.reset();
            //Actualizo el algoritmo con el mensaje
            algoritmo.update(byteinput);
            //Guardo el resumen obtenido
            resumen = algoritmo.digest();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo seleccionado no existe");
            e.printStackTrace();
        }
        //Devuelvo el resumen
        return resumen;
    }
    /**
     * Precondiciones: No tiene.
     * Compara dos arrays de bytes (resúmenes) indicando si son o no iguales.
     * Postcondiciones: Devuelve true si son iguales, false si no lo son.
     * @param resumen1 Array de bytes a comparar
     * @param resumen2 Array de bytes a comparar
     * @return boolean con el resultado
     */
    public static boolean compararResumenes(byte[] resumen1, byte[] resumen2) {
        //Uso el método de la clase MessageDigest para comparar los resúmenes de bytes si son iguales y devuelvo el resultado.
        return MessageDigest.isEqual(resumen1, resumen2);
    }
    /**
     * Precondiciones: No tiene.
     * Compara dos arrays de bytes (resúmenes) indicando si son o no iguales transformándolos antes en hash hexadecimal.
     * Postcondiciones: Devuelve true si son iguales, false si no lo son.
     * @param resumen1 Array de bytes a comparar
     * @param resumen2 Array de bytes a comparar
     * @return boolean con el resultado
     */
    public static boolean compararHash (byte[] resumen1, byte[] resumen2) {
        //Transformo los resúmenes en hash hexadecimal
        String hash1 = String.format("%064x", new BigInteger(1, resumen1));
        String hash2 = String.format("%064x", new BigInteger(1, resumen2));
        //Devuelvo el resultado de la comparación pero en formato hexadecimal
        return hash1.equals(hash2);
    }
}