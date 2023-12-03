package edu.RUlloa.Taller;

import edu.RUlloa.Taller.Carro;

import java.util.ArrayList;

public class Parqueadero {
    // Constantes y atributos

    public static final int TAMANO = 40;
    public static final int NO_HAY_PUESTO = -1;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int CARRO_NO_EXISTE = -3;
    public static final int CARRO_YA_EXISTE = -4;
    public static final int HORA_INICIAL = 6;
    public static final int HORA_CIERRE = 21;
    public static final int TARIFA_INICIAL = 1200;

    private Puesto[] listaDePuestos;
    private int tarifa;
    private int caja;
    private int horaActual;
    private boolean abierto;

    // Constructor
    public Parqueadero() {
        horaActual = HORA_INICIAL;
        abierto = true;
        tarifa = TARIFA_INICIAL;
        caja = 0;
        listaDePuestos = new Puesto[TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            listaDePuestos[i] = new Puesto(i);
        }
    }

    public String darPlacaCarro(int numeroDePuesto) {
        if (estaOcupado(numeroDePuesto)) {
            return listaDePuestos[numeroDePuesto].getCarro().getPlacas();
        } else {
            return "No hay un carro en esta posición";
        }
    }

    public int entrarCarro(String placa) {
        if (!abierto) {
            return PARQUEADERO_CERRADO;
        }

        // Buscar en el parqueadero un carro con la placa indicada
        int numeroPuestoCarro = buscarPuestoCarro(placa.toUpperCase());
        if (numeroPuestoCarro != CARRO_NO_EXISTE) {
            return CARRO_YA_EXISTE;
        }

        // Buscar un puesto libre para el carro y agregarlo
        int numeroPuestoLibre = buscarPuestoLibre();
        if (numeroPuestoLibre != NO_HAY_PUESTO) {
            Carro carroEntrando = new Carro(placa, horaActual);
            listaDePuestos[numeroPuestoLibre].parquearCarro(carroEntrando);
            return numeroPuestoLibre;
        } else {
            return NO_HAY_PUESTO;
        }
    }

    public int sacarCarro(String placa) {
        if (!abierto) {
            return PARQUEADERO_CERRADO;
        }

        int numeroPuesto = buscarPuestoCarro(placa.toUpperCase());
        if (numeroPuesto == CARRO_NO_EXISTE) {
            return CARRO_NO_EXISTE;
        }

        Carro carro = listaDePuestos[numeroPuesto].getCarro();
        int horasEnParqueadero = carro.getTiempoEnParqueadero(horaActual);
        int porPagar = horasEnParqueadero * tarifa;

        caja += porPagar;
        listaDePuestos[numeroPuesto].sacarCarro();

        return porPagar;
    }

    public boolean estaOcupado(int numeroDePuesto) {
        return listaDePuestos[numeroDePuesto].estaOcupado();
    }

    // 2.1 Método que retorna cuánto tiempo llevan en promedio los carros en el parqueadero.
    public double darTiempoPromedio() {
        if (caja == 0) {
            return 0; // Evitar división por cero
        }

        int totalTiempo = 0;
        int totalCarros = 0;

        for (Puesto puesto : listaDePuestos) {
            if (puesto.estaOcupado()) {
                totalTiempo += puesto.getCarro().getTiempoEnParqueadero(horaActual);
                totalCarros++;
            }
        }

        return (double) totalTiempo / totalCarros;
    }

    // 2.2 Método que retorna el carro que ha estado durante más horas en el parqueadero.
    // Si no hay ningún carro, el método debe retornar null.
    public Carro darCarroMasHoras() {
        Carro carroMasHoras = null;
        int maxHoras = 0;

        for (Puesto puesto : listaDePuestos) {
            if (puesto.estaOcupado()) {
                int horasEnParqueadero = puesto.getCarro().getTiempoEnParqueadero(horaActual);
                if (horasEnParqueadero > maxHoras) {
                    maxHoras = horasEnParqueadero;
                    carroMasHoras = puesto.getCarro();
                }
            }
        }

        return carroMasHoras;
    }

    // 2.3 Método que retorna un valor boolean indicando si hay un carro que lleve más de 8 horas parqueado.
    public boolean hayCarroMasDeOchoHoras() {
        for (Puesto puesto : listaDePuestos) {
            if (puesto.estaOcupado()) {
                int horasEnParqueadero = puesto.getCarro().getTiempoEnParqueadero(horaActual);
                if (horasEnParqueadero > 8) {
                    return true;
                }
            }
        }
        return false;
    }

    // 2.4 Método que retorna en un ArrayList todos los carros que llevan más de tres horas parqueados.
    public ArrayList<Carro> darCarrosMasDeTresHorasParqueados() {
        ArrayList<Carro> carrosMasDeTresHoras = new ArrayList<>();

        for (Puesto puesto : listaDePuestos) {
            if (puesto.estaOcupado()) {
                int horasEnParqueadero = puesto.getCarro().getTiempoEnParqueadero(horaActual);
                if (horasEnParqueadero > 3) {
                    carrosMasDeTresHoras.add(puesto.getCarro());
                }
            }
        }

        return carrosMasDeTresHoras;
    }

    // 2.5 Método que retorna un valor boolean indicando si hay dos carros parqueados con la misma placa.
    public boolean hayCarrosPlacaIgual() {
        for (int i = 0; i < TAMANO; i++) {
            if (listaDePuestos[i].estaOcupado()) {
                String placaActual = listaDePuestos[i].getCarro().getPlacas();
                for (int j = i + 1; j < TAMANO; j++) {
                    if (listaDePuestos[j].estaOcupado() && placaActual.equalsIgnoreCase(listaDePuestos[j].getCarro().getPlacas())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 3.1 Método que cuenta cuántos carros parqueados tienen una placa que comienza con el prefijo “PB”.
    public int contarCarrosQueComienzanConPlacaPB() {
        int contador = 0;
        for (Puesto puesto : listaDePuestos) {
            if (puesto.estaOcupado() && puesto.getCarro().getPlacas().toUpperCase().startsWith("PB")) {
                contador++;
            }
        }
        return contador;
    }

    // 3.2 Método que retorna un valor boolean indicando si hay un carro que lleve 24 o más horas parqueado.
    public boolean hayCarroCon24Horas() {
        for (Puesto puesto : listaDePuestos) {
            if (puesto.estaOcupado()) {
                int horasEnParqueadero = puesto.getCarro().getTiempoEnParqueadero(horaActual);
                if (horasEnParqueadero >= 24) {
                    return true;
                }
            }
        }
        return false;
    }

    // 3.3 Modificación del método metodo1( ) para que retorne mensajes específicos.
    public String metodo1() {
        int carrosPB = contarCarrosQueComienzanConPlacaPB();
        boolean hayCarro24Horas = hayCarroCon24Horas();

        return "Cantidad de carros con placa PB: " + carrosPB + " - Hay carro parqueado por 24 o más horas: " +
                (hayCarro24Horas ? "Sí." : "No.");
    }

    // 3.4 Método para desocupar el parqueadero y retornar la cantidad de carros sacados.
    public int desocuparParqueadero() {
        int carrosSacados = 0;
        for (Puesto puesto : listaDePuestos) {
            if (puesto.estaOcupado()) {
                carrosSacados++;
                puesto.sacarCarro();
            }
        }
        return carrosSacados;
    }

    // 3.5 Modificación del método metodo2( ) para que retorne un mensaje específico.
    public String metodo2() {
        int carrosSacados = desocuparParqueadero();
        return "Cantidad de carros sacados: " + carrosSacados + ".";
    }

    private int buscarPuestoCarro(String placa) {
        for (int i = 0; i < TAMANO; i++) {
            if (listaDePuestos[i].tieneCarroConPlaca(placa)) {
                return i;
            }
        }
        return CARRO_NO_EXISTE;
    }

    private int buscarPuestoLibre() {
        for (int i = 0; i < TAMANO; i++) {
            if (!listaDePuestos[i].estaOcupado()) {
                return i;
            }
        }
        return NO_HAY_PUESTO;
    }

    public int consultarIngresos() {
        return caja;
    }

    public int consultarPuestosDisponibles() {
        int disponibles = 0;
        for (Puesto puesto : listaDePuestos) {
            if (!puesto.estaOcupado()) {
                disponibles++;
            }
        }
        return disponibles;
    }

    public void avanzarReloj() {
        horaActual++;
        if (horaActual > HORA_CIERRE) {
            horaActual = HORA_INICIAL;
        }
    }

    public int consultarHoraActual() {
        return horaActual;
    }

    public void cambiarTarifa(int nuevaTarifa) {
        tarifa = nuevaTarifa;
    }

    public int consultarTarifa() {
        return tarifa;
    }
}





