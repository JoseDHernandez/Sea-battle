/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import java.util.List;

/**
 *
 * @author Jose
 */
public class Mine extends Power implements Impactable {

    public Mine() {
        super();
    }

    public Mine(String coord) {
        super(coord);
    }

    @Override
    public boolean verifyImpact(String Coord) {
        return super.getPosition().equals(Coord);
    }

    @Override
    public boolean verifyImpact(List<String> atackCoords, List<String> targetCoords) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}
