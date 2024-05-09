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

    public String getRandomCoord(Player templayer) {
        if (templayer.getListCells().isEmpty()) {
            return null;
        }
        try {
            //listCells.removeAll(enemy.getCells());
            int index = templayer.getListCells().size() - 1;
            String coord = templayer.getListCells().get(getRandomNumber(index));
            templayer.getListCells().remove(index);
            return coord;
        } catch (NullPointerException e) {
            return null;
        }
    }

}
