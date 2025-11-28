package Arreglo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import Clase.Producto;
import conexión.ConexiónMySQL;

public class ArrayProducto {
    
	public ArrayList<Producto> listarProductos() {
	    ArrayList<Producto> lista = new ArrayList<Producto>();
	    try {
	        CallableStatement csta = ConexiónMySQL.getConexión()
	            .prepareCall("{call sp_ListarProductos()}");
	        ResultSet rs = csta.executeQuery();
	        Producto prod;
	        while (rs.next()) {
	            prod = new Producto(
	                rs.getString(1),  // cod_prod
	                rs.getString(2),  // nom_prod
	                rs.getString(3),  // marca_prod
	                rs.getDouble(4),  // precio_prod
	                rs.getInt(5)      // stock_prod
	            );
	            lista.add(prod);
	        }
	    } catch (Exception e) {
	        System.out.println("Error al listar: " + e);
	        e.printStackTrace();  // ← AGREGA ESTA LÍNEA para ver el error
	    }
	    return lista;
	}
    
    public ArrayList<Producto> consultarPorCodigo(String cod) {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ConsultarProductoCod(?)}");
            csta.setString(1, cod);
            ResultSet rs = csta.executeQuery();
            Producto prod;
            while (rs.next()) {
                prod = new Producto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getInt(5)
                );
                lista.add(prod);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar: " + e);
        }
        return lista;
    }
    
    public ArrayList<Producto> consultarPorNombre(String nom) {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try {
            java.sql.Statement sta = ConexiónMySQL.getConexión().createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM Producto WHERE nom_prod LIKE '%" + nom + "%'");
            Producto prod;
            while (rs.next()) {
                prod = new Producto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getInt(5)
                );
                lista.add(prod);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar: " + e);
        }
        return lista;
    }
    
    public void insertar(Producto prod) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_InsertarProducto(?,?,?,?,?)}");
            csta.setString(1, (String) prod.getCodProd());
            csta.setString(2, prod.getNombre());
            csta.setString(3, (String) prod.getMarca());
            csta.setDouble(4, prod.getPrecio());
            csta.setInt(5, prod.getStock());
            csta.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR al insertar: " + e);
        }
    }
    
    public void eliminar(String cod) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EliminarProducto(?)}");
            csta.setString(1, cod);
            csta.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR al eliminar: " + e);
        }
    }
    
    public void editar(Producto prod) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EditarProducto(?,?,?,?,?)}");
            csta.setString(1, (String) prod.getCodProd());
            csta.setString(2, prod.getNombre());
            csta.setString(3, (String) prod.getMarca());
            csta.setDouble(4, prod.getPrecio());
            csta.setInt(5, prod.getStock());
            csta.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR al editar: " + e);
        }
    }
    
    public void actualizarStock(String codProd, int cantidad) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ActualizarStock(?,?)}");
            csta.setString(1, codProd);
            csta.setInt(2, cantidad);
            csta.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR al actualizar stock: " + e);
        }
    }
    
}