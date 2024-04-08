/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package batalla.naval;

import java.util.List;

/**
 *
 * @author Jose
 */
public interface Cell {

    /**
     * Agrega una celda a la lista de celdas usadas.
     *
     * @param cell La celda a agregar.
     */
    void addCell(String cell);

    /**
     * Agrega una lista de celdas a la lista de celdas usadas.
     *
     * @param cells La lista de celdas a agregar.
     */
    void addCell(List<String> cells);

    /**
     * Obtiene la lista de celdas usadas.
     *
     * @return La lista de celdas usadas.
     */
    List<String> getCells();
}
