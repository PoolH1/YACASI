package Arreglo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import Clase.Cliente;
import conexión.ConexiónMySQL;

public class ArrayCliente {
    
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try {
            CallableStatement csta = ConexiónMySQL.getConexión()
                .prepareCall("{call sp_ListarClientes()}");
            ResultSet rs = csta.executeQuery();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente(
                    rs.getString(1),  // cod_cliente
                    rs.getString(2),  // nombre_cliente
                    rs.getString(3),  // apellido_cliente
                    rs.getString(4),  // dni_cliente
                    rs.getString(5),  // telefono_cliente
                    rs.getString(6),  // email_cliente
                    rs.getString(7)   // direccion_cliente
                );
                lista.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    public ArrayList<Cliente> consultarPorCodigo(String cod) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_ConsultarClienteCod(?)}");
            csta.setString(1, cod);
            ResultSet rs = csta.executeQuery();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                );
                lista.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar cliente: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    public ArrayList<Cliente> consultarPorNombre(String nom) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try {
            java.sql.Statement sta = ConexiónMySQL.getConexión().createStatement();
            ResultSet rs = sta.executeQuery(
                "SELECT * FROM Cliente WHERE nombre_cliente LIKE '%" + nom + "%' " +
                "OR apellido_cliente LIKE '%" + nom + "%'"
            );
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                );
                lista.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar cliente: " + e);
            e.printStackTrace();
        }
        return lista;
    }
    
    public void insertar(Cliente cliente) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_InsertarCliente(?,?,?,?,?,?,?)}");
            csta.setString(1, cliente.getCodCliente());
            csta.setString(2, cliente.getNombre());
            csta.setString(3, cliente.getApellido());
            csta.setString(4, cliente.getDni());
            csta.setString(5, cliente.getTelefono());
            csta.setString(6, cliente.getEmail());
            csta.setString(7, cliente.getDireccion());
            csta.executeUpdate();
            System.out.println("Cliente insertado correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al insertar cliente: " + e);
            e.printStackTrace();
        }
    }
    
    public void eliminar(String cod) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EliminarCliente(?)}");
            csta.setString(1, cod);
            csta.executeUpdate();
            System.out.println("Cliente eliminado correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al eliminar cliente: " + e);
            e.printStackTrace();
        }
    }
    
    public void editar(Cliente cliente) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EditarCliente(?,?,?,?,?,?,?)}");
            csta.setString(1, cliente.getCodCliente());
            csta.setString(2, cliente.getNombre());
            csta.setString(3, cliente.getApellido());
            csta.setString(4, cliente.getDni());
            csta.setString(5, cliente.getTelefono());
            csta.setString(6, cliente.getEmail());
            csta.setString(7, cliente.getDireccion());
            csta.executeUpdate();
            System.out.println("Cliente editado correctamente");
        } catch (Exception e) {
            System.out.println("ERROR al editar cliente: " + e);
            e.printStackTrace();
        }
    }
}
