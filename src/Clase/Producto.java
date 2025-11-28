package Clase;

public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private String codProd;    // ← Agregar este campo
    private String marca;      // ← Agregar este campo

    // Constructor original (3 parámetros) - MANTENERLO
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Constructor NUEVO para Base de Datos (5 parámetros) - AGREGAR ESTE
    public Producto(String codProd, String nombre, String marca, double precio, int stock) {
        this.codProd = codProd;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // AGREGAR estos getters nuevos
    public String getCodProd() {
        return codProd;
    }

    public String getMarca() {
        return marca;
    }

    // Setters (si los necesitas)
    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Métodos de negocio
    public void vender(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
            System.out.println("Se vendieron " + cantidad + " unidades de " + nombre);
        } else {
            System.out.println("No hay suficiente stock de " + nombre);
        }
    }

    public void agregarStock(int cantidad) {
        stock += cantidad;
        System.out.println("Se agregaron " + cantidad + " unidades de " + nombre);
    }

    public void mostrarInfo() {
        System.out.println("Código: " + codProd + 
                           " | Producto: " + nombre + 
                           " | Marca: " + marca +
                           " | Precio: " + precio + 
                           " | Stock: " + stock);
    }

    public static void main(String[] args) {
        Producto p1 = new Producto("Martillo", 35.5, 10);
        p1.mostrarInfo();
        p1.vender(3);
        p1.mostrarInfo();
        p1.vender(15); 
    }
}