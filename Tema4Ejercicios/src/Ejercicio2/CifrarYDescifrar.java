package Ejercicio2;

import java.util.Scanner;

public class CifrarYDescifrar {
    public static void main(String[] args) {
        menuPrincipal();
    }
    /**
     * Precondiciones: No tiene.
     * Método que crea la estructura del menú para mostrarla.
     * Postcondiciones: No tiene.
     */
    public static void pintarMenu() {
        System.out.println("""
                
                ---Máquina Enigma Casera---
                [1] CifrarMensaje.
                [2] DescifrarMensaje.
                [0] Salir.
                ---------------------------""");
    }
    /**
     * Precondiciones: No tiene.
     * Método que imprime por pantalla el menú y comprueba si la entrada es válida.
     * Postcondiciones: Lanza un proceso u otro según la opción elegida.
     */
    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        String menu;
        boolean salir = false;
        do {
            pintarMenu();
            menu = sc.next();
            switch (menu) {
                case "1" -> cifrarMensaje();
                case "2" -> descifrarMensaje();
                case "0" -> salir = true;
                default ->  System.out.println("\033[93;1;4mNo es una opción válida.\033[0m");
            }
        } while (!salir);
        sc.close();
    }
    /**
     * Precondiciones: No tiene.
     * Método que pide el texto y la clave para cifrarlo y guardarlo.
     * Postcondiciones: No tiene.
     */
    private static void cifrarMensaje() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el texto a cifrar");
        String texto = sc.nextLine();
        System.out.println("Introduce la clave de cifrado. Debe tener 16 caracteres");
        String clave = sc.nextLine();
        try {
            CifrarMensaje.guardarTextCifrado(texto, clave);
            System.out.println("Texto cifrado y guardado con éxito");
        }catch (Exception e) {
            System.err.println("Clave no válida");
            e.printStackTrace();
        }
    }
    /**
     * Precondiciones: No tiene.
     * Método que pide la clave para descifrar el texto y mostrarlo por pantalla.
     * Postcondiciones: No tiene.
     */
    private static void descifrarMensaje() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la clave de descifrado. Debe tener 16 caracteres");
        String clave = sc.nextLine();
        try {
            System.out.println("\033[93;1m"+DescifrarMensaje.leerTextCifrado(clave)+"\033[0m");
        } catch (Exception e) {
            System.err.println("Clave incorrecta");
            e.printStackTrace();
        }
    }
}