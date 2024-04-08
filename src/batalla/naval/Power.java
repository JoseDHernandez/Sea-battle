/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalla.naval;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Power implements Cell {

    /**
     * Lista de coordenadas utilizadas por el PowerUp
     */
    private List<String> coords;
    /**
     * Cordenada de la posición de PowerUp
     */
    private String position;

    public Power() {
        coords = new ArrayList<>();
        position = "";
    }

    /**
     * Constructor para la clase Power.
     *
     * @param position Coordenadas de la posición
     */
    public Power(String position) {
        coords = new ArrayList<>();
        this.position = position;
    }

    @Override
    public void addCell(List<String> coords) {
        this.coords = coords;
    }

    @Override
    public void addCell(String coord) {
        coords.add(coord);
    }

    @Override
    public List<String> getCells() {
        return coords;
    }

    public void setPosition(String coord) {
        position = coord;
    }

    public String getPosition() {
        return position;
    }

    /**
     * Método para obtener la posición de la entidad en alfabeto militar
     *
     * @return Posición en alfabeto militar
     */
    public String getTextConvertPosition() {
        String letter = position.substring(0, 1);
        String text;
        text = switch (letter.toUpperCase()) {
            case "A" ->
                "Alfa";
            case "B" ->
                "Bravo";
            case "C" ->
                "Charlie";
            case "D" ->
                "Delta";
            case "E" ->
                "Echo";
            case "F" ->
                "Foxtrot";
            case "G" ->
                "Golf";
            case "H" ->
                "Hotel";
            case "I" ->
                "India";
            case "J" ->
                "Juliett";
            default ->
                letter;
        };
        return text + " " + position.substring(1);
    }
}
