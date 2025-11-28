package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class VentanaLogin extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar, btnRegistrar;
    private JLabel lblTitulo, lblUsuario, lblPassword;
    
    private static HashMap<String, String> usuarios = new HashMap<>();
    
    static {
        usuarios.put("admin", "admin123");
    }
    
    public VentanaLogin() {
        super("Ferretería - Sistema de Acceso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(240, 240, 240));
        
        // Título
        lblTitulo = new JLabel("SISTEMA DE FERRETERÍA");
        lblTitulo.setBounds(0, 30, 400, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(41, 128, 185));
        add(lblTitulo);
        
        // Usuario
        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(60, 90, 80, 25);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 90, 180, 30);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        add(txtUsuario);
        
        // Contraseña
        lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(60, 130, 80, 25);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 130, 180, 30);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        add(txtPassword);
        
        // Botón Ingresar
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(80, 190, 120, 35);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIngresar.setBackground(new Color(46, 204, 113));
        btnIngresar.setForeground(Color.BLACK);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(true);
        btnIngresar.setOpaque(true);
        btnIngresar.addActionListener(this);
        add(btnIngresar);
        
        // Botón Registrar
        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(210, 190, 120, 35);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(52, 152, 219));
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(true);
        btnRegistrar.setOpaque(true);
        btnRegistrar.addActionListener(this);
        add(btnRegistrar);
        
        // Enter para ingresar
        txtPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    validarIngreso();
                }
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            validarIngreso();
        } else if (e.getSource() == btnRegistrar) {
            mostrarVentanaRegistro();
        }
    }
    
    private void validarIngreso() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete todos los campos", 
                "Campos vacíos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (usuarios.containsKey(usuario)) {
            if (usuarios.get(usuario).equals(password)) {
                JOptionPane.showMessageDialog(this, 
                    "¡Bienvenido " + usuario + "!", 
                    "Acceso exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Cerrar ventana de login
                this.dispose();
                
                // Abrir ventana principal pasando el nombre de usuario
                SwingUtilities.invokeLater(() -> {
                    VentanaPrincipal vp = new VentanaPrincipal(usuario);
                    vp.setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Contraseña incorrecta", 
                    "Error de acceso", 
                    JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                txtPassword.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Usuario no registrado", 
                "Error de acceso", 
                JOptionPane.ERROR_MESSAGE);
            txtUsuario.setText("");
            txtPassword.setText("");
            txtUsuario.requestFocus();
        }
    }
    
    private void mostrarVentanaRegistro() {
        JDialog dialogRegistro = new JDialog(this, "Registro de Usuario", true);
        dialogRegistro.setSize(380, 280);
        dialogRegistro.setLayout(null);
        dialogRegistro.setLocationRelativeTo(this);
        dialogRegistro.setResizable(false);
        dialogRegistro.getContentPane().setBackground(new Color(240, 240, 240));
        
        // Título
        JLabel lblTituloReg = new JLabel("CREAR NUEVA CUENTA");
        lblTituloReg.setBounds(0, 20, 380, 25);
        lblTituloReg.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloReg.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloReg.setForeground(new Color(52, 152, 219));
        dialogRegistro.add(lblTituloReg);
        
        // Usuario
        JLabel lblNuevoUsuario = new JLabel("Usuario:");
        lblNuevoUsuario.setBounds(40, 70, 100, 25);
        lblNuevoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogRegistro.add(lblNuevoUsuario);
        
        JTextField txtNuevoUsuario = new JTextField();
        txtNuevoUsuario.setBounds(150, 70, 180, 30);
        txtNuevoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogRegistro.add(txtNuevoUsuario);
        
        // Contraseña
        JLabel lblNuevaPassword = new JLabel("Contraseña:");
        lblNuevaPassword.setBounds(40, 110, 100, 25);
        lblNuevaPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogRegistro.add(lblNuevaPassword);
        
        JPasswordField txtNuevaPassword = new JPasswordField();
        txtNuevaPassword.setBounds(150, 110, 180, 30);
        txtNuevaPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogRegistro.add(txtNuevaPassword);
        
        // Confirmar contraseña
        JLabel lblConfirmarPassword = new JLabel("Confirmar:");
        lblConfirmarPassword.setBounds(40, 150, 100, 25);
        lblConfirmarPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogRegistro.add(lblConfirmarPassword);
        
        JPasswordField txtConfirmarPassword = new JPasswordField();
        txtConfirmarPassword.setBounds(150, 150, 180, 30);
        txtConfirmarPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogRegistro.add(txtConfirmarPassword);
        
        // Botón Registrar
        JButton btnGuardarRegistro = new JButton("Registrar");
        btnGuardarRegistro.setBounds(80, 200, 100, 30);
        btnGuardarRegistro.setFont(new Font("Arial", Font.BOLD, 13));
        btnGuardarRegistro.setBackground(new Color(46, 204, 113));
        btnGuardarRegistro.setForeground(Color.BLACK);
        btnGuardarRegistro.setFocusPainted(false);
        btnGuardarRegistro.setBorderPainted(true);
        btnGuardarRegistro.setOpaque(true);
        dialogRegistro.add(btnGuardarRegistro);
        
        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(190, 200, 100, 30);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(true);
        btnCancelar.setOpaque(true);
        dialogRegistro.add(btnCancelar);
        
        btnGuardarRegistro.addActionListener(e -> {
            String nuevoUsuario = txtNuevoUsuario.getText().trim();
            String nuevaPassword = new String(txtNuevaPassword.getPassword());
            String confirmarPassword = new String(txtConfirmarPassword.getPassword());
            
            if (nuevoUsuario.isEmpty() || nuevaPassword.isEmpty() || confirmarPassword.isEmpty()) {
                JOptionPane.showMessageDialog(dialogRegistro, 
                    "Por favor complete todos los campos", 
                    "Campos vacíos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (nuevoUsuario.length() < 4) {
                JOptionPane.showMessageDialog(dialogRegistro, 
                    "El usuario debe tener al menos 4 caracteres", 
                    "Usuario inválido", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (nuevaPassword.length() < 6) {
                JOptionPane.showMessageDialog(dialogRegistro, 
                    "La contraseña debe tener al menos 6 caracteres", 
                    "Contraseña inválida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!nuevaPassword.equals(confirmarPassword)) {
                JOptionPane.showMessageDialog(dialogRegistro, 
                    "Las contraseñas no coinciden", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                txtNuevaPassword.setText("");
                txtConfirmarPassword.setText("");
                return;
            }
            
            if (usuarios.containsKey(nuevoUsuario)) {
                JOptionPane.showMessageDialog(dialogRegistro, 
                    "El usuario ya existe, elija otro nombre", 
                    "Usuario existente", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            usuarios.put(nuevoUsuario, nuevaPassword);
            JOptionPane.showMessageDialog(dialogRegistro, 
                "Usuario registrado exitosamente\nYa puede iniciar sesión", 
                "Registro exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            dialogRegistro.dispose();
        });
        
        btnCancelar.addActionListener(e -> dialogRegistro.dispose());
        
        dialogRegistro.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin login = new VentanaLogin();
            login.setVisible(true);
        });
    }
}