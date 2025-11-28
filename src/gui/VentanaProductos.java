package gui;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Arreglo.ArrayProducto;
import Clase.Producto;

public class VentanaProductos extends JFrame implements ActionListener, MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCodigo, lblNombre, lblMarca, lblPrecio, lblStock;
    private JTextField txtCod, txtNom, txtMarca, txtPre, txtStock;
    private JButton btnAgregar, btnModificar, btnEliminar, btnLimpiar;
    private JScrollPane scrollPane;
    private JTable tbTable;

    public static void main(String[] args) {
        // Configurar look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaProductos frame = new VentanaProductos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        
        
    }

    public VentanaProductos() {
        setTitle("Ferretería - Mantenimiento de Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Labels y TextFields
        lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(22, 20, 60, 13);
        contentPane.add(lblCodigo);

        txtCod = new JTextField();
        txtCod.setBounds(92, 17, 100, 19);
        contentPane.add(txtCod);
        txtCod.setColumns(10);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(22, 50, 60, 13);
        contentPane.add(lblNombre);

        txtNom = new JTextField();
        txtNom.addKeyListener(this);
        txtNom.setColumns(10);
        txtNom.setBounds(92, 47, 150, 19);
        contentPane.add(txtNom);

        lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(22, 80, 60, 13);
        contentPane.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setColumns(10);
        txtMarca.setBounds(92, 77, 150, 19);
        contentPane.add(txtMarca);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(280, 20, 60, 13);
        contentPane.add(lblPrecio);

        txtPre = new JTextField();
        txtPre.addKeyListener(this);
        txtPre.setColumns(10);
        txtPre.setBounds(350, 17, 100, 19);
        contentPane.add(txtPre);

        lblStock = new JLabel("Stock:");
        lblStock.setBounds(280, 50, 60, 13);
        contentPane.add(lblStock);

        txtStock = new JTextField();
        txtStock.addKeyListener(this);
        txtStock.setColumns(10);
        txtStock.setBounds(350, 47, 100, 19);
        contentPane.add(txtStock);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this);
        btnAgregar.setBounds(480, 16, 120, 25);
        contentPane.add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnModificar.setBounds(480, 46, 120, 25);
        contentPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.setBounds(480, 76, 120, 25);
        contentPane.add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        btnLimpiar.setBounds(480, 106, 120, 25);
        contentPane.add(btnLimpiar);
        
        

        // Tabla
        scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 150, 600, 240);
        contentPane.add(scrollPane);

        tbTable = new JTable();
        tbTable.addMouseListener(this);
        tbTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(tbTable);

        Listar("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            agregarProducto();
        } else if (e.getSource() == btnModificar) {
            modificarProducto();
        } else if (e.getSource() == btnEliminar) {
            eliminarProducto();
        } else if (e.getSource() == btnLimpiar) {
            Limpiar();
        }
    }

    protected void agregarProducto() {
        try {
            Producto prod = new Producto(
                txtCod.getText(),
                txtNom.getText(),
                txtMarca.getText(),
                Double.parseDouble(txtPre.getText()),
                Integer.parseInt(txtStock.getText())
            );
            ArrayProducto ap = new ArrayProducto();
            ap.insertar(prod);
            JOptionPane.showMessageDialog(this, "Producto agregado correctamente");
            Listar("");
            Limpiar();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(this, "Error: Complete todos los campos correctamente");
        }
    }

    protected void eliminarProducto() {
        if (txtCod.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este producto?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ArrayProducto ap = new ArrayProducto();
            ap.eliminar(txtCod.getText());
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
            Listar("");
            Limpiar();
        }
    }

    protected void modificarProducto() {
        try {
            Producto prod = new Producto(
                txtCod.getText(),
                txtNom.getText(),
                txtMarca.getText(),
                Double.parseDouble(txtPre.getText()),
                Integer.parseInt(txtStock.getText())
            );
            ArrayProducto ap = new ArrayProducto();
            ap.editar(prod);
            JOptionPane.showMessageDialog(this, "Producto modificado correctamente");
            Listar("");
            Limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Complete todos los campos correctamente");
        }
    }

    public void Listar(String nom) {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayProducto ap = new ArrayProducto();
        ArrayList<Producto> lista = new ArrayList<Producto>();
        
        if (nom.length() == 0)
            lista = ap.listarProductos();
        else
            lista = ap.consultarPorNombre(nom);
        
        modelo.setRowCount(lista.size());
        Iterator<Producto> it = lista.iterator();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        
        int i = 0;
        while (it.hasNext()) {
            Producto prod = it.next();
            modelo.setValueAt(prod.getCodProd(), i, 0);
            modelo.setValueAt(prod.getNombre(), i, 1);
            modelo.setValueAt(prod.getMarca(), i, 2);
            modelo.setValueAt(prod.getPrecio(), i, 3);
            modelo.setValueAt(prod.getStock(), i, 4);
            i++;
        }
        tbTable.setModel(modelo);
    }

    void Limpiar() {
        txtCod.setText("");
        txtNom.setText("");
        txtMarca.setText("");
        txtPre.setText("");
        txtStock.setText("");
        txtCod.requestFocus();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tbTable) {
            int fila = tbTable.getSelectedRow();
            txtCod.setText(String.valueOf(tbTable.getValueAt(fila, 0)));
            txtNom.setText(String.valueOf(tbTable.getValueAt(fila, 1)));
            txtMarca.setText(String.valueOf(tbTable.getValueAt(fila, 2)));
            txtPre.setText(String.valueOf(tbTable.getValueAt(fila, 3)));
            txtStock.setText(String.valueOf(tbTable.getValueAt(fila, 4)));
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {
        if (e.getSource() == txtNom) {
            char c = e.getKeyChar();
            if (Character.isDigit(c)) {
                e.consume();
                JOptionPane.showMessageDialog(this, "Solo se permiten letras en el nombre");
            }
        }
        if (e.getSource() == txtPre || e.getSource() == txtStock) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                e.consume();
                JOptionPane.showMessageDialog(this, "Solo se permiten números");
            }
        }
    }
    
    
    
}