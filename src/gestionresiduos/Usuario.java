package gestionresiduos;

class Usuario {
    private String nombre;
    private String correo;
    private int puntosReciclaje; // Puntos obtenidos por reciclaje

    // Constructor de la clase Usuario
    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.puntosReciclaje = 0;
    }

    // Métodos getters para obtener información del usuario
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getPuntosReciclaje() {
        return puntosReciclaje;
    }

    // Método para agregar puntos por reciclaje al usuario
    public void agregarPuntosReciclaje(int puntos) {
        puntosReciclaje += puntos;
    }
}