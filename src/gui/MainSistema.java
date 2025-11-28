package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal del sistema de gestión de ferretería
 * 
 * Este es el punto de entrada de toda la aplicación.
 * Inicia el sistema mostrando primero la ventana de login,
 * y después de autenticarse, abre el menú principal.
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class MainSistema {

	public static void main(String[] args) {
	    // Configurar look and feel PRIMERO
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            mostrarPantallaBienvenida();
	            VentanaLogin login = new VentanaLogin();
	            login.setVisible(true);
	        }
	    });
	}

    /**
     * Muestra una pantalla de bienvenida/splash (opcional)
     */
    private static void mostrarPantallaBienvenida() {
        System.out.println("=====================================");
        System.out.println("  SISTEMA DE GESTIÓN - FERRETERÍA   ");
        System.out.println("         Versión 1.0 - 2025          ");
        System.out.println("=====================================");
        System.out.println("Iniciando sistema...");
        System.out.println();
    }
}