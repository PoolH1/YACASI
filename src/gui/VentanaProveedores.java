package gui;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Arreglo.ArrayProveedor;
import Clase.Proveedor;

public class VentanaProveedores extends JFrame implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCodigo, lblNombre, lblRuc, lblTelefono, lblEmail, lblDireccion, lblContacto;
    private JTextField txtCod, txtNom, txtRuc, txtTel, txtEmail, txtDir, txtContacto;
    private JButton btnAgregar, btnModificar, btnEliminar, btnLimpiar;
    private JScrollPane scrollPane;
    private JTable tbTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaProveedores frame = new VentanaProveedores();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaProveedores() {
        setTitle("Ferretería - Gestión de Proveedores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Columna 1
        lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(22, 20, 80, 13);
        contentPane.add(lblCodigo);

        txtCod = new JTextField();
        txtCod.setBounds(110, 17, 100, 19);
        contentPane.add(txtCod);

        lblNombre = new JLabel("Razón Social:");
        lblNombre.setBounds(22, 50, 80, 13);
        contentPane.add(lblNombre);

        txtNom = new JTextField();
        txtNom.setBounds(110, 47, 200, 19);
        contentPane.add(txtNom);

        lblRuc = new JLabel("RUC:");
        lblRuc.setBounds(22, 80, 80, 13);
        contentPane.add(lblRuc);

        txtRuc = new JTextField();
        txtRuc.setBounds(110, 77, 120, 19);
        contentPane.add(txtRuc);

        lblContacto = new JLabel("Contacto:");
        lblContacto.setBounds(22, 110, 80, 13);
        contentPane.add(lblContacto);

        txtContacto = new JTextField();
        txtContacto.setBounds(110, 107, 150, 19);
        contentPane.add(txtContacto);

        // Columna 2
        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(350, 20, 80, 13);
        contentPane.add(lblTelefono);

        txtTel = new JTextField();
        txtTel.setBounds(440, 17, 120, 19);
        contentPane.add(txtTel);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(350, 50, 80, 13);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(440, 47, 200, 19);
        contentPane.add(txtEmail);

        lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(350, 80, 80, 13);
        contentPane.add(lblDireccion);

        txtDir = new JTextField();
        txtDir.setBounds(440, 77, 300, 19);
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
        scrollPane.setBounds(22, 200, 800, 250);
        contentPane.add(scrollPane);

        tbTable = new JTable();
        tbTable.addMouseListener(this);
        tbTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(tbTable);

        Listar("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            agregarProveedor();
        } else if (e.getSource() == btnModificar) {
            modificarProveedor();
        } else if (e.getSource() == btnEliminar) {
            eliminarProveedor();
        } else if (e.getSource() == btnLimpiar) {
            Limpiar();
        }
    }

    protected void agregarProveedor() {
        try {
            Proveedor proveedor = new Proveedor(
                txtCod.getText(),
                txtNom.getText(),
                txtRuc.getText(),
                txtTel.getText(),
                txtEmail.getText(),
                txtDir.getText(),
                txtContacto.getText()
            );
            ArrayProveedor ap = new ArrayProveedor();
            ap.insertar(proveedor);
            JOptionPane.showMessageDialog(this, "Proveedor agregado correctamente");
            Listar("");
            Limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Complete todos los campos correctamente");
        }
    }

    protected void eliminarProveedor() {
        if (txtCod.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un proveedor para eliminar");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este proveedor?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ArrayProveedor ap = new ArrayProveedor();
            ap.eliminar(txtCod.getText());
            JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente");
            Listar("");
            Limpiar();
        }
    }

    protected void modificarProveedor() {
        try {
            Proveedor proveedor = new Proveedor(
                txtCod.getText(),
                txtNom.getText(),
                txtRuc.getText(),
                txtTel.getText(),
                txtEmail.getText(),
                txtDir.getText(),
                txtContacto.getText()
            );
            ArrayProveedor ap = new ArrayProveedor();
            ap.editar(proveedor);
            JOptionPane.showMessageDialog(this, "Proveedor modificado correctamente");
            Listar("");
            Limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Complete todos los campos correctamente");
        }
    }

    public void Listar(String nom) {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayProveedor ap = new ArrayProveedor();
        ArrayList<Proveedor> lista;
        
        if (nom.length() == 0)
            lista = ap.listarProveedores();
        else
            lista = ap.consultarPorNombre(nom);
        
        modelo.addColumn("Código");
        modelo.addColumn("Razón Social");
        modelo.addColumn("RUC");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");
        modelo.addColumn("Dirección");
        modelo.addColumn("Contacto");
        
        for (Proveedor prov : lista) {
            Object[] fila = {
                prov.getCodProveedor(),
                prov.getNombreProveedor(),
                prov.getRuc(),
                prov.getTelefono(),
                prov.getEmail(),
                prov.getDireccion(),
                prov.getContacto()
            };
            modelo.addRow(fila);
        }
        tbTable.setModel(modelo);
    }

    void Limpiar() {
        txtCod.setText("");
        txtNom.setText("");
        txtRuc.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        txtDir.setText("");
        txtContacto.setText("");
        txtCod.requestFocus();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tbTable) {
            int fila = tbTable.getSelectedRow();
            txtCod.setText(String.valueOf(tbTable.getValueAt(fila, 0)));
            txtNom.setText(String.valueOf(tbTable.getValueAt(fila, 1)));
            txtRuc.setText(String.valueOf(tbTable.getValueAt(fila, 2)));
            txtTel.setText(String.valueOf(tbTable.getValueAt(fila, 3)));
            txtEmail.setText(String.valueOf(tbTable.getValueAt(fila, 4)));
            txtDir.setText(String.valueOf(tbTable.getValueAt(fila, 5)));
            txtContacto.setText(String.valueOf(tbTable.getValueAt(fila, 6)));
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}