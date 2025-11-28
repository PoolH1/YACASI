package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Arreglo.ArregloVentas;
import Arreglo.ArrayProducto;
import Clase.Venta;
import Clase.Producto;

public class VentanaVentas extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    
    // Componentes de entrada
    private JTextField txtIdVenta, txtCliente, txtCantidad, txtPrecioUnitario;
    private JComboBox<String> cboProducto;
    private JCheckBox chkUsarBD;
    
    // Tabla
    private JTable table;
    private DefaultTableModel model;
    
    // Botones
    private JButton btnRegistrar, btnBuscar, btnEliminar, btnModificar, btnListar, btnLimpiar, btnRefrescarProductos;
    
    // Arreglos
    private ArregloVentas av;
    private ArrayProducto ap;

    public VentanaVentas() {
        super("Gestión de Ventas - Ferretería");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Inicializar arreglos
        av = new ArregloVentas(true);
        ap = new ArrayProducto();
        
        crearComponentes();
        cargarProductos();
        listarVentas();
    }

    private void crearComponentes() {
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBounds(0, 0, 1200, 60);
        panelTitulo.setBackground(new Color(230, 126, 34));
        panelTitulo.setLayout(null);
        add(panelTitulo);

        JLabel lblTitulo = new JLabel("REGISTRO DE VENTAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 15, 1200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo.add(lblTitulo);

        // Checkbox para usar BD
        chkUsarBD = new JCheckBox("Usar Base de Datos", true);
        chkUsarBD.setBounds(20, 70, 180, 25);
        chkUsarBD.setFont(new Font("Arial", Font.PLAIN, 12));
        chkUsarBD.addActionListener(this);
        add(chkUsarBD);

        // ===== PANEL DE FORMULARIO =====
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBounds(20, 105, 1160, 120);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(230, 126, 34), 2),
            "Datos de la Venta",
            0, 0, new Font("Arial", Font.BOLD, 14), new Color(230, 126, 34)
        ));
        panelFormulario.setLayout(null);
        add(panelFormulario);

        // Fila 1: ID Venta y Cliente
        JLabel lblIdVenta = new JLabel("ID Venta:");
        lblIdVenta.setBounds(20, 30, 100, 25);
        lblIdVenta.setFont(new Font("Arial", Font.BOLD, 12));
        panelFormulario.add(lblIdVenta);

        txtIdVenta = new JTextField();
        txtIdVenta.setBounds(120, 30, 120, 25);
        txtIdVenta.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(txtIdVenta);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(270, 30, 100, 25);
        lblCliente.setFont(new Font("Arial", Font.BOLD, 12));
        panelFormulario.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(370, 30, 300, 25);
        txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(txtCliente);

        // Fila 2: Producto
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(20, 70, 100, 25);
        lblProducto.setFont(new Font("Arial", Font.BOLD, 12));
        panelFormulario.add(lblProducto);

        cboProducto = new JComboBox<>();
        cboProducto.setBounds(120, 70, 400, 25);
        cboProducto.setFont(new Font("Arial", Font.PLAIN, 12));
        cboProducto.addActionListener(this);
        panelFormulario.add(cboProducto);

        btnRefrescarProductos = new JButton("↻");
        btnRefrescarProductos.setBounds(530, 70, 45, 25);
        btnRefrescarProductos.setFont(new Font("Arial", Font.BOLD, 14));
        btnRefrescarProductos.setToolTipText("Refrescar lista de productos");
        btnRefrescarProductos.addActionListener(this);
        panelFormulario.add(btnRefrescarProductos);

        // Cantidad
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(600, 70, 80, 25);
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 12));
        panelFormulario.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(690, 70, 100, 25);
        txtCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(txtCantidad);

        // Precio Unitario
        JLabel lblPrecio = new JLabel("Precio Unit.:");
        lblPrecio.setBounds(820, 70, 90, 25);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 12));
        panelFormulario.add(lblPrecio);

        txtPrecioUnitario = new JTextField();
        txtPrecioUnitario.setBounds(920, 70, 120, 25);
        txtPrecioUnitario.setFont(new Font("Arial", Font.PLAIN, 12));
        txtPrecioUnitario.setEditable(false);
        txtPrecioUnitario.setBackground(new Color(240, 240, 240));
        panelFormulario.add(txtPrecioUnitario);

        // ===== PANEL DE BOTONES =====
        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(20, 235, 1160, 50);
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        add(panelBotones);

        btnRegistrar = new JButton("Registrar Venta");
        btnRegistrar.setPreferredSize(new Dimension(160, 35));
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.addActionListener(this);
        panelBotones.add(btnRegistrar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(120, 35));
        btnBuscar.setBackground(new Color(52, 152, 219));
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(this);
        panelBotones.add(btnBuscar);

        btnModificar = new JButton("Modificar");
        btnModificar.setPreferredSize(new Dimension(120, 35));
        btnModificar.setBackground(new Color(241, 196, 15));
        btnModificar.setForeground(Color.BLACK);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
        btnModificar.setFocusPainted(false);
        btnModificar.addActionListener(this);
        panelBotones.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setPreferredSize(new Dimension(120, 35));
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(this);
        panelBotones.add(btnEliminar);

        btnListar = new JButton("Listar Todo");
        btnListar.setPreferredSize(new Dimension(120, 35));
        btnListar.setBackground(new Color(155, 89, 182));
        btnListar.setForeground(Color.BLACK);
        btnListar.setFont(new Font("Arial", Font.BOLD, 12));
        btnListar.setFocusPainted(false);
        btnListar.addActionListener(this);
        panelBotones.add(btnListar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(120, 35));
        btnLimpiar.setBackground(new Color(149, 165, 166));
        btnLimpiar.setForeground(Color.BLACK);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(this);
        panelBotones.add(btnLimpiar);

        // ===== TABLA =====
        String[] columnas = {"ID Venta", "Cliente", "Código Prod.", "Producto", "Cantidad", "Precio Unit.", "Total", "Fecha"};
        model = new DefaultTableModel(columnas, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.setForeground(Color.BLACK);
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        table.getTableHeader().setBackground(new Color(230, 126, 34));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setOpaque(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(255, 200, 150));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(200, 200, 200));
        
        // Ajustar ancho de columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(70);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Cliente
        table.getColumnModel().getColumn(2).setPreferredWidth(90);  // Código
        table.getColumnModel().getColumn(3).setPreferredWidth(200); // Producto
        table.getColumnModel().getColumn(4).setPreferredWidth(80);  // Cantidad
        table.getColumnModel().getColumn(5).setPreferredWidth(90);  // Precio
        table.getColumnModel().getColumn(6).setPreferredWidth(90);  // Total
        table.getColumnModel().getColumn(7).setPreferredWidth(150); // Fecha
        
        // Listener para seleccionar fila
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    seleccionarFila();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 295, 1160, 350);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);
    }

    private void cargarProductos() {
        cboProducto.removeAllItems();
        cboProducto.addItem("-- Seleccione un producto --");
        
        ArrayList<Producto> productos = ap.listarProductos();
        for (Producto p : productos) {
            cboProducto.addItem(p.getCodProd() + " - " + p.getNombre() + " (Stock: " + p.getStock() + ")");
        }
    }

    private void actualizarPrecio() {
        String seleccion = (String) cboProducto.getSelectedItem();
        if (seleccion != null && !seleccion.startsWith("--")) {
            String codigo = seleccion.split(" - ")[0];
            ArrayList<Producto> lista = ap.consultarPorCodigo(codigo);
            if (!lista.isEmpty()) {
                txtPrecioUnitario.setText(String.format("%.2f", lista.get(0).getPrecio()));
            }
        } else {
            txtPrecioUnitario.setText("");
        }
    }

    private void registrarVenta() {
        String sId = txtIdVenta.getText().trim();
        String cliente = txtCliente.getText().trim();
        String seleccion = (String) cboProducto.getSelectedItem();
        String sCant = txtCantidad.getText().trim();
        String sPrecio = txtPrecioUnitario.getText().trim();

        if (sId.isEmpty() || cliente.isEmpty() || seleccion == null || 
            seleccion.startsWith("--") || sCant.isEmpty() || sPrecio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id, cant;
        double precio;
        try {
            id = Integer.parseInt(sId);
            cant = Integer.parseInt(sCant);
            precio = Double.parseDouble(sPrecio);
            
            if (cant <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID, Cantidad y Precio deben ser numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Venta vExistente = av.buscar(id);
        if (vExistente != null) {
            JOptionPane.showMessageDialog(this, "Ya existe una venta con el ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigo = seleccion.split(" - ")[0];
        String nombreProd = seleccion.split(" - ")[1].split(" \\(Stock:")[0];

        ArrayList<Producto> listaProd = ap.consultarPorCodigo(codigo);
        if (!listaProd.isEmpty()) {
            Producto prod = listaProd.get(0);
            if (prod.getStock() < cant) {
                JOptionPane.showMessageDialog(this, 
                    "Stock insuficiente. Stock disponible: " + prod.getStock(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ap.actualizarStock(codigo, prod.getStock() - cant);
        }

        Venta v = new Venta(id, cliente, codigo, nombreProd, cant, precio, new Date());
        av.adicionar(v);
        JOptionPane.showMessageDialog(this, 
            "Venta registrada correctamente\nTotal: S/ " + String.format("%.2f", v.total()), 
            "Éxito", JOptionPane.INFORMATION_MESSAGE);

        listarVentas();
        limpiarCampos();
        cargarProductos(); // Actualizar stock en combo
    }

    private void buscarVenta() {
        String s = txtIdVenta.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID a buscar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id, "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarVentaEnFormulario(v);
            // Buscar en la tabla y seleccionar
            for (int i = 0; i < table.getRowCount(); i++) {
                if (Integer.parseInt(table.getValueAt(i, 0).toString()) == id) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    break;
                }
            }
        }
    }

    private void eliminarVenta() {
        String s = txtIdVenta.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID a eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int r = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar esta venta?\n\n" +
            "Cliente: " + v.getNombreCliente() + "\n" +
            "Producto: " + v.getNombreProd() + "\n" +
            "Total: S/ " + String.format("%.2f", v.total()),
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (r == JOptionPane.YES_OPTION) {
            av.eliminar(v);
            JOptionPane.showMessageDialog(this, "Venta eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            listarVentas();
            limpiarCampos();
        }
    }

    private void modificarVenta() {
        String sId = txtIdVenta.getText().trim();
        if (sId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una venta a modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(sId);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Venta vExistente = av.buscar(id);
        if (vExistente == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cliente = txtCliente.getText().trim();
        String seleccion = (String) cboProducto.getSelectedItem();
        String sCant = txtCantidad.getText().trim();
        String sPrecio = txtPrecioUnitario.getText().trim();

        if (cliente.isEmpty() || seleccion == null || seleccion.startsWith("--") || sCant.isEmpty() || sPrecio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cant;
        double precio;
        try {
            cant = Integer.parseInt(sCant);
            precio = Double.parseDouble(sPrecio);
            
            if (cant <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad y Precio deben ser numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigo = seleccion.split(" - ")[0];
        String nombreProd = seleccion.split(" - ")[1].split(" \\(Stock:")[0];

        vExistente.setNombreCliente(cliente);
        vExistente.setCodProd(codigo);
        vExistente.setNombreProd(nombreProd);
        vExistente.setCant(cant);
        vExistente.setPreuni(precio);

        JOptionPane.showMessageDialog(this, "Venta modificada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        listarVentas();
        limpiarCampos();
    }

    private void listarVentas() {
        model.setRowCount(0);
        ArrayList<Venta> ventas = av.listarVentas();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (Venta v : ventas) {
            Object[] fila = {
                v.getIden(),
                v.getNombreCliente(),
                v.getCodProd(),
                v.getNombreProd(),
                v.getCant(),
                String.format("S/ %.2f", v.getPreuni()),
                String.format("S/ %.2f", v.total()),
                sdf.format(v.getFecha())
            };
            model.addRow(fila);
        }
    }

    private void seleccionarFila() {
        int fila = table.getSelectedRow();
        if (fila != -1) {
            txtIdVenta.setText(table.getValueAt(fila, 0).toString());
            txtCliente.setText(table.getValueAt(fila, 1).toString());
            
            String codProd = table.getValueAt(fila, 2).toString();
            // Seleccionar el producto en el combo
            for (int i = 0; i < cboProducto.getItemCount(); i++) {
                String item = cboProducto.getItemAt(i);
                if (item.startsWith(codProd + " -")) {
                    cboProducto.setSelectedIndex(i);
                    break;
                }
            }
            
            txtCantidad.setText(table.getValueAt(fila, 4).toString());
            String precio = table.getValueAt(fila, 5).toString().replace("S/ ", "");
            txtPrecioUnitario.setText(precio);
        }
    }

    private void mostrarVentaEnFormulario(Venta v) {
        txtIdVenta.setText(String.valueOf(v.getIden()));
        txtCliente.setText(v.getNombreCliente());
        txtCantidad.setText(String.valueOf(v.getCant()));
        txtPrecioUnitario.setText(String.format("%.2f", v.getPreuni()));
        
        // Seleccionar el producto
        for (int i = 0; i < cboProducto.getItemCount(); i++) {
            String item = cboProducto.getItemAt(i);
            if (item.startsWith(v.getCodProd() + " -")) {
                cboProducto.setSelectedIndex(i);
                break;
            }
        }
    }

    private void limpiarCampos() {
        txtIdVenta.setText("");
        txtCliente.setText("");
        cboProducto.setSelectedIndex(0);
        txtCantidad.setText("");
        txtPrecioUnitario.setText("");
        table.clearSelection();
        txtIdVenta.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkUsarBD) {
            av.setUsarBD(chkUsarBD.isSelected());
            String msg = chkUsarBD.isSelected() ? 
                "Ahora usando Base de Datos" : "Ahora usando memoria local";
            JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
            listarVentas();
        } else if (e.getSource() == cboProducto) {
            actualizarPrecio();
        } else if (e.getSource() == btnRefrescarProductos) {
            cargarProductos();
        } else if (e.getSource() == btnRegistrar) {
            registrarVenta();
        } else if (e.getSource() == btnBuscar) {
            buscarVenta();
        } else if (e.getSource() == btnEliminar) {
            eliminarVenta();
        } else if (e.getSource() == btnModificar) {
            modificarVenta();
        } else if (e.getSource() == btnListar) {
            listarVentas();
        } else if (e.getSource() == btnLimpiar) {
            limpiarCampos();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaVentas vv = new VentanaVentas();
            vv.setVisible(true);
        });
    }
}