package Clase;

public class Proveedor {
    private String codProveedor;
    private String nombreProveedor;
    private String ruc;
    private String telefono;
    private String email;
    private String direccion;
    private String contacto;

    // Constructor completo
    public Proveedor(String codProveedor, String nombreProveedor, String ruc, 
                     String telefono, String email, String direccion, String contacto) {
        this.codProveedor = codProveedor;
        this.nombreProveedor = nombreProveedor;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    // Getters y Setters
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    // Métodos de negocio
    public void mostrarInfo() {
        System.out.println("Proveedor: " + nombreProveedor + 
                           " | RUC: " + ruc + 
                           " | Contacto: " + contacto + 
                           " | Teléfono: " + telefono);
    }

    @Override
    public String toString() {
        return codProveedor + " - " + nombreProveedor;
    }
}
