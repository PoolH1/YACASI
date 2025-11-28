package Clase;

import java.util.ArrayList;
import java.util.Scanner;

public class Menú {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Venta> ventas = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n=== MENÚ FERRETERÍA ===");
            System.out.println("1. Adicionar venta");
            System.out.println("2. Buscar venta");
            System.out.println("3. Eliminar venta");
            System.out.println("4. Modificar venta");
            System.out.println("5. Reportar ventas");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch(opcion) {
                case 1: adicionar(); break;
                case 2: buscar(); break;
                case 3: eliminar(); break;
                case 4: modificar(); break;
                case 5: reportar(); break;
                case 6: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida");
            }
        } while(opcion != 6);

        sc.close();
    }
    static void adicionar() {
        System.out.println("[Adicionar venta - en construcción]");
    }

    static void buscar() {
        System.out.println("[Buscar venta - en construcción]");
    }

    static void eliminar() {
        System.out.print("Ingrese el ID de la venta a eliminar: ");
        int id = sc.nextInt();

        Venta encontrada = null;
        for (Venta v : ventas) {
            if (v.getIden() == id) {
                encontrada = v;
                break;
            }
        }

        if (encontrada != null) {
            ventas.remove(encontrada);
            System.out.println("Venta eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna venta con ese ID.");
        }
    }

    static void modificar() {
        System.out.println("[Modificar venta - en construcción]");
    }

    static void reportar() {
        System.out.println("[Reportar ventas - en construcción]");
    }
}