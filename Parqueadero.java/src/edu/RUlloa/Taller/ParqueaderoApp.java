package edu.RUlloa.Taller;

import edu.RUlloa.Taller.Parqueadero;

import java.util.Scanner;

public class ParqueaderoApp {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ingresar un carro al parqueadero");
            System.out.println("2. Dar salida a un carro del parqueadero");
            System.out.println("3. Informar los ingresos del parqueadero");
            System.out.println("4. Consultar la cantidad de puestos disponibles");
            System.out.println("5. Avanzar el reloj del parqueadero");
            System.out.println("6. Cambiar la tarifa del parqueadero");
            System.out.println("0. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la placa del carro:");
                    String placaIngreso = scanner.nextLine();
                    int resultadoIngreso = parqueadero.entrarCarro(placaIngreso);
                    imprimirResultadoIngreso(resultadoIngreso);
                    break;
                case 2:
                    System.out.println("Ingrese la placa del carro a sacar:");
                    String placaSalida = scanner.nextLine();
                    int resultadoSalida = parqueadero.sacarCarro(placaSalida);
                    imprimirResultadoSalida(resultadoSalida);
                    break;
                case 3:
                    System.out.println("Ingresos del parqueadero: $" + parqueadero.consultarIngresos());
                    break;
                case 4:
                    System.out.println("Cantidad de puestos disponibles: " + parqueadero.consultarPuestosDisponibles());
                    break;
                case 5:
                    parqueadero.avanzarReloj();
                    System.out.println("Reloj del parqueadero avanzado a la hora: " + parqueadero.consultarHoraActual());
                    break;
                case 6:
                    System.out.println("Ingrese la nueva tarifa del parqueadero:");
                    int nuevaTarifa = scanner.nextInt();
                    parqueadero.cambiarTarifa(nuevaTarifa);
                    System.out.println("Tarifa del parqueadero cambiada a: $" + parqueadero.consultarTarifa());
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void imprimirResultadoIngreso(int resultado) {
        switch (resultado) {
            case Parqueadero.NO_HAY_PUESTO:
                System.out.println("No hay puestos disponibles en el parqueadero.");
                break;
            case Parqueadero.CARRO_YA_EXISTE:
                System.out.println("El carro ya está en el parqueadero.");
                break;
            case Parqueadero.PARQUEADERO_CERRADO:
                System.out.println("El parqueadero está cerrado en este momento.");
                break;
            default:
                System.out.println("edu.RUlloa.Taller.Carro ingresado exitosamente en el puesto: " + resultado);
                break;
        }
    }

    private static void imprimirResultadoSalida(int resultado) {
        switch (resultado) {
            case Parqueadero.CARRO_NO_EXISTE:
                System.out.println("El carro no está en el parqueadero.");
                break;
            case Parqueadero.PARQUEADERO_CERRADO:
                System.out.println("El parqueadero está cerrado en este momento.");
                break;
            default:
                System.out.println("edu.RUlloa.Taller.Carro sacado exitosamente. Pago total: $" + resultado);
                break;
        }
    }
}
