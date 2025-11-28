package Arreglo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import Clase.ProveedorProducto;
import Clase.Producto;
import Clase.Proveedor;
import conexión.ConexiónMySQL;

public class ArrayProveedorProducto {
    
    /**
     * Lista todas las relaciones Proveedor-Producto
     */
    public ArrayList<ProveedorProducto> listarTodasRelaciones() {
        ArrayList<ProveedorProducto> lista = new ArrayList<ProveedorProducto>();
        try {
            CallableStatement csta = ConexiónMySQL.getConexión()
                .prepareCall("{call sp_ListarTodasRelaciones()}");
            ResultSet rs = csta.executeQuery();
            ProveedorProducto pp;
            while (rs.next()) {
                pp = new ProveedorProducto(
                    rs.getInt(1),       // id_relacion
                    rs.getString(2),    // cod_proveedor
                    rs.getString(3),    // nombre_proveedor
                    rs.getString(4),    // cod_prod
                    rs.getString(5),    // nom_prod
                    rs.getDouble(6),    // precio_suministro
                    rs.getDate(7),      // fecha_registro
                    rs.getString(8)     // estado
                );
                lista.add(pp);
            }
        } catch (Exception e) {
            System.out.println("Error al listar relaciones: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
     * Lista todos los productos que suministra un proveedor específico
     */
    public ArrayList<Producto> listarProductosDeProveedor(String codProveedor) {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ListarProductosDeProveedor(?)}");
            csta.setString(1, codProveedor);
            ResultSet rs = csta.executeQuery();
            Producto prod;
            while (rs.next()) {
                prod = new Producto(
                    rs.getString(1),    // cod_prod
                    rs.getString(2),    // nom_prod
                    rs.getString(3),    // marca_prod
                    rs.getDouble(4),    // precio_prod
                    rs.getInt(5)        // stock_prod
                );
                lista.add(prod);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos del proveedor: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
     * Lista todos los proveedores que suministran un producto específico
     */
    public ArrayList<Proveedor> listarProveedoresDeProducto(String codProducto) {
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ListarProveedoresDeProducto(?)}");
            csta.setString(1, codProducto);
            ResultSet rs = csta.executeQuery();
            Proveedor prov;
            while (rs.next()) {
                prov = new Proveedor(
                    rs.getString(1),    // cod_proveedor
                    rs.getString(2),    // nombre_proveedor
                    rs.getString(3),    // ruc_proveedor
                    rs.getString(4),    // telefono_proveedor
                    rs.getString(5),    // email_proveedor
                    "",                 // direccion (no incluida en SP)
                    ""                  // contacto (no incluido en SP)
                );
                lista.add(prov);
            }
        } catch (Exception e) {
            System.out.println("Error al listar proveedores del producto: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
     * Cuenta cuántos productos suministra un proveedor
     */
    public int contarProductosPorProveedor(String codProveedor) {
        int total = 0;
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ContarProductosPorProveedor(?)}");
            csta.setString(1, codProveedor);
            ResultSet rs = csta.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total_productos");
            }
        } catch (Exception e) {
            System.out.println("Error al contar productos: " + e);
            e.printStackTrace();
        }
        return total;
    }
    
    /**
     * Cuenta cuántos proveedores suministran un producto
     */
    public int contarProveedoresPorProducto(String codProducto) {
        int total = 0;
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ContarProveedoresPorProducto(?)}");
            csta.setString(1, codProducto);
            ResultSet rs = csta.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total_proveedores");
            }
        } catch (Exception e) {
            System.out.println("Error al contar proveedores: " + e);
            e.printStackTrace();
        }
        return total;
    }
    
    /**
     * Inserta una nueva relación Proveedor-Producto
     */
    public void insertar(ProveedorProducto pp) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_InsertarProveedorProducto(?,?,?)}");
            csta.setString(1, pp.getCodProveedor());
            csta.setString(2, pp.getCodProducto());
            csta.setDouble(3, pp.getPrecioSuministro());
            csta.executeUpdate();
            System.out.println("Relación Proveedor-Producto insertada correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al insertar relación: " + e);
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina una relación Proveedor-Producto
     */
    public void eliminar(String codProveedor, String codProducto) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EliminarProveedorProducto(?,?)}");
            csta.setString(1, codProveedor);
            csta.setString(2, codProducto);
            csta.executeUpdate();
            System.out.println("Relación Proveedor-Producto eliminada correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al eliminar relación: " + e);
            e.printStackTrace();
        }
    }
    
    /**
     * Verifica si existe una relación entre un proveedor y un producto
     */
    public boolean existeRelacion(String codProveedor, String codProducto) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            java.sql.Statement sta = cnx.createStatement();
            ResultSet rs = sta.executeQuery(
                "SELECT COUNT(*) as total FROM Proveedor_Producto " +
                "WHERE cod_proveedor = '" + codProveedor + "' " +
                "AND cod_prod = '" + codProducto + "' " +
                "AND estado = 'Activo'"
            );
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al verificar relación: " + e);
            e.printStackTrace();
        }
        return false;
    }
}