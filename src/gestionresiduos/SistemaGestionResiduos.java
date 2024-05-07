package gestionresiduos;



import javax.swing.*;

// Clase principal que contiene el método main
public class SistemaGestionResiduos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorPrincipal controlador = new ControladorPrincipal();
            controlador.iniciarSistema();
        });
    }
}



