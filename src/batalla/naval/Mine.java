/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalla.naval;

import java.util.List;

/**
 *
 * @author Jose
 */
public class Mine extends Power implements Impactable {

    public Mine(String coord) {
        super(coord);
    }

    @Override
    public void recibeImpact(String coord, Boat boat) {

        super.addCell(coord);
        for (String coordBoat : boat.getCoords()) {
            if (coordBoat.equals(coord)) {
                boat.setLife(boat.getLife() - 1);
            }
        }
    }

    @Override
    public boolean verifyImpact(String atackCoord, String targetCoord) {
        if (atackCoord.equals(targetCoord)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyImpact(List<String> atackCoords, List<String> targetCoords) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public String getTextPosition() {
        return super.getTextConvertPosition();
    }

}
