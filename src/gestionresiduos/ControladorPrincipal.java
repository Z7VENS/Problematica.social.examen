package gestionresiduos;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.util.HashMap;

// Clase que controla la lógica principal del sistema
class ControladorPrincipal {
    private Estadisticas estadisticas;
    private Usuario usuarioActual;
    private JFrame frame;

    // Constructor de la clase ControladorPrincipal
    public ControladorPrincipal() {
        estadisticas = new Estadisticas();
        usuarioActual = null;
    }

    public void iniciarSistema() {
        frame = new JFrame("Sistema de Gestión de Residuos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    
        JPanel panelInicioSesion = new JPanel(new GridBagLayout());
        panelInicioSesion.setBackground(new Color(245, 245, 245));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel lblTitulo = new JLabel("Inicio de Sesión");
        lblTitulo.setForeground(new Color(34, 167, 240));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelInicioSesion.add(lblTitulo, gbc);
    
        JLabel lblNombreUsuario = new JLabel("Nombre de usuario:");
        lblNombreUsuario.setForeground(new Color(34, 167, 240));
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInicioSesion.add(lblNombreUsuario, gbc);
    
        JTextField txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtNombreUsuario.setForeground(new Color(34, 167, 240));
        txtNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInicioSesion.add(txtNombreUsuario, gbc);
    
        JLabel lblCorreoUsuario = new JLabel("Correo electrónico:");
        lblCorreoUsuario.setForeground(new Color(34, 167, 240));
        lblCorreoUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblCorreoUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInicioSesion.add(lblCorreoUsuario, gbc);
    
        JTextField txtCorreoUsuario = new JTextField();
        txtCorreoUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtCorreoUsuario.setForeground(new Color(34, 167, 240));
        txtCorreoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInicioSesion.add(txtCorreoUsuario, gbc);
    
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBackground(new Color(34, 167, 240));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 18));
        btnIniciarSesion.addActionListener(e -> iniciarSesion(txtNombreUsuario.getText(), txtCorreoUsuario.getText()));
        panelInicioSesion.add(btnIniciarSesion, gbc);
    
        frame.add(panelInicioSesion, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Método para iniciar sesión
    private void iniciarSesion(String nombre, String correo) {
        if (!nombre.isEmpty() && !correo.isEmpty()) {
            usuarioActual = new Usuario(nombre, correo);
            mostrarInterfazPrincipal();
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
        }
    }

    // Método para mostrar la interfaz principal después de iniciar sesión
    private void mostrarInterfazPrincipal() {
        frame.getContentPane().removeAll();

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 245));

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 245));

        JButton btnRegistroPuntoAcopio = new JButton("Registrar Punto");
        JButton btnEducacionAmbiental = new JButton("Educación");
        JButton btnMostrarEstadisticas = new JButton("Mostrar Estadísticas");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        stylizeButton(btnRegistroPuntoAcopio);
        stylizeButton(btnEducacionAmbiental);
        stylizeButton(btnMostrarEstadisticas);
        stylizeButton(btnCerrarSesion);

        btnRegistroPuntoAcopio.addActionListener(e -> registrarPuntoAcopio());
        btnEducacionAmbiental.addActionListener(e -> mostrarEducacionAmbiental());
        btnMostrarEstadisticas.addActionListener(e -> mostrarEstadisticas());
        btnCerrarSesion.addActionListener(e -> retrocederInicioSesion());

        panelBotones.add(btnRegistroPuntoAcopio);
        panelBotones.add(btnEducacionAmbiental);
        panelBotones.add(btnMostrarEstadisticas);
        panelBotones.add(btnCerrarSesion);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Método para estilizar un botón
    private void stylizeButton(JButton button) {
        button.setBackground(new Color(34, 167, 240));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }

    // Método para retroceder al inicio de sesión
    private void retrocederInicioSesion() {
        usuarioActual = null;
        frame.getContentPane().removeAll();
        frame.repaint();
        iniciarSistema();
    }

    // Método para registrar un nuevo punto de acopio de reciclaje
    private void registrarPuntoAcopio() {
        JFrame inputFrame = new JFrame();
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputFrame.setSize(300, 150);
        inputFrame.setLayout(new BorderLayout());
        inputFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField ubicacionField = new JTextField();
        JTextField cantidadField = new JTextField();

        panel.add(new JLabel("Ingrese la ubicación del punto de acopio:"));
        panel.add(ubicacionField);
        panel.add(new JLabel("Ingrese la cantidad de reciclaje en kg:"));
        panel.add(cantidadField);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            String ubicacion = ubicacionField.getText();
            String cantidadStr = cantidadField.getText();
            if (!ubicacion.isEmpty() && !cantidadStr.isEmpty()) {
                try {
                    int cantidadReciclaje = Integer.parseInt(cantidadStr);
                    estadisticas.agregarPuntoAcopio(ubicacion, cantidadReciclaje);
                    usuarioActual.agregarPuntosReciclaje(cantidadReciclaje * 10);
                    JOptionPane.showMessageDialog(inputFrame, "Punto de acopio registrado exitosamente.");
                    inputFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(inputFrame, "La cantidad de reciclaje debe ser un número entero.");
                }
            } else {
                JOptionPane.showMessageDialog(inputFrame, "Debe ingresar una ubicación y una cantidad.");
            }
        });

        inputFrame.add(panel, BorderLayout.CENTER);
        inputFrame.add(btnRegistrar, BorderLayout.SOUTH);
        inputFrame.setVisible(true);
    }

    // Método para mostrar un recurso educativo sobre el medio ambiente
    private void mostrarEducacionAmbiental() {
        try {
            Desktop.getDesktop().browse(new URI("https://youtu.be/kJI0sloHVyU?si=GqZrhJs9FiFpRg7B"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error al abrir el enlace.");
        }
    }

    // Método para mostrar las estadísticas de los puntos de acopio y los puntos de reciclaje de los usuarios
    private void mostrarEstadisticas() {
        StringBuilder sb = new StringBuilder("Estadísticas de Puntos de Acopio:\n");
        HashMap<String, Integer> puntosAcopioReciclaje = estadisticas.getPuntosAcopioReciclaje();
        for (String ubicacion : puntosAcopioReciclaje.keySet()) {
            sb.append("- Ubicación: ").append(ubicacion).append(", Cantidad Reciclada: ").append(puntosAcopioReciclaje.get(ubicacion)).append(" kg\n");
        }
        sb.append("\n");
        if (usuarioActual != null) {
            sb.append("Sesión iniciada por: ").append(usuarioActual.getNombre()).append("\n");
            sb.append("Puntos ganados por reciclaje: ").append(usuarioActual.getPuntosReciclaje()).append("\n");
        } else {
            sb.append("Sesión iniciada por: Invitado\n");
        }

        JOptionPane.showMessageDialog(frame, sb.toString());
    }
}
// :]