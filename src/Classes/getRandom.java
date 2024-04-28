package Classes;

import java.util.Random;

public class getRandom {

    private final int FIRST_LETTER = 65;//Decimal de 'A'
    private int MAX_LETTER;
    private int Size;

    /**
     * Este constructor solo sirve como valor inicializador del atributo al
     * instanciar, se debe re asignar los valores con
     * {@code getRandom(int Size,int Last_letter)}
     */
    public getRandom() {
        MAX_LETTER = 0;
        Size = 0;
    }

    /**
     * Este metodo es el metodo constructor que se debe usar para que funcione
     * de manera adecuada esta clase
     *
     * @param Size Tamaño del tablero
     */
    public getRandom(int Size) {
        this.Size = Size;
        MAX_LETTER = FIRST_LETTER + Size;
    }

    /**
     * Genera un número entero aleatorio dentro del rango [min, max].
     *
     * @param min El valor mínimo del rango.
     * @param max El valor máximo del rango.
     * @return Un número entero aleatorio dentro del rango especificado.
     */
    public int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    /**
     * Genera un número entero aleatorio dentro del rango [0, max].
     *
     * @param max El valor máximo del rango.
     * @return Un número entero aleatorio dentro del rango especificado.
     */
    public int getRandomNumber(int max) {
        return getRandomNumber(0, max);
    }

    /**
     * Este metodo retorna una coordenada aleatoria no usada
     *
     * @param attackPlayer Objeto Player del jugador atacante
     * @param targetPlayer Objeto Player del jugador al que se va a atacar
     * @return Coordenada aleatoria, en caso de que todas las coordenadas fueron
     * usadas se retorna {@code null}
     */
    public String getRandomCoords(Player attackPlayer, Player targetPlayer) {
        if (attackPlayer.getCells().size() == (Size * Size)) {
            return null;
        }
        String coord = "";
        do {
            coord = getRandomCoords(attackPlayer);
        } while (targetPlayer.impactVerification(coord) == -1);
        return coord;
    }

    /**
     * Genera una coordenada aleatorias para el jugador especificado,
     * asegurándose de que no se hayan utilizado previamente.
     *
     * @param tempPlayer El jugador para el cual se generan las coordenadas.
     * @return Las coordenadas aleatorias generadas para el jugador.
     */
    public String getRandomCoords(Player tempPlayer) {
        StringBuilder coord = new StringBuilder();

        do {
            coord = new StringBuilder();
            char letter = (char) getRandomNumber(FIRST_LETTER, MAX_LETTER);
            coord.append(letter).append(getRandomNumber(1, Size));
        } while (tempPlayer.getCells().contains(coord.toString()));
        return coord.toString();
    }

}
