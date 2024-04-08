/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalla.naval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Boat {

    private int life; // Vida restante del barco
    private int size; // Longitud del barco
    private List<String> coords; // Coordenadas ocupadas por el barco
    private boolean orientation;//true = horizontal , false = vertical
    private int rotation;

    public Boat() {
        life = 1;
        size = 1;
        coords = new ArrayList<>();
        orientation = true;
        rotation = 1;
    }

    public Boat(int size) {
        this.life = size;
        this.size = size;
        coords = new ArrayList<>();
        this.orientation = true;
        rotation = 1;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public double getRotationDegrees() {
        double r;
        switch (rotation) {
            case 1:
                r = Math.PI / 2;
                break;
            case 2:
                r = Math.PI;
                break;
            case 3:
                r = (3 * Math.PI) / 2;
                break;
            default:
                r = 0.0;
                break;
        }
        return r;
    }

    public boolean getOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    /**
     * Obtiene la vida restante del barco.
     *
     * @return La vida restante del barco.
     */
    public int getLife() {
        return life;
    }

    /**
     * Establece la vida restante del barco.
     *
     * @param life La vida restante del barco a establecer.
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Obtiene la longitud del barco.
     *
     * @return La longitud del barco.
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece la longitud del barco.
     *
     * @param size La longitud del barco a establecer.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Obtiene las coordenadas ocupadas por el barco.
     *
     * @return La lista de coordenadas ocupadas por el barco.
     */
    public List<String> getCoords() {
        return coords;
    }

    /**
     * Establece las coordenadas ocupadas por el barco.
     *
     * @param coords La lista de coordenadas ocupadas por el barco a establecer.
     */
    public void setCoords(List<String> coords) {
        this.coords = coords;
    }

}
