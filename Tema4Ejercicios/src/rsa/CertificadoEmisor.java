package rsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class CertificadoEmisor {

    public static final String FICHEROCLAVEPUBLICA = "clavePublicaEmisor.key";

    public static final String FICHEROCLAVEPRIVADA = "clavePrivadaEmisor.key";

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
            generator.initialize(2048);
            claves = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: Algoritmo no valido");
            e.printStackTrace();
        }

        return claves;
    }

    public static void guardarClaves(KeyPair claves) {
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(FICHEROCLAVEPUBLICA);
            fos.write(claves.getPublic().getEncoded());
            fos.close();
            fos = new FileOutputStream(FICHEROCLAVEPRIVADA);
            fos.write(claves.getPrivate().getEncoded());
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: No se ha encontrado el fichero");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR: Algo no ha ido como debia");
            e.printStackTrace();
        }
    }

    public static PublicKey getClavePublica() {
        File ficheroClavePublica= new File(FICHEROCLAVEPUBLICA);
        PublicKey clavePublica=null;

        try {
            byte[] bytesPublica = Files.readAllBytes(ficheroClavePublica.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytesPublica);
            
        } catch (IOException e) {
            System.err.println("ERROR: Algo no salió como debía");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR: El algoritmo no es valido");
            e.printStackTrace();
        }

        return clavePublica;
    }
}
