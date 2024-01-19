import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase que representa un simulador de radio con funciones básicas.
 */
public class Radio {

    private boolean isOn;
    private String mode; // AM or FM
    private double frequency;
    private Map<Integer, Double> presetStations;

    /**
     * Constructor de la clase Radio. */
    public Radio() {
        isOn = false;
        mode = "FM";
        frequency = 87.9;
        presetStations = new HashMap<>();
    }

    /**
     * Enciende el radio.
     */
    public void turnOn() {
        isOn = true;
        System.out.println("Radio encendido");
    }

    /**
     * Apaga el radio.
     */
    public void turnOff() {
        isOn = false;
        System.out.println("Radio apagado");
    }

    /**
     * Cambia el modo del radio de AM a FM o de FM a AM.
     */
    public void changeMode() {
        if (isOn) {
            mode = mode.equals("AM") ? "FM" : "AM";
            System.out.println("Modo cambiado a " + mode);
        } else {
            System.out.println("Enciende el radio primero");
        }
    }

    /**
     * Avanza en el dial de las emisoras. Al llegar al final del dial, inicia nuevamente.
     */
    public void changeFrequency() {
        if (isOn) {
            if (mode.equals("AM")) {
                frequency += 10;
                if (frequency > 1610) {
                    frequency = 530;
                }
            } else {
                frequency += 0.2;
                if (frequency > 107.9) {
                    frequency = 87.9;
                }
            }
            System.out.println("Frecuencia cambiada a " + frequency);
        } else {
            System.out.println("Enciende el radio primero");
        }
    }

    /**
     * Guarda una emisora en uno de los 12 botones preestablecidos.
     *
     * @param buttonNumber Número del botón (1-12) en el que se guardará la emisora.
     */
    public void savePreset(int buttonNumber) {
        if (isOn) {
            presetStations.put(buttonNumber, frequency);
            System.out.println("Emisora guardada en el botón " + buttonNumber);
        } else {
            System.out.println("Enciende el radio primero");
        }
    }

    /**
     * Selecciona la emisora guardada en un botón preestablecido.
     *
     * @param buttonNumber Número del botón (1-12) que contiene la emisora guardada.
     */
    public void selectPreset(int buttonNumber) {
        if (isOn) {
            if (presetStations.containsKey(buttonNumber)) {
                frequency = presetStations.get(buttonNumber);
                System.out.println("Seleccionado el botón " + buttonNumber + ": Frecuencia " + frequency);
            } else {
                System.out.println("Botón " + buttonNumber + " no tiene emisora guardada");
            }
        } else {
            System.out.println("Enciende el radio primero");
        }
    }

    /**
     * Método principal que permite interactuar con el simulador de radio a través de la consola.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Radio radio = new Radio();

        int option;
        do {
            System.out.println("1. Prende el radio");
            System.out.println("2. Cambia de AM a FM a AM");
            System.out.println("3. Avanzar en el dial de las emisoras");
            System.out.println("4. Permite guardar una emisora en uno de los 12 botones");
            System.out.println("5. Permite seleccionar la emisora puesta en un botón");
            System.out.println("6. Apagar el radio");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    radio.turnOn();
                    break;
                case 2:
                    radio.changeMode();
                    break;
                case 3:
                    radio.changeFrequency();
                    break;
                case 4:
                    System.out.print("Ingresa el número del botón (1-12): ");
                    int buttonNumber = scanner.nextInt();
                    radio.savePreset(buttonNumber);
                    break;
                case 5:
                    System.out.print("Ingresa el número del botón (1-12): ");
                    int selectedButton = scanner.nextInt();
                    radio.selectPreset(selectedButton);
                    break;
                case 6:
                    radio.turnOff();
                    break;
            }
        } while (option != 0);

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}

