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
public class Submarine extends Power implements Impactable, Rotable {

    private boolean orientation;//true = horizontal , false = vertical
    private int rotation;
    private List<String> torpedoes;

    public Submarine() {
        super();
        orientation = true;
        rotation = 1;
        torpedoes = new ArrayList<>();
    }

    public void setTorpedoes(String[] arr) {
        torpedoes.clear();
        torpedoes.add(arr[0]);
        torpedoes.add(arr[1]);
    }

    public List<String> getTorpedoes() {
        return torpedoes;
    }

    @Override
    public void addCell(List<String> coords) {
        super.addCell(coords);
    }

    @Override
    public void addCell(String coord) {
        super.addCell(coord);
    }

    @Override
    public List<String> getCells() {
        return super.getCells();
    }

    @Override
    public void setPosition(String position) {
        super.setPosition(position);
    }

    @Override
    public void recibeImpact(String coord, Boat boat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean verifyImpact(String atackCoord, String targetCoord) {

        return atackCoord.equals(targetCoord);
    }

    @Override
    public boolean verifyImpact(List<String> atackCoords, List<String> targetCoords) {
        for (String coord : targetCoords) {
            if (atackCoords.contains(coord)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyImpact(String coord, List<String> targetCoords) {
        return verifyImpact(Arrays.asList(coord), targetCoords);
    }

    public String[] shoot(List<String> targetCoords) {
        String coords[] = {super.getPosition(), super.getPosition()};
        return shoot(coords, targetCoords);
    }

    public String[] shoot(String[] coord, List<String> targetCoords) {

        int num1 = Integer.parseInt(coord[0].substring(1));
        int num2 = Integer.parseInt(coord[1].substring(1));
        //Horizontal
        if (!orientation) {
            String coordTop = coord[0].charAt(0) + "" + (num1 - 1);
            String coordButton = coord[1].charAt(0) + "" + (num2 + 1);

            return new String[]{coordTop, coordButton};
        } else {
            //Vertical
            String coordLeft = ((char) (((int) coord[0].charAt(0)) - 1)) + "" + num1;
            String coordRight = ((char) (((int) coord[1].charAt(0)) + 1)) + "" + num2;

            return new String[]{coordLeft, coordRight};
        }
    }

    @Override
    public String getTextPosition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    @Override
    public int getRotation() {
        return rotation;
    }

    @Override
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

    @Override
    public boolean getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

}
