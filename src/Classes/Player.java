/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Entitys.Boat;
import Entitys.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Player implements Cell {

    /**
     * Nombre del jugador
     */
    private String name;
    /**
     * Tipo de ataque {@code true} Normal {@code false} PowerUp
     */
    private boolean typeAttack;
    /**
     * Lista de celdas usadas
     */
    private List<String> cells;

    /**
     * Lista de barcos del jugador [Id, Boat Object]
     */
    private List<Object> boats;

    /**
     * Constructor de la clase Player. Inicializa el nombre del jugador como una
     * cadena vacía, la lista de celdas como una nueva lista vacía, y la lista
     * de barcos como una nueva lista vacía.
     */
    public Player() {
        name = "";
        cells = new ArrayList<>();
        boats = new ArrayList<>();
        typeAttack = true;
    }

    public Player(String name) {
        this.name = name;
        cells = new ArrayList<>();
        boats = new ArrayList<>();
        typeAttack = true;
    }

    /**
     * Establece el tipo de ataque.
     *
     * @param type el tipo de ataque a establecer. {@code true} para ataque
     * normal, {@code false} para PowerUp.
     */
    public void setTypeAttack(boolean type) {
        typeAttack = type;
    }

    /**
     * Obtiene el tipo de ataque.
     *
     * @return {@code true} si el tipo de ataque es normal, {@code false} si es
     * PowerUp.
     */
    public boolean getTypeAttack() {
        return typeAttack;
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

    @Override
    public void addCell(String cell) {
        if (!cells.contains(cell)) {
            cells.add(cell);
        }

    }

    @Override
    public void addCell(List<String> cells) {
        this.cells.addAll(cells);
    }

    @Override
    public List<String> getCells() {
        return cells;
    }

    public void clearCells() {
        cells.clear();
    }

    /**
     * Agrega un barco a la lista de barcos del jugador.
     *
     * @param boat El barco a agregar.
     */
    public void addBoat(Boat boat) {
        // Agerga la Id
        boats.add(boats.isEmpty() ? 0 : (boats.size() / 2));
        // Agrega el objeto boat
        boats.add(boat);
    }

    /**
     * Obtiene la lista de barcos del jugador.
     *
     * @return La lista de barcos del jugador.
     */
    public List<Object> getBoatList() {
        return boats;
    }

    public void clearBoatsList() {
        boats.clear();
    }

    public void setListOfBoats(List<Object> list) {
        boats.addAll(list);
    }

    public Boat getBoat(int id) {
        id = id + 1;
        Boat temp = (Boat) boats.get(id);
        int actualLife = temp.getLife();
        int afterLife = actualLife - 1;
        if (afterLife >= 0) {
            temp.setLife(afterLife);
            boats.set(id, temp);
            return temp;
        }
        //Restableco id para poder borrar ID y boat si ya no tiene vida
        id = id - 1;
        //Remuevo id
        boats.remove(id);
        //Remuevo Boat (mismo index porque ya no existe el Id)
        boats.remove(id);
        return null;
    }
}
