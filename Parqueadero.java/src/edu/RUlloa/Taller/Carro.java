package edu.RUlloa.Taller;

public class Carro {
    private String placas;
    private int horaLlegada;

    public Carro(String pPlacas, int pHora) {
        placas = pPlacas;
        horaLlegada = pHora;
    }

    public String getPlacas() {
        return placas;
    }

    public int getHoraLlegada() {
        return horaLlegada;
    }

    public String toString() {
        return "Placas: " + placas + ", Hora de llegada: " + horaLlegada;
    }

    public boolean tienePlaca(String pPlaca) {
        return placas.equalsIgnoreCase(pPlaca);
    }

    public int getTiempoEnParqueadero(int pHoraSalida) {
        return pHoraSalida - horaLlegada + 1;
    }
}

