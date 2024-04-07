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
public class Player {

    private String name;//Nombre jugador
    private List<String> cells;//Celdas usadas
    private List<Boat> boats;//Barcos del jugador

    public Player() {
        name = "";
        cells = new ArrayList<>();
        boats = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param name El nombre del jugador a establecer.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void addCell(String cell) {
        cells.add(cell);
    }

    public void addCell(List<String> cells) {
        for (String cell : cells) {
            this.cells.add(cell);
        }
    }

    public List<String> getCells() {
        return cells;
    }

    public void addBoat(Boat boat) {
        boats.add(boat);
    }
}
