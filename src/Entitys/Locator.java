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
                    if (((Boat) boats.get(i)).getCoords().contains(coord)) {
                        int id = i - 1;
                        if (id >= 0 || id == -3) {
                            temp.add(id);//Id
                            temp.add(coord);//Coordenda
                        }
                    }
                }
            }
        }
        System.out.println(temp.toString());
        return temp;
    }
}
