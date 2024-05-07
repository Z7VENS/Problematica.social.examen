package gestionresiduos;
// Clase que representa un punto de acopio de reciclaje
class PuntoAcopio {
    private String ubicacion;
    private int cantidadReciclaje;

    // Constructor de la clase PuntoAcopio
    public PuntoAcopio(String ubicacion, int cantidadReciclaje) {
        this.ubicacion = ubicacion;
        this.cantidadReciclaje = cantidadReciclaje;
    }

    // Métodos getters para obtener información del punto de acopio
    public String getUbicacion() {
        return ubicacion;
    }

    public int getCantidadReciclaje() {
        return cantidadReciclaje;
    }
}