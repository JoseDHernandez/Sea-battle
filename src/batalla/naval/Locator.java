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
public class Locator extends Power {

    public List<String> findMatchCoords(String atackCoords, List<String> targetCoords) {
        List<String> temp = new ArrayList<>();
        for (String coord : targetCoords) {
            if (atackCoords.equals(coord)) {
                temp.add(coord);
            }
        }
        return temp;
    }
}
