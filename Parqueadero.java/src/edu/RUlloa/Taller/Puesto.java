package edu.RUlloa.Taller;

import edu.RUlloa.Taller.Carro;

public class Puesto {
    private Carro carro;
    private int numeroPuesto;

    public Puesto(int pPuesto) {
        carro = null;
        numeroPuesto = pPuesto;
    }

    public Carro getCarro() {
        return carro;
    }

    public boolean estaOcupado() {
        return carro != null;
    }

    public void parquearCarro(Carro pCarro) {
        carro = pCarro;
    }

    public void sacarCarro() {
        carro = null;
    }

    public int darNumeroPuesto() {
        return numeroPuesto;
    }

    public boolean tieneCarroConPlaca(String pPlaca) {
        return carro != null && carro.tienePlaca(pPlaca);
    }
}


