package Clase;

import java.util.Date;

/**
 * Clase que representa la relación entre Proveedor y Producto
 * Esta es una tabla intermedia para la relación muchos a muchos
 */
public class ProveedorProducto {
    private int idRelacion;
    private String codProveedor;
    private String nombreProveedor;
    private String codProducto;
    private String nombreProducto;
    private double precioSuministro;
    private Date fechaRegistro;
    private String estado;

    // Constructor completo
    public ProveedorProducto(int idRelacion, String codProveedor, String nombreProveedor,
                             String codProducto, String nombreProducto, 
                             double precioSuministro, Date fechaRegistro, String estado) {
        this.idRelacion = idRelacion;
        this.codProveedor = codProveedor;
        this.nombreProveedor = nombreProveedor;
        this.codProducto = codProducto;
        this.nombreProducto = nombreProducto;
        this.precioSuministro = precioSuministro;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Constructor simplificado para insertar
    public ProveedorProducto(String codProveedor, String codProducto, double precioSuministro) {
        this.codProveedor = codProveedor;
        this.codProducto = codProducto;
        this.precioSuministro = precioSuministro;
        this.estado = "Activo";
        this.fechaRegistro = new Date();
    }

    // Getters y Setters
    public int getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioSuministro() {
        return precioSuministro;
    }

    public void setPrecioSuministro(double precioSuministro) {
        this.precioSuministro = precioSuministro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proveedor: " + nombreProveedor + 
               " | Producto: " + nombreProducto + 
               " | Precio Suministro: S/ " + precioSuministro;
    }
}