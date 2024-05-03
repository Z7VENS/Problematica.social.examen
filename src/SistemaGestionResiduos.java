

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.HashMap;

public class SistemaGestionResiduos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorPrincipal controlador = new ControladorPrincipal();
            controlador.iniciarSistema();
        });
    }
}

class Usuario {
    private String nombre;
    private String correo;

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}

class PuntoAcopio {
    private String ubicacion;
    private int cantidadReciclaje;

    public PuntoAcopio(String ubicacion, int cantidadReciclaje) {
        this.ubicacion = ubicacion;
        this.cantidadReciclaje = cantidadReciclaje;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCantidadReciclaje() {
        return cantidadReciclaje;
    }
}

class Recolector {
    private String ruta;

    public Recolector(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
}

class Estadisticas {
    private HashMap<String, Integer> puntosAcopioReciclaje;

    public Estadisticas() {
        puntosAcopioReciclaje = new HashMap<>();
    }

    public void agregarPuntoAcopio(String ubicacion, int cantidadReciclaje) {
        puntosAcopioReciclaje.put(ubicacion, cantidadReciclaje);
    }

    public HashMap<String, Integer> getPuntosAcopioReciclaje() {
        return puntosAcopioReciclaje;
    }
}

class Notificador {
    public void enviarNotificacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}

class ControladorPrincipal {
    private Estadisticas estadisticas;
    private Notificador notificador;
    private Usuario usuarioActual;

    public ControladorPrincipal() {
        estadisticas = new Estadisticas();
        notificador = new Notificador();
        usuarioActual = null;
    }

    public void iniciarSistema() {
        JFrame frame = new JFrame("Sistema de Gestión de Residuos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        JPanel panelInicioSesion = new JPanel(new GridLayout(3, 2));
        panelInicioSesion.setBackground(new Color(0, 0, 0));

        JLabel lblNombreUsuario = new JLabel("Nombre de usuario:");
        lblNombreUsuario.setForeground(new Color(255, 215, 0)); // Dorado
        JTextField txtNombreUsuario = new JTextField();

        JLabel lblCorreoUsuario = new JLabel("Correo electrónico:");
        lblCorreoUsuario.setForeground(new Color(255, 215, 0)); // Dorado
        JTextField txtCorreoUsuario = new JTextField();

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBackground(new Color(255, 215, 0)); // Dorado
        btnIniciarSesion.setForeground(Color.BLACK);
        btnIniciarSesion.addActionListener(e -> {
            String nombre = txtNombreUsuario.getText();
            String correo = txtCorreoUsuario.getText();
            if (!nombre.isEmpty() && !correo.isEmpty()) {
                usuarioActual = new Usuario(nombre, correo);
                mostrarInterfazPrincipal(frame);
            } else {
                notificador.enviarNotificacion("Por favor, complete todos los campos.");
            }
        });

        panelInicioSesion.add(lblNombreUsuario);
        panelInicioSesion.add(txtNombreUsuario);
        panelInicioSesion.add(lblCorreoUsuario);
        panelInicioSesion.add(txtCorreoUsuario);
        panelInicioSesion.add(new JLabel());
        panelInicioSesion.add(btnIniciarSesion);

        frame.add(panelInicioSesion, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void mostrarInterfazPrincipal(JFrame frame) {
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));
        panelPrincipal.setBackground(new Color(0, 0, 0));

        JPanel panelIzquierdo = new JPanel(new GridLayout(5, 1));
        panelIzquierdo.setBackground(new Color(0, 0, 0));
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(0, 0, 0));

        JButton btnRegistroPuntoAcopio = new JButton("Registrar Punto de Acopio");
        JButton btnEducacionAmbiental = new JButton("Educación Ambiental");
        JButton btnMostrarEstadisticas = new JButton("Mostrar Estadísticas");
        btnMostrarEstadisticas.setForeground(new Color(255, 215, 0)); // Dorado

        btnRegistroPuntoAcopio.setBackground(new Color(255, 215, 0)); // Dorado
        btnEducacionAmbiental.setBackground(new Color(255, 215, 0)); // Dorado

        btnRegistroPuntoAcopio.addActionListener(e -> registrarPuntoAcopio());
        btnEducacionAmbiental.addActionListener(e -> mostrarEducacionAmbiental());
        btnMostrarEstadisticas.addActionListener(e -> mostrarEstadisticas());

        panelIzquierdo.add(new JLabel("Acciones disponibles:"));
        panelIzquierdo.add(btnRegistroPuntoAcopio);
        panelIzquierdo.add(btnEducacionAmbiental);

        JLabel lblSesion = new JLabel("Sesión iniciada por: " + (usuarioActual != null ? usuarioActual.getNombre() : "Invitado"));
        lblSesion.setForeground(new Color(255, 215, 0)); // Dorado
        panelDerecho.add(lblSesion, BorderLayout.NORTH);
        panelDerecho.add(btnMostrarEstadisticas, BorderLayout.SOUTH);

        panelPrincipal.add(panelIzquierdo);
        panelPrincipal.add(panelDerecho);

        frame.getContentPane().removeAll();
        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void registrarPuntoAcopio() {
        String ubicacion = JOptionPane.showInputDialog(null, "Ingrese la ubicación del punto de acopio:");
        if (ubicacion != null && !ubicacion.isEmpty()) {
            String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad de residuos reciclados (kg):");
            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                try {
                    int cantidadReciclaje = Integer.parseInt(cantidadStr);
                    estadisticas.agregarPuntoAcopio(ubicacion, cantidadReciclaje);
                    notificador.enviarNotificacion("Punto de acopio registrado correctamente.");
                } catch (NumberFormatException e) {
                    notificador.enviarNotificacion("Por favor, ingrese una cantidad válida.");
                }
            }
        } else {
            notificador.enviarNotificacion("Por favor, ingrese la ubicación del punto de acopio.");
        }
    }

    private void mostrarEducacionAmbiental() {
        try {
            Desktop.getDesktop().browse(new URI("https://youtu.be/kJI0sloHVyU?si=GqZrhJs9FiFpRg7B"));
        } catch (Exception e) {
            notificador.enviarNotificacion("Error al abrir el enlace.");
        }
    }

    private void mostrarEstadisticas() {
        StringBuilder sb = new StringBuilder("Estadísticas de Puntos de Acopio:\n");
        for (String ubicacion : estadisticas.getPuntosAcopioReciclaje().keySet()) {
            sb.append("- Ubicación: ").append(ubicacion).append(", Cantidad Reciclada: ").append(estadisticas.getPuntosAcopioReciclaje().get(ubicacion)).append(" kg\n");
        }
        notificador.enviarNotificacion(sb.toString());
    }
}
