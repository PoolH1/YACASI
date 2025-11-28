package Clase;

public class Empleado {
	private String nombre;
    private int edad;
    private String cargo;

    public Empleado(String nombre, int edad, String cargo) {
        this.nombre = nombre;
        this.edad = edad;
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCargo() {
        return cargo;
    }

    public void mostrarInfo() {
        System.out.println("Empleado: " + nombre + 
                           " | Edad: " + edad + 
                           " | Cargo: " + cargo);
    }

    public static void main(String[] args) {
        Empleado e1 = new Empleado("Carlos", 30, "Vendedor");
        e1.mostrarInfo();
    }
}
