package Clase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {
	private String nombreArchivo;

    public GestorArchivos(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void guardarTexto(String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            bw.write(texto);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public ArrayList<String> leerTexto() {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return lineas;
    }
}






























