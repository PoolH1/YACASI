package Arreglo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import Clase.Venta;
import conexión.ConexiónMySQL;

public class ArregloVentas {
    private ArrayList<Venta> ventas;
    private boolean usarBD = true; // Flag para usar BD o memoria

    public ArregloVentas() {
        ventas = new ArrayList<>();
    }

    public ArregloVentas(boolean usarBaseDatos) {
        ventas = new ArrayList<>();
        this.usarBD = usarBaseDatos;
    }

    // Métodos que funcionan con memoria (compatibilidad con código existente)
    public void adicionar(Venta v) {
        if (usarBD) {
            insertarEnBD(v);
        } else {
            ventas.add(v);
        }
    }

    public Venta buscar(int id) {
        if (usarBD) {
            return buscarEnBD(id);
        } else {
            for (Venta v : ventas) {
                if (v.getIden() == id) {
                    return v;
                }
            }
            return null;
        }
    }

    public void eliminar(Venta v) {
        if (usarBD) {
            eliminarEnBD(v.getIden());
        } else {
            ventas.remove(v);
        }
    }

    public int tamaño() {
        if (usarBD) {
            return listarVentas().size();
        } else {
            return ventas.size();
        }
    }

    public Venta obtener(int i) {
        if (usarBD) {
            ArrayList<Venta> lista = listarVentas();
            if (i >= 0 && i < lista.size()) {
                return lista.get(i);
            }
            return null;
        } else {
            return ventas.get(i);
        }
    }

    // Métodos para trabajar con Base de Datos
    public ArrayList<Venta> listarVentas() {
        ArrayList<Venta> lista = new ArrayList<Venta>();
        try {
            CallableStatement csta = ConexiónMySQL.getConexión().prepareCall("{call sp_ListarVentas()}");
            ResultSet rs = csta.executeQuery();
            Venta venta;
            while (rs.next()) {
                venta = new Venta(
                    rs.getInt(1),       // id_venta
                    rs.getString(2),    // nombre_cliente
                    rs.getString(3),    // cod_prod
                    rs.getString(4),    // nombre_prod
                    rs.getInt(5),       // cantidad
                    rs.getDouble(6),    // precio_unitario
                    rs.getDate(7)       // fecha
                );
                lista.add(venta);
            }
        } catch (Exception e) {
            System.out.println("Error al listar ventas: " + e);
        }
        return lista;
    }

    public Venta buscarEnBD(int id) {
        Venta venta = null;
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_BuscarVenta(?)}");
            csta.setInt(1, id);
            ResultSet rs = csta.executeQuery();
            if (rs.next()) {
                venta = new Venta(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getDate(7)
                );
            }
        } catch (Exception e) {
            System.out.println("Error al buscar venta: " + e);
        }
        return venta;
    }

    public void insertarEnBD(Venta v) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_InsertarVenta(?,?,?,?,?)}");
            csta.setInt(1, v.getIden());
            csta.setString(2, v.getNombreCliente());
            csta.setString(3, v.getCodProd());
            csta.setInt(4, v.getCant());
            csta.setDouble(5, v.getPreuni());
            csta.executeUpdate();
            System.out.println("Venta insertada correctamente en BD");
        } catch (Exception e) {
            System.out.println("ERROR al insertar venta: " + e);
        }
    }

    public void eliminarEnBD(int id) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EliminarVenta(?)}");
            csta.setInt(1, id);
            csta.executeUpdate();
            System.out.println("Venta eliminada correctamente de BD");
        } catch (Exception e) {
            System.out.println("ERROR al eliminar venta: " + e);
        }
    }

    public void editarEnBD(Venta v) {
        try {
            Connection cnx = ConexiónMySQL.getConexión();
            CallableStatement csta = cnx.prepareCall("{call sp_EditarVenta(?,?,?,?,?)}");
            csta.setInt(1, v.getIden());
            csta.setString(2, v.getNombreCliente());
            csta.setString(3, v.getCodProd());
            csta.setInt(4, v.getCant());
            csta.setDouble(5, v.getPreuni());
            csta.executeUpdate();
            System.out.println("Venta editada correctamente en BD");
        } catch (Exception e) {
            System.out.println("ERROR al editar venta: " + e);
        }
    }

    public ArrayList<Venta> consultarPorCliente(String nombreCliente) {
        ArrayList<Venta> lista = new ArrayList<Venta>();
        try {
            java.sql.Statement sta = ConexiónMySQL.getConexión().createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM Venta WHERE nombre_cliente LIKE '%" + nombreCliente + "%'");
            Venta venta;
            while (rs.next()) {
                venta = new Venta(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getDate(7)
                );
                lista.add(venta);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar: " + e);
        }
        return lista;
    }

    public boolean isUsarBD() {
        return usarBD;
    }

    public void setUsarBD(boolean usarBD) {
        this.usarBD = usarBD;
    }
}