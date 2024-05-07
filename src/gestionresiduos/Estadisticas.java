package gestionresiduos;
import java.util.HashMap;

// Clase que almacena las estadísticas de los puntos de acopio y los puntos de reciclaje de los usuarios
class Estadisticas {
    private HashMap<String, Integer> puntosAcopioReciclaje; // Almacena la cantidad de reciclaje por cada punto de acopio

    // Constructor de la clase Estadisticas
    public Estadisticas() {
        puntosAcopioReciclaje = new HashMap<>();
    }

    // Método para agregar un nuevo punto de acopio con su respectiva cantidad de reciclaje
    public void agregarPuntoAcopio(String ubicacion, int cantidadReciclaje) {
        puntosAcopioReciclaje.put(ubicacion, cantidadReciclaje);
    }

    // Método getter para obtener los puntos de acopio y reciclaje
    public HashMap<String, Integer> getPuntosAcopioReciclaje() {
        return puntosAcopioReciclaje;
    }

    // Método para agregar los puntos de reciclaje ganados por un usuario
    public void agregarReciclajeUsuario(Usuario usuario, int puntosReciclaje) {
        String nombreUsuario = usuario != null ? usuario.getNombre() : "Invitado";
        if (!puntosAcopioReciclaje.containsKey(nombreUsuario)) {
            puntosAcopioReciclaje.put(nombreUsuario, 0);
        }
        puntosAcopioReciclaje.put(nombreUsuario, puntosAcopioReciclaje.get(nombreUsuario) + puntosReciclaje);
    }
}
