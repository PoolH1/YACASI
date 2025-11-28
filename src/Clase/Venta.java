package Clase;

import java.util.Date;

public class Venta {
    private int iden;
    private String nombreCliente;
    private String codProd;
    private String nombreProd;
    private int cant;
    private double preuni;
    private Date fecha;

    // Constructor completo con fecha
    public Venta(int iden, String nombreCliente, String codProd, String nombreProd, int cant, double preuni, Date fecha) {
        this.iden = iden;
        this.nombreCliente = nombreCliente;
        this.codProd = codProd;
        this.nombreProd = nombreProd;
        this.cant = cant;
        this.preuni = preuni;
        this.fecha = fecha;
    }

    // Constructor sin fecha (para compatibilidad con código existente)
    public Venta(int iden, String nombreCliente, String prod, int cant, double preuni) {
        this.iden = iden;
        this.nombreCliente = nombreCliente;
        this.codProd = prod;
        this.nombreProd = prod;
        this.cant = cant;
        this.preuni = preuni;
        this.fecha = new Date();
    }

    // Getters y Setters
    public int getIden() { 
        return iden; 
    }
    
    public void setIden(int iden) { 
        this.iden = iden; 
    }

    public String getNombre() { 
        return nombreCliente; 
    }
    
    public void setNombre(String nombre) { 
        this.nombreCliente = nombre; 
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getProd() { 
        return nombreProd; 
    }
    
    public void setProd(String prod) { 
        this.nombreProd = prod; 
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public int getCant() { 
        return cant; 
    }
    
    public void setCant(int cant) { 
        this.cant = cant; 
    }

    public double getPreuni() { 
        return preuni; 
    }
    
    public void setPreuni(double preuni) { 
        this.preuni = preuni; 
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Método para calcular el total
    public double total() {
        return cant * preuni;
    }

    @Override
    public String toString() {
        return "ID: " + iden + " | Cliente: " + nombreCliente + 
               " | Producto: " + nombreProd + " | Cantidad: " + cant + 
               " | Precio: " + preuni + " | Total: " + total();
    }
}