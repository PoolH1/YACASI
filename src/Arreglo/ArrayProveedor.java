package Arreglo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import Clase.Proveedor;
import conexión.ConexiónMySQL;

public class ArrayProveedor {
    
    public ArrayList<Proveedor> listarProveedores() {
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
        try {
            CallableStatement csta = ConexiónMySQL.getConexión()
                .prepareCall("{call sp_ListarProveedores()}");
            ResultSet rs = csta.executeQuery();
            Proveedor proveedor;
            while (rs.next()) {
                proveedor = new Proveedor(
                    rs.getString(1),  // cod_proveedor
                    rs.getString(2),  // nombre_proveedor
                    rs.getString(3),  // ruc_proveedor
                    rs.getString(4),  // telefono_proveedor
                    rs.getString(5),  // email_proveedor
                    rs.getString(6),  // direccion_proveedor
                    rs.getString(7)   // contacto_proveedor
                );
                lista.add(proveedor);
            }
        } catch (Exception e) {
            System.out.println("Error al listar proveedores: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    public ArrayList<Proveedor> consultarPorCodigo(String cod) {
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ConsultarProveedorCod(?)}");
            csta.setString(1, cod);
            ResultSet rs = csta.executeQuery();
            Proveedor proveedor;
            while (rs.next()) {
                proveedor = new Proveedor(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                );
                lista.add(proveedor);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar proveedor: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    public ArrayList<Proveedor> consultarPorNombre(String nom) {
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
        try {
            java.sql.Statement sta = ConexiónMySQL.getConexión().createStatement();
            ResultSet rs = sta.executeQuery(
                "SELECT * FROM Proveedor WHERE nombre_proveedor LIKE '%" + nom + "%'"
            );
            Proveedor proveedor;
            while (rs.next()) {
                proveedor = new Proveedor(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                );
                lista.add(proveedor);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar proveedor: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    public void insertar(Proveedor proveedor) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_InsertarProveedor(?,?,?,?,?,?,?)}");
            csta.setString(1, proveedor.getCodProveedor());
            csta.setString(2, proveedor.getNombreProveedor());
            csta.setString(3, proveedor.getRuc());
            csta.setString(4, proveedor.getTelefono());
            csta.setString(5, proveedor.getEmail());
            csta.setString(6, proveedor.getDireccion());
            csta.setString(7, proveedor.getContacto());
            csta.executeUpdate();
            System.out.println("Proveedor insertado correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al insertar proveedor: " + e);
            e.printStackTrace();
        }
    }
    
    public void eliminar(String cod) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EliminarProveedor(?)}");
            csta.setString(1, cod);
            csta.executeUpdate();
            System.out.println("Proveedor eliminado correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al eliminar proveedor: " + e);
            e.printStackTrace();
        }
    }
    
    public void editar(Proveedor proveedor) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EditarProveedor(?,?,?,?,?,?,?)}");
            csta.setString(1, proveedor.getCodProveedor());
            csta.setString(2, proveedor.getNombreProveedor());
            csta.setString(3, proveedor.getRuc());
            csta.setString(4, proveedor.getTelefono());
            csta.setString(5, proveedor.getEmail());
            csta.setString(6, proveedor.getDireccion());
            csta.setString(7, proveedor.getContacto());
            csta.executeUpdate();
            System.out.println("Proveedor editado correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al editar proveedor: " + e);
            e.printStackTrace();
        }
    }
}