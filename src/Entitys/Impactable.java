/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Entitys;

import java.util.List;

/**
 *
 * @author Jose
 */
public interface Impactable {

    /**
     * Método para indicar que el Boat ha recibido un impacto en la coordenada
     * especificada.
     *
     * @param coord La coordenada donde se produce el impacto
     * @param boat Objeto boat a compara coordenadas
     */
    void recibeImpact(String coord, Boat boat);

    /**
     * Método para verificar si la celda/coordenada ha sido impactada en la
     * coordenada especificada.
     *
     * @param atackCoord la coordenada seleccionada
     * @param targetCoord coordenada objetiva
     * @return {@code true} si el objeto ha sido impactado en la coordenada
     * especificada, {@code false} en caso contrario
     */
    boolean verifyImpact(String atackCoord, String targetCoord);

    /**
     * Método para verificar si en una lista de coordenadas alguna ha impactado
     * en alguna de las coordenadas objetivas.
     *
     * @param atackCoords Lista de coordenadas seleccionadas
     * @param targetCoords Lista de coordenadas objetivas
     * @return {@code true} si el objeto ha sido impactado en la coordenada
     * especificada, {@code false} en caso contrario
     */
    boolean verifyImpact(List<String> atackCoords, List<String> targetCoords);

    /**
     * Método para obtener la posición del PowerUp en alfabeto militar
     *
     * @return Posición en alfabeto militar
     */
    String getTextPosition();
}
