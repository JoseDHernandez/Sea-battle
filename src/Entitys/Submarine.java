/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

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
    private List<String> torpedoes;// Posiciones de los torpedos
    private int rotationTorpedoes0;
    private int rotationTorpedoes1;

    public Submarine() {
        super();
        orientation = true;
        rotation = 1;
        torpedoes = new ArrayList<>();
        rotationTorpedoes0 = 0;
        rotationTorpedoes1 = 2;
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

    private double rotationDegrees(int rot) {
        double r;
        r = switch (rot) {
            case 1 ->
                Math.PI / 2;
            case 2 ->
                Math.PI;
            case 3 ->
                (3 * Math.PI) / 2;
            default ->
                0.0;
        };
        return r;
    }

    @Override
    public double getRotationDegrees() {

        return rotationDegrees(rotation);
    }

    public double[] getRotationDegreesTorpedoes() {
        double[] degrees = {rotationDegrees(rotationTorpedoes0), rotationDegrees(rotationTorpedoes1)};
        return degrees;
    }

    public void setRotationTorpedoes(int[] rot) {
        rotationTorpedoes0 = rot[0];
        rotationTorpedoes1 = rot[1];
    }

    public int[] getRotationTorpedoes() {
        int[] t = {rotationTorpedoes0, rotationTorpedoes1};
        return t;
    }

    @Override
    public boolean getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean verifyImpact(String atackCoord) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
