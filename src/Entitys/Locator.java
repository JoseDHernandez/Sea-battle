/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Locator extends Power {

    public List<Object> findMatchCoords(List<Object> boats, List<String> Coords) {
        List<Object> temp = new ArrayList<>();
        if (!boats.isEmpty()) {
            for (int i = 1; i < boats.size(); i += 2) {
                if (temp.size() == 18) {
                    break;
                }
                for (String coord : Coords) {
                    Boat boat = new Boat((Boat) boats.get(i));
                    if (boat.getCoords().contains(coord)) {
                        temp.add(coord);//Coordenda
                        for (int k = 0; k < boat.getCoords().size(); k++) {
                            if (boat.getCoords().get(k).equals(coord)) {
                                temp.add(k);//Index
                                break;
                            }
                        }
                        temp.add(boat);//Barco
                    }
                }
            }
        }
        return temp;
    }
}
