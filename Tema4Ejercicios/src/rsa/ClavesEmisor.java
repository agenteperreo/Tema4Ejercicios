package rsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ClavesEmisor {

    /**
     * Variables constantes con el nombre de los archivos en los que se van a guardar las keys publica y privada
     */
    public static final String FICHEROCLAVEPUBLICA = "clavePublicaEmisor.key";
    public static final String FICHEROCLAVEPRIVADA = "clavePrivadaEmisor.key";

    /**
     * Método principal que genera las claves y las guarda en ficheros
     */
    public static void main(String[] args) {
        KeyPair claves = generarClaves();
        guardarClaves(claves);
    }

    /**
     * Clase para generar un par de claves
     *
     * @return las claves generadas
     */
    public static KeyPair generarClaves() {
        KeyPairGenerator generator;
        KeyPair claves = null;

        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(5000);
            claves = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: Algoritmo no valido");
            e.printStackTrace();
        }

        return claves;
    }

    /**
     * Metodo que guarda las claves del emisor en los archivos
     *
     * @param claves claves publica y privada que se van a guardar
     */
    public static void guardarClaves(KeyPair claves) {
        FileOutputStream fos;

        try {
            //Creamos el fichero y guardamos la clave publica en él
            fos = new FileOutputStream(FICHEROCLAVEPUBLICA);
            fos.write(claves.getPublic().getEncoded());
            fos.close();

            //Cremaos el fichero y guardamos la clave privada en él
            fos = new FileOutputStream(FICHEROCLAVEPRIVADA);
            fos.write(claves.getPrivate().getEncoded());
            fos.close();

            //Control de excepciones
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: No se ha encontrado el fichero");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR: Algo no ha ido como debia");
            e.printStackTrace();
        }
    }

    /**
     * Metodo que lee la clave publica del fichero
     *
     * @return la clave publica del emisor
     */
    public static PublicKey getClavePublica() {
        //Guardamos el fichero con la clave publica en una variable file
        File ficheroClavePublica = new File(FICHEROCLAVEPUBLICA);
        PublicKey clavePublica = null;

        try {
            byte[] bytesPublica = Files.readAllBytes(ficheroClavePublica.toPath());
            KeyFactory generarKey = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytesPublica);
            clavePublica = generarKey.generatePublic(publicKeySpec);
        } catch (IOException e) {
            System.err.println("ERROR: Algo no salió como debía");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: El algoritmo no es valido");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.err.println("ERROR: Tipo de key Spec no valida");
            e.printStackTrace();
        }

        return clavePublica;
    }

    /**
     * Metodo que lee la clave privada del fichero
     *
     * @return clave privada del emisor
     */
    public static PrivateKey getClavePrivada() {
        //Guardamos el fichero con la clave privada en una variable file
        File ficheroClavePrivada = new File(FICHEROCLAVEPRIVADA);
        PrivateKey clavePrivada = null;

        try {
            byte[] bytesPrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
            KeyFactory generarKey = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytesPrivada);
            clavePrivada = generarKey.generatePrivate(privateKeySpec);

            //Control de excepciones
        } catch (IOException e) {
            System.err.println("ERROR: Algo no salió como debía");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: El algoritmo no es valido");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.err.println("ERROR: Tipo de key Spec no valida");
            e.printStackTrace();
        }

        return clavePrivada;
    }
}
