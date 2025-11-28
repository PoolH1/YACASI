package gui;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Arreglo.ArrayCliente;
import Clase.Cliente;

public class VentanaClientes extends JFrame implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCodigo, lblNombre, lblApellido, lblDni, lblTelefono, lblEmail, lblDireccion;
    private JTextField txtCod, txtNom, txtApe, txtDni, txtTel, txtEmail, txtDir;
    private JButton btnAgregar, btnModificar, btnEliminar, btnLimpiar;
    private JScrollPane scrollPane;
    private JTable tbTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaClientes frame = new VentanaClientes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaClientes() {
        setTitle("Ferretería - Gestión de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Columna 1
        lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(22, 20, 60, 13);
        contentPane.add(lblCodigo);

        txtCod = new JTextField();
        txtCod.setBounds(92, 17, 100, 19);
        contentPane.add(txtCod);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(22, 50, 60, 13);
        contentPane.add(lblNombre);

        txtNom = new JTextField();
        txtNom.setBounds(92, 47, 150, 19);
        contentPane.add(txtNom);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(22, 80, 60, 13);
        contentPane.add(lblApellido);

        txtApe = new JTextField();
        txtApe.setBounds(92, 77, 150, 19);
        contentPane.add(txtApe);

        lblDni = new JLabel("DNI:");
        lblDni.setBounds(22, 110, 60, 13);
        contentPane.add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(92, 107, 100, 19);
        contentPane.add(txtDni);

        // Columna 2
        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(280, 20, 70, 13);
        contentPane.add(lblTelefono);

        txtTel = new JTextField();
        txtTel.setBounds(360, 17, 120, 19);
        contentPane.add(txtTel);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(280, 50, 70, 13);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(360, 47, 200, 19);
        contentPane.add(txtEmail);

        lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(280, 80, 70, 13);
        contentPane.add(lblDireccion);

        txtDir = new JTextField();
        txtDir.setBounds(360, 77, 300, 19);
        contentPane.add(txtDir);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this);
        btnAgregar.setBounds(22, 150, 120, 25);
        contentPane.add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnModificar.setBounds(152, 150, 120, 25);
        contentPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.setBounds(282, 150, 120, 25);
        contentPane.add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        btnLimpiar.setBounds(412, 150, 120, 25);
        contentPane.add(btnLimpiar);

        // Tabla
        scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 200, 750, 250);
        contentPane.add(scrollPane);

        tbTable = new JTable();
        tbTable.addMouseListener(this);
        tbTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(tbTable);

        Listar("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            agregarCliente();
        } else if (e.getSource() == btnModificar) {
            modificarCliente();
        } else if (e.getSource() == btnEliminar) {
            eliminarCliente();
        } else if (e.getSource() == btnLimpiar) {
            Limpiar();
        }
    }

    protected void agregarCliente() {
        try {
            Cliente cliente = new Cliente(
                txtCod.getText(),
                txtNom.getText(),
                txtApe.getText(),
                txtDni.getText(),
                txtTel.getText(),
                txtEmail.getText(),
                txtDir.getText()
            );
            ArrayCliente ac = new ArrayCliente();
            ac.insertar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente");
            Listar("");
            Limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Complete todos los campos correctamente");
        }
    }

    protected void eliminarCliente() {
        if (txtCod.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este cliente?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ArrayCliente ac = new ArrayCliente();
            ac.eliminar(txtCod.getText());
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
            Listar("");
            Limpiar();
        }
    }

    protected void modificarCliente() {
        try {
            Cliente cliente = new Cliente(
                txtCod.getText(),
                txtNom.getText(),
                txtApe.getText(),
                txtDni.getText(),
                txtTel.getText(),
                txtEmail.getText(),
                txtDir.getText()
            );
            ArrayCliente ac = new ArrayCliente();
            ac.editar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente modificado correctamente");
            Listar("");
            Limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Complete todos los campos correctamente");
        }
    }

    public void Listar(String nom) {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayCliente ac = new ArrayCliente();
        ArrayList<Cliente> lista;
        
        if (nom.length() == 0)
            lista = ac.listarClientes();
        else
            lista = ac.consultarPorNombre(nom);
        
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");
        modelo.addColumn("Dirección");
        
        for (Cliente cli : lista) {
            Object[] fila = {
                cli.getCodCliente(),
                cli.getNombre(),
                cli.getApellido(),
                cli.getDni(),
                cli.getTelefono(),
                cli.getEmail(),
                cli.getDireccion()
            };
            modelo.addRow(fila);
        }
        tbTable.setModel(modelo);
    }

    void Limpiar() {
        txtCod.setText("");
        txtNom.setText("");
        txtApe.setText("");
        txtDni.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        txtDir.setText("");
        txtCod.requestFocus();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tbTable) {
            int fila = tbTable.getSelectedRow();
            txtCod.setText(String.valueOf(tbTable.getValueAt(fila, 0)));
            txtNom.setText(String.valueOf(tbTable.getValueAt(fila, 1)));
            txtApe.setText(String.valueOf(tbTable.getValueAt(fila, 2)));
            txtDni.setText(String.valueOf(tbTable.getValueAt(fila, 3)));
            txtTel.setText(String.valueOf(tbTable.getValueAt(fila, 4)));
            txtEmail.setText(String.valueOf(tbTable.getValueAt(fila, 5)));
            txtDir.setText(String.valueOf(tbTable.getValueAt(fila, 6)));
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}