package Ejercicio2;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MaquinaEnigma {
    //Variables de clase
    private static final int LONGITUD = 16;
    private static final String ALGORITMO = "AES/ECB/PKCS5Padding";

    /**
     * Precondiciones: La clave debe ser un String y tener 16 caracteres.
     * Recibe la clave introducida por el usuario y la convierte en una Key para cifrar el mensaje.
     * Postcondiciones: Devuelve un Key con la clave para codificar el mensaje.
     * @param claveUsuario La clave introducida por el usuario
     * @return Key con la clave para codificar el mensaje
     */
    public static Key obtenerClave (String claveUsuario){
        return new SecretKeySpec(claveUsuario.getBytes(), 0, LONGITUD, "AES");
    }
    /**
     * Precondiciones: Debe recibir un texto y una clave para cifrar el texto.
     * Recibe el texto a cifrar y la clave para cifrarlo y mediante la clase Cipher cifra el texto y lo devuelve.
     * Postcondiciones: Devuelve el texto cifrado.
     * @param texto El texto a cifrar
     * @param clave La clave para cifrar el texto
     * @return El texto cifrado String
     */
    public static String cifrar (String texto, Key clave){
        byte[] cifrado = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            cifrado = cipher.doFinal(texto.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("El padding seleccionado no existe");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave utilizada no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque elegido no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding seleccionado no es correcto");
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(cifrado);
    }
    /**
     * Precondiciones: Debe recibir un texto cifrado y una clave para descifrar el texto.
     * Recibe el texto cifrado y la clave para descifrarlo y mediante la clase Cipher descifra el texto y lo devuelve.
     * Postcondiciones: Devuelve el texto descifrado en formato String.
     * @param mensajeCifrado El texto cifrado
     * @param clave La clave para descifrar el texto
     * @return El texto descifrado String
     */
    public static String descifrar (String mensajeCifrado, Key clave){
        byte[] descifrar = new byte[0];
        try{
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, clave);
            descifrar = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
        } catch (NoSuchPaddingException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("El padding seleccionado no existe");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("La clave utilizada no es válida");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El tamaño del bloque elegido no es correcto");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("El padding seleccionado no es correcto");
            e.printStackTrace();
        }
        return new String(descifrar);
    }
}