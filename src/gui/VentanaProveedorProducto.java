package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import Arreglo.*;
import Clase.*;

public class VentanaProveedorProducto extends JFrame implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    // Componentes principales
    private JComboBox<String> cboProveedor, cboProducto;
    private JTextField txtPrecioSuministro;
    private JButton btnAgregar, btnEliminar, btnRefrescar;
    
    // Tablas
    private JTable tbRelaciones, tbProductosProveedor, tbProveedoresProducto;
    private JScrollPane scrollRelaciones, scrollProductos, scrollProveedores;
    
    // Labels de contadores
    private JLabel lblTotalRelaciones, lblProductosProveedor, lblProveedoresProducto;
    
    // Arreglos
    private ArrayProveedorProducto app;
    private ArrayProveedor ap;
    private ArrayProducto aprod;

    public VentanaProveedorProducto() {
        setTitle("Gestión de Relación Proveedor-Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1100, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Inicializar arreglos
        app = new ArrayProveedorProducto();
        ap = new ArrayProveedor();
        aprod = new ArrayProducto();

        // ===== PANEL SUPERIOR: FORMULARIO =====
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(new TitledBorder("Asignar Producto a Proveedor"));
        panelFormulario.setBounds(10, 10, 1070, 150);
        panelFormulario.setLayout(null);
        contentPane.add(panelFormulario);

        // Proveedor
        JLabel lblProveedor = new JLabel("Proveedor:");
        lblProveedor.setFont(new Font("Arial", Font.BOLD, 12));
        lblProveedor.setBounds(20, 30, 80, 25);
        panelFormulario.add(lblProveedor);

        cboProveedor = new JComboBox<>();
        cboProveedor.setBounds(110, 30, 300, 25);
        cboProveedor.addActionListener(this);
        panelFormulario.add(cboProveedor);

        JButton btnRefrescarProv = new JButton("↻");
        btnRefrescarProv.setBounds(415, 30, 45, 25);
        btnRefrescarProv.addActionListener(e -> cargarProveedores());
        panelFormulario.add(btnRefrescarProv);

        lblProductosProveedor = new JLabel("Productos: 0");
        lblProductosProveedor.setForeground(Color.BLUE);
        lblProductosProveedor.setBounds(470, 30, 150, 25);
        panelFormulario.add(lblProductosProveedor);

        // Producto
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setFont(new Font("Arial", Font.BOLD, 12));
        lblProducto.setBounds(20, 65, 80, 25);
        panelFormulario.add(lblProducto);

        cboProducto = new JComboBox<>();
        cboProducto.setBounds(110, 65, 300, 25);
        cboProducto.addActionListener(this);
        panelFormulario.add(cboProducto);

        JButton btnRefrescarProd = new JButton("↻");
        btnRefrescarProd.setBounds(415, 65, 45, 25);
        btnRefrescarProd.addActionListener(e -> cargarProductos());
        panelFormulario.add(btnRefrescarProd);

        lblProveedoresProducto = new JLabel("Proveedores: 0");
        lblProveedoresProducto.setForeground(Color.BLUE);
        lblProveedoresProducto.setBounds(470, 65, 150, 25);
        panelFormulario.add(lblProveedoresProducto);

        // Precio de suministro
        JLabel lblPrecio = new JLabel("Precio Suministro:");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 12));
        lblPrecio.setBounds(20, 100, 120, 25);
        panelFormulario.add(lblPrecio);

        txtPrecioSuministro = new JTextField();
        txtPrecioSuministro.setBounds(150, 100, 100, 25);
        panelFormulario.add(txtPrecioSuministro);

        // Botones
        btnAgregar = new JButton("Asignar Relación");
        btnAgregar.setBackground(new Color(46, 204, 113));
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAgregar.setBounds(270, 100, 150, 30);
        btnAgregar.addActionListener(this);
        panelFormulario.add(btnAgregar);

        btnEliminar = new JButton("Eliminar Relación");
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setBounds(430, 100, 150, 30);
        btnEliminar.addActionListener(this);
        panelFormulario.add(btnEliminar);

        btnRefrescar = new JButton("Refrescar Todo");
        btnRefrescar.setBackground(new Color(52, 152, 219));
        btnRefrescar.setForeground(Color.BLACK);
        btnRefrescar.setFont(new Font("Arial", Font.BOLD, 12));
        btnRefrescar.setBounds(590, 100, 150, 30);
        btnRefrescar.addActionListener(this);
        panelFormulario.add(btnRefrescar);

        // ===== PANEL CENTRAL: TODAS LAS RELACIONES =====
        JPanel panelRelaciones = new JPanel();
        panelRelaciones.setBorder(new TitledBorder("Todas las Relaciones Proveedor-Producto"));
        panelRelaciones.setBounds(10, 170, 700, 480);
        panelRelaciones.setLayout(new BorderLayout());
        contentPane.add(panelRelaciones);

        lblTotalRelaciones = new JLabel("Total de relaciones: 0");
        lblTotalRelaciones.setFont(new Font("Arial", Font.BOLD, 12));
        lblTotalRelaciones.setForeground(Color.BLUE);
        panelRelaciones.add(lblTotalRelaciones, BorderLayout.NORTH);

        tbRelaciones = new JTable();
        tbRelaciones.addMouseListener(this);
        scrollRelaciones = new JScrollPane(tbRelaciones);
        panelRelaciones.add(scrollRelaciones, BorderLayout.CENTER);

        // ===== PANEL DERECHO SUPERIOR: PRODUCTOS DEL PROVEEDOR =====
        JPanel panelProductos = new JPanel();
        panelProductos.setBorder(new TitledBorder("Productos del Proveedor Seleccionado"));
        panelProductos.setBounds(720, 170, 360, 235);
        panelProductos.setLayout(new BorderLayout());
        contentPane.add(panelProductos);

        tbProductosProveedor = new JTable();
        scrollProductos = new JScrollPane(tbProductosProveedor);
        panelProductos.add(scrollProductos, BorderLayout.CENTER);

        // ===== PANEL DERECHO INFERIOR: PROVEEDORES DEL PRODUCTO =====
        JPanel panelProveedores = new JPanel();
        panelProveedores.setBorder(new TitledBorder("Proveedores del Producto Seleccionado"));
        panelProveedores.setBounds(720, 415, 360, 235);
        panelProveedores.setLayout(new BorderLayout());
        contentPane.add(panelProveedores);

        tbProveedoresProducto = new JTable();
        scrollProveedores = new JScrollPane(tbProveedoresProducto);
        panelProveedores.add(scrollProveedores, BorderLayout.CENTER);

        // Cargar datos iniciales
        cargarProveedores();
        cargarProductos();
        listarTodasRelaciones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            agregarRelacion();
        } else if (e.getSource() == btnEliminar) {
            eliminarRelacion();
        } else if (e.getSource() == btnRefrescar) {
            refrescarTodo();
        } else if (e.getSource() == cboProveedor) {
            actualizarProductosProveedor();
        } else if (e.getSource() == cboProducto) {
            actualizarProveedoresProducto();
        }
    }

    private void cargarProveedores() {
        cboProveedor.removeAllItems();
        cboProveedor.addItem("-- Seleccione un proveedor --");
        
        ArrayList<Proveedor> proveedores = ap.listarProveedores();
        for (Proveedor prov : proveedores) {
            cboProveedor.addItem(prov.getCodProveedor() + " - " + prov.getNombreProveedor());
        }
    }

    private void cargarProductos() {
        cboProducto.removeAllItems();
        cboProducto.addItem("-- Seleccione un producto --");
        
        ArrayList<Producto> productos = aprod.listarProductos();
        for (Producto prod : productos) {
            cboProducto.addItem(prod.getCodProd() + " - " + prod.getNombre());
        }
    }

    private void actualizarProductosProveedor() {
        String seleccion = (String) cboProveedor.getSelectedItem();
        if (seleccion != null && !seleccion.startsWith("--")) {
            String codProveedor = seleccion.split(" - ")[0];
            
            // Contar productos
            int total = app.contarProductosPorProveedor(codProveedor);
            lblProductosProveedor.setText("Productos: " + total);
            
            // Listar productos en tabla
            ArrayList<Producto> productos = app.listarProductosDeProveedor(codProveedor);
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Código");
            modelo.addColumn("Producto");
            modelo.addColumn("Marca");
            modelo.addColumn("Stock");
            
            for (Producto p : productos) {
                modelo.addRow(new Object[]{
                    p.getCodProd(),
                    p.getNombre(),
                    p.getMarca(),
                    p.getStock()
                });
            }
            tbProductosProveedor.setModel(modelo);
        } else {
            lblProductosProveedor.setText("Productos: 0");
            tbProductosProveedor.setModel(new DefaultTableModel());
        }
    }

    private void actualizarProveedoresProducto() {
        String seleccion = (String) cboProducto.getSelectedItem();
        if (seleccion != null && !seleccion.startsWith("--")) {
            String codProducto = seleccion.split(" - ")[0];
            
            // Contar proveedores
            int total = app.contarProveedoresPorProducto(codProducto);
            lblProveedoresProducto.setText("Proveedores: " + total);
            
            // Listar proveedores en tabla
            ArrayList<Proveedor> proveedores = app.listarProveedoresDeProducto(codProducto);
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Código");
            modelo.addColumn("Razón Social");
            modelo.addColumn("RUC");
            modelo.addColumn("Teléfono");
            
            for (Proveedor p : proveedores) {
                modelo.addRow(new Object[]{
                    p.getCodProveedor(),
                    p.getNombreProveedor(),
                    p.getRuc(),
                    p.getTelefono()
                });
            }
            tbProveedoresProducto.setModel(modelo);
        } else {
            lblProveedoresProducto.setText("Proveedores: 0");
            tbProveedoresProducto.setModel(new DefaultTableModel());
        }
    }

    private void agregarRelacion() {
        String selProv = (String) cboProveedor.getSelectedItem();
        String selProd = (String) cboProducto.getSelectedItem();
        String precio = txtPrecioSuministro.getText().trim();

        if (selProv == null || selProv.startsWith("--") || 
            selProd == null || selProd.startsWith("--") || precio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        String codProveedor = selProv.split(" - ")[0];
        String codProducto = selProd.split(" - ")[0];

        // Verificar si ya existe la relación
        if (app.existeRelacion(codProveedor, codProducto)) {
            JOptionPane.showMessageDialog(this, 
                "Esta relación ya existe entre el proveedor y el producto");
            return;
        }

        try {
            double precioSuministro = Double.parseDouble(precio);
            ProveedorProducto pp = new ProveedorProducto(codProveedor, codProducto, precioSuministro);
            app.insertar(pp);
            JOptionPane.showMessageDialog(this, "Relación agregada correctamente");
            
            refrescarTodo();
            limpiar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido");
        }
    }

    private void eliminarRelacion() {
        int fila = tbRelaciones.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una relación de la tabla");
            return;
        }

        String codProveedor = (String) tbRelaciones.getValueAt(fila, 1);
        String codProducto = (String) tbRelaciones.getValueAt(fila, 3);

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Eliminar esta relación?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            app.eliminar(codProveedor, codProducto);
            JOptionPane.showMessageDialog(this, "Relación eliminada correctamente");
            refrescarTodo();
        }
    }

    private void listarTodasRelaciones() {
        ArrayList<ProveedorProducto> lista = app.listarTodasRelaciones();
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID");
        modelo.addColumn("Cód. Proveedor");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Cód. Producto");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio Suministro");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        for (ProveedorProducto pp : lista) {
            modelo.addRow(new Object[]{
                pp.getIdRelacion(),
                pp.getCodProveedor(),
                pp.getNombreProveedor(),
                pp.getCodProducto(),
                pp.getNombreProducto(),
                String.format("S/ %.2f", pp.getPrecioSuministro()),
                pp.getFechaRegistro(),
                pp.getEstado()
            });
        }
        
        tbRelaciones.setModel(modelo);
        lblTotalRelaciones.setText("Total de relaciones: " + lista.size());
    }

    private void refrescarTodo() {
        listarTodasRelaciones();
        actualizarProductosProveedor();
        actualizarProveedoresProducto();
        cargarProveedores();
        cargarProductos();
    }

    private void limpiar() {
        cboProveedor.setSelectedIndex(0);
        cboProducto.setSelectedIndex(0);
        txtPrecioSuministro.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tbRelaciones) {
            int fila = tbRelaciones.getSelectedRow();
            String codProveedor = (String) tbRelaciones.getValueAt(fila, 1);
            String nombreProveedor = (String) tbRelaciones.getValueAt(fila, 2);
            String codProducto = (String) tbRelaciones.getValueAt(fila, 3);
            String nombreProducto = (String) tbRelaciones.getValueAt(fila, 4);
            
            cboProveedor.setSelectedItem(codProveedor + " - " + nombreProveedor);
            cboProducto.setSelectedItem(codProducto + " - " + nombreProducto);
            
            String precio = (String) tbRelaciones.getValueAt(fila, 5);
            txtPrecioSuministro.setText(precio.replace("S/ ", ""));
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                VentanaProveedorProducto frame = new VentanaProveedorProducto();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}