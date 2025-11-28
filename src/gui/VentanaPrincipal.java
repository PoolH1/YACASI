package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Arreglo.ArregloVentas;
import Arreglo.ArrayProducto;
import Clase.Venta;
import Clase.Producto;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    
    // Componentes del menú principal
    private JPanel panelMenu;
    private JButton btnProductos, btnClientes, btnProveedores, btnVentas, btnReportes, btnSalir;
    private JLabel lblTitulo, lblUsuario;
    
    // Componentes de ventas
    private JPanel panelVentas;
    private JTextField txtId, txtNombre, txtCant;
    private JComboBox<String> cboProducto;
    private JTextField txtPrecio;
    private JTextArea txtS;
    private JButton btnAdicionar, btnBuscar, btnEliminar, btnModificar, btnListar;
    private JCheckBox chkUsarBD;
    private ArregloVentas av;
    private ArrayProducto ap;
    
    private String nombreUsuario;

    public VentanaPrincipal() {
        this("Usuario");
    }

    public VentanaPrincipal(String usuario) {
        super("Ferretería - Sistema de Gestión");
        this.nombreUsuario = usuario;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Inicializar arreglos
        av = new ArregloVentas(true);
        ap = new ArrayProducto();

        // Crear el menú principal
        crearMenuPrincipal();
        
        // Crear panel de ventas (oculto inicialmente)
        crearPanelVentas();
        
        // Mostrar menú al inicio
        mostrarMenu();
    }

    private void crearMenuPrincipal() {
        panelMenu = new JPanel();
        panelMenu.setBounds(0, 0, 900, 650);
        panelMenu.setLayout(null);
        panelMenu.setBackground(new Color(240, 248, 255));
        add(panelMenu);

        // Título principal
        lblTitulo = new JLabel("SISTEMA DE GESTIÓN - FERRETERÍA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(41, 128, 185));
        lblTitulo.setBounds(0, 30, 900, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelMenu.add(lblTitulo);

        // Usuario logueado
        lblUsuario = new JLabel("Usuario: " + nombreUsuario);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsuario.setBounds(700, 10, 180, 20);
        panelMenu.add(lblUsuario);

        // Botón Productos
        btnProductos = new JButton("GESTIÓN DE PRODUCTOS");
        btnProductos.setFont(new Font("Arial", Font.BOLD, 16));
        btnProductos.setBounds(300, 120, 300, 60);
        btnProductos.setBackground(new Color(52, 152, 219));
        btnProductos.setForeground(Color.BLACK);
        btnProductos.setFocusPainted(false);
        btnProductos.addActionListener(this);
        panelMenu.add(btnProductos);

        // Botón Clientes
        btnClientes = new JButton("GESTIÓN DE CLIENTES");
        btnClientes.setFont(new Font("Arial", Font.BOLD, 16));
        btnClientes.setBounds(300, 200, 300, 60);
        btnClientes.setBackground(new Color(46, 204, 113));
        btnClientes.setForeground(Color.BLACK);
        btnClientes.setFocusPainted(false);
        btnClientes.addActionListener(this);
        panelMenu.add(btnClientes);

        // Botón Proveedores
        btnProveedores = new JButton("GESTIÓN DE PROVEEDORES");
        btnProveedores.setFont(new Font("Arial", Font.BOLD, 16));
        btnProveedores.setBounds(300, 280, 300, 60);
        btnProveedores.setBackground(new Color(155, 89, 182));
        btnProveedores.setForeground(Color.BLACK);
        btnProveedores.setFocusPainted(false);
        btnProveedores.addActionListener(this);
        panelMenu.add(btnProveedores);

        // Botón Ventas
        btnVentas = new JButton("REGISTRO DE VENTAS");
        btnVentas.setFont(new Font("Arial", Font.BOLD, 16));
        btnVentas.setBounds(300, 360, 300, 60);
        btnVentas.setBackground(new Color(230, 126, 34));
        btnVentas.setForeground(Color.BLACK);
        btnVentas.setFocusPainted(false);
        btnVentas.addActionListener(this);
        panelMenu.add(btnVentas);

        // Botón Reportes
        btnReportes = new JButton("REPORTES Y CONSULTAS");
        btnReportes.setFont(new Font("Arial", Font.BOLD, 16));
        btnReportes.setBounds(300, 440, 300, 60);
        btnReportes.setBackground(new Color(241, 196, 15));
        btnReportes.setForeground(Color.BLACK);
        btnReportes.setFocusPainted(false);
        btnReportes.addActionListener(this);
        panelMenu.add(btnReportes);

        // Botón Salir
        btnSalir = new JButton("CERRAR SESIÓN");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setBounds(350, 530, 200, 40);
        btnSalir.setBackground(new Color(231, 76, 60));
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(this);
        panelMenu.add(btnSalir);

        // Información adicional
        JLabel lblInfo = new JLabel("Bienvenido al Sistema de Gestión de Ferretería");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        lblInfo.setBounds(0, 590, 900, 20);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        panelMenu.add(lblInfo);
    }

    private void crearPanelVentas() {
        panelVentas = new JPanel();
        panelVentas.setBounds(0, 0, 900, 650);
        panelVentas.setLayout(null);
        add(panelVentas);
        panelVentas.setVisible(false);

        // Botón Volver al Menú
        JButton btnVolver = new JButton("← Volver al Menú");
        btnVolver.setBounds(10, 10, 150, 30);
        btnVolver.setBackground(new Color(52, 73, 94));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> mostrarMenu());
        panelVentas.add(btnVolver);

        // Título
        JLabel lblTituloVentas = new JLabel("REGISTRO DE VENTAS");
        lblTituloVentas.setFont(new Font("Arial", Font.BOLD, 20));
        lblTituloVentas.setBounds(0, 10, 900, 30);
        lblTituloVentas.setHorizontalAlignment(SwingConstants.CENTER);
        panelVentas.add(lblTituloVentas);

        // Panel superior con checkbox
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBounds(10, 50, 870, 30);
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelVentas.add(panelSuperior);

        chkUsarBD = new JCheckBox("Usar Base de Datos", true);
        chkUsarBD.addActionListener(this);
        panelSuperior.add(chkUsarBD);

        // Campos de entrada
        JLabel lblId = new JLabel("ID Venta:");
        lblId.setBounds(20, 100, 70, 25);
        panelVentas.add(lblId);
        txtId = new JTextField();
        txtId.setBounds(100, 100, 80, 25);
        panelVentas.add(txtId);

        JLabel lblNombre = new JLabel("Cliente:");
        lblNombre.setBounds(200, 100, 70, 25);
        panelVentas.add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(270, 100, 180, 25);
        panelVentas.add(txtNombre);

        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(20, 140, 70, 25);
        panelVentas.add(lblProducto);
        cboProducto = new JComboBox<>();
        cboProducto.setBounds(100, 140, 250, 25);
        cboProducto.addActionListener(this);
        panelVentas.add(cboProducto);

        JButton btnRefrescar = new JButton("↻");
        btnRefrescar.setBounds(355, 140, 45, 25);
        btnRefrescar.addActionListener(e -> cargarProductos());
        panelVentas.add(btnRefrescar);

        JLabel lblCant = new JLabel("Cantidad:");
        lblCant.setBounds(420, 140, 70, 25);
        panelVentas.add(lblCant);
        txtCant = new JTextField();
        txtCant.setBounds(490, 140, 80, 25);
        panelVentas.add(txtCant);

        JLabel lblPrecio = new JLabel("Precio Unit:");
        lblPrecio.setBounds(590, 140, 80, 25);
        panelVentas.add(lblPrecio);
        txtPrecio = new JTextField();
        txtPrecio.setEditable(false);
        txtPrecio.setBounds(675, 140, 100, 25);
        panelVentas.add(txtPrecio);

        // Botones de operaciones
        btnAdicionar = new JButton("Registrar Venta");
        btnAdicionar.setBounds(20, 190, 140, 30);
        btnAdicionar.setBackground(new Color(46, 204, 113));
        btnAdicionar.setForeground(Color.BLACK);
        btnAdicionar.addActionListener(this);
        panelVentas.add(btnAdicionar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(170, 190, 100, 30);
        btnBuscar.addActionListener(this);
        panelVentas.add(btnBuscar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(280, 190, 100, 30);
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.addActionListener(this);
        panelVentas.add(btnEliminar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(390, 190, 100, 30);
        btnModificar.addActionListener(this);
        panelVentas.add(btnModificar);

        btnListar = new JButton("Listar Todo");
        btnListar.setBounds(500, 190, 120, 30);
        btnListar.setBackground(new Color(52, 152, 219));
        btnListar.setForeground(Color.BLACK);
        btnListar.addActionListener(this);
        panelVentas.add(btnListar);

        // Área de texto
        txtS = new JTextArea();
        txtS.setEditable(false);
        txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtS);
        scroll.setBounds(20, 240, 850, 360);
        panelVentas.add(scroll);

        cargarProductos();
    }

    private void mostrarMenu() {
        panelMenu.setVisible(true);
        panelVentas.setVisible(false);
    }

    private void mostrarVentas() {
        panelMenu.setVisible(false);
        panelVentas.setVisible(true);
        reportarVentas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Botones del menú principal
        if (e.getSource() == btnProductos) {
            VentanaProductos vp = new VentanaProductos();
            vp.setVisible(true);
        } else if (e.getSource() == btnClientes) {
            VentanaClientes vc = new VentanaClientes();
            vc.setVisible(true);
        } else if (e.getSource() == btnProveedores) {
            String[] opciones = {"Gestión de Proveedores", "Relación Proveedor-Producto"};
            int seleccion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción:",
                "Gestión de Proveedores",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            if (seleccion == 0) {
                VentanaProveedores vpr = new VentanaProveedores();
                vpr.setVisible(true);
            } else if (seleccion == 1) {
                VentanaProveedorProducto vpp = new VentanaProveedorProducto();
                vpp.setVisible(true);
            }
        } else if (e.getSource() == btnVentas) {
            VentanaVentas vv = new VentanaVentas();
            vv.setVisible(true);
        } else if (e.getSource() == btnReportes) {
            mostrarReportes();
        } else if (e.getSource() == btnSalir) {
            cerrarSesion();
        }
        // Operaciones de ventas
        else if (e.getSource() == chkUsarBD) {
            av.setUsarBD(chkUsarBD.isSelected());
            String msg = chkUsarBD.isSelected() ? 
                "Ahora usando Base de Datos" : "Ahora usando memoria local";
            JOptionPane.showMessageDialog(this, msg);
        } else if (e.getSource() == cboProducto) {
            actualizarPrecio();
        } else if (e.getSource() == btnAdicionar) {
            adicionarVenta();
        } else if (e.getSource() == btnBuscar) {
            buscarVenta();
        } else if (e.getSource() == btnEliminar) {
            eliminarVenta();
        } else if (e.getSource() == btnModificar) {
            modificarVenta();
        } else if (e.getSource() == btnListar) {
            reportarVentas();
        }
    }

    private void mostrarReportes() {
        JOptionPane.showMessageDialog(this, 
            "Módulo de Reportes - En desarrollo\n\n" +
            "Próximamente:\n" +
            "• Reporte de ventas por fecha\n" +
            "• Productos más vendidos\n" +
            "• Clientes frecuentes\n" +
            "• Stock bajo\n" +
            "• Exportar a Excel/PDF", 
            "Reportes y Consultas", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea cerrar sesión?",
            "Cerrar Sesión",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            VentanaLogin login = new VentanaLogin();
            login.setVisible(true);
        }
    }

    private void cargarProductos() {
        cboProducto.removeAllItems();
        cboProducto.addItem("-- Seleccione un producto --");
        
        ArrayList<Producto> productos = ap.listarProductos();
        for (Producto p : productos) {
            cboProducto.addItem(p.getCodProd() + " - " + p.getNombre());
        }
    }

    private void actualizarPrecio() {
        String seleccion = (String) cboProducto.getSelectedItem();
        if (seleccion != null && !seleccion.startsWith("--")) {
            String codigo = seleccion.split(" - ")[0];
            ArrayList<Producto> lista = ap.consultarPorCodigo(codigo);
            if (!lista.isEmpty()) {
                txtPrecio.setText(String.valueOf(lista.get(0).getPrecio()));
            }
        } else {
            txtPrecio.setText("");
        }
    }

    private void adicionarVenta() {
        String sId = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String seleccion = (String) cboProducto.getSelectedItem();
        String sCant = txtCant.getText().trim();
        String sPreuni = txtPrecio.getText().trim();

        if (sId.isEmpty() || nombre.isEmpty() || seleccion == null || 
            seleccion.startsWith("--") || sCant.isEmpty() || sPreuni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        int id, cant;
        double preuni;
        try {
            id = Integer.parseInt(sId);
            cant = Integer.parseInt(sCant);
            preuni = Double.parseDouble(sPreuni);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID, Cantidad y Precio deben ser numéricos válidos");
            return;
        }

        Venta vExistente = av.buscar(id);
        if (vExistente != null) {
            JOptionPane.showMessageDialog(this, "Ya existe una venta con el ID: " + id);
            return;
        }

        String codigo = seleccion.split(" - ")[0];
        String nombreProd = seleccion.split(" - ")[1];

        ArrayList<Producto> listaProd = ap.consultarPorCodigo(codigo);
        if (!listaProd.isEmpty()) {
            Producto prod = listaProd.get(0);
            if (prod.getStock() < cant) {
                JOptionPane.showMessageDialog(this, 
                    "Stock insuficiente. Stock disponible: " + prod.getStock());
                return;
            }
            ap.actualizarStock(codigo, prod.getStock() - cant);
        }

        Venta v = new Venta(id, nombre, codigo, nombreProd, cant, preuni, new java.util.Date());
        av.adicionar(v);
        JOptionPane.showMessageDialog(this, "Venta registrada correctamente");

        reportarVentas();
        limpiarCampos();
    }

    private void buscarVenta() {
        String s = txtId.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID a buscar");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido");
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id);
        } else {
            txtS.setText("Venta encontrada:\n" + 
                         "ID: " + v.getIden() + "\n" +
                         "Cliente: " + v.getNombreCliente() + "\n" +
                         "Producto: " + v.getNombreProd() + "\n" +
                         "Cantidad: " + v.getCant() + "\n" +
                         "Precio: " + v.getPreuni() + "\n" +
                         "Total: " + v.total());
        }
    }

    private void eliminarVenta() {
        String s = txtId.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID a eliminar");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido");
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id);
            return;
        }

        int r = JOptionPane.showConfirmDialog(this, "¿Eliminar esta venta?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            av.eliminar(v);
            JOptionPane.showMessageDialog(this, "Venta eliminada correctamente");
            reportarVentas();
            limpiarCampos();
        }
    }

    private void modificarVenta() {
        JOptionPane.showMessageDialog(this, "Función en desarrollo");
    }

    private void reportarVentas() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-8s %-20s %-20s %-8s %-10s %-10s\n", 
            "ID", "Cliente", "Producto", "Cant", "Precio", "Total"));
        sb.append("=".repeat(80)).append("\n");
        
        ArrayList<Venta> ventas = av.listarVentas();
        for (Venta v : ventas) {
            sb.append(String.format("%-8d %-20s %-20s %-8d %-10.2f %-10.2f\n",
                v.getIden(),
                v.getNombreCliente(),
                v.getNombreProd(),
                v.getCant(),
                v.getPreuni(),
                v.total()));
        }
        txtS.setText(sb.toString());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        cboProducto.setSelectedIndex(0);
        txtCant.setText("");
        txtPrecio.setText("");
        txtId.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            vp.setVisible(true);
        });
    }
}