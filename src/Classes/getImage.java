package Classes;

import Entitys.Boat;
import Entitys.Locator;
import Entitys.Mine;
import Entitys.Power;
import Entitys.Submarine;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class getImage {

    private Player player; // Jugador actual

    public getImage() {
        this.player = null;
    }

    public getImage(Player player) {
        this.player = player;
    }

    /**
     * Metodo para obtener la ruta de una imagen segun el nombre
     *
     * @param name Nombre de la imagen
     * @return ruta absoluta de la imagen
     */
    public static String urlOfImage(String name) {
        return System.getProperty("user.dir") + "\\src\\img\\" + name + ".png";
    }

    /**
     * Rota la imagen especificada por un ángulo dado.
     *
     * @param icon La imagen a rotar.
     * @param angle El ángulo de rotación en radianes.
     * @return Una nueva imagen rotada.
     */
    public ImageIcon rotateImage(ImageIcon icon, double angle) {
        java.awt.Image image = icon.getImage();
        int w = image.getWidth(null);
        int h = image.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Rotar la imagen 
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, w / 2, h / 2);
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);

        g2d.dispose();

        return new ImageIcon(rotatedImage);
    }

    /**
     * Obtiene un ImageIcon para un tipo específico de celda en función de
     * varios factores (tipo de barco, power up, etc).
     *
     * @param type El tipo de celda.
     * @param index El índice de la longitud de la entidad o parte espeficica
     * (10 para celda impactada (en agua), 0 - 2 Barcos y submarino, 8 y 9
     * Torpedos, 0 - 8 Localizador con 4 reservador para GPS, 11 - 14 Imagenes
     * barcos, 15 celda a impactar).
     * @param actualBoat
     * @param actualPowerUp
     * @param inBuild
     * @return Un ImageIcon para la celda especificada.
     */
    public ImageIcon getIcon(boolean type, int index, Boat actualBoat, Power actualPowerUp, boolean inBuild) {

        StringBuilder typeOfImage = new StringBuilder();
        String url = "";
        //Imagen de impacto
        if (index == 15) {
            return new ImageIcon(urlOfImage("ZH"));
        }
        if (index == 10) {
            return new ImageIcon(urlOfImage("ZX"));
        }
        //Imagenes del bote actual
        if (index > 10 && index < 15) {
            //Le resto 11 al index para obtener el numero de imagen
            return rotateImage(new ImageIcon(urlOfImage((index - 11) + "")), actualBoat == null ? 0.0 : actualBoat.getRotationDegrees());
        }
        if (player.getTypeAttack() && actualBoat != null) {

            //Obtener inicial
            String iniLetter = "";
            iniLetter = inBuild ? "P" : "X";
            typeOfImage.append(iniLetter);
            //Siguientes letras
            typeOfImage.append(actualBoat.getSize() == 1 ? "B" : "H");
            url = typeOfImage.toString();
            int rotation = actualBoat.getRotation();
            switch (actualBoat.getSize()) {
                case 3 -> {
                    if (rotation >= 2) {
                        switch (index) {
                            case 0 ->
                                url += "C";
                            case 1 ->
                                url += "B";
                            default ->
                                url += "A";
                        }
                    } else {
                        switch (index) {
                            case 0 ->
                                url += "A";
                            case 1 ->
                                url += "B";
                            default ->
                                url += "C";
                        }
                    }
                }
                case 2 -> {

                    if (rotation >= 2) {
                        if (index == 0) {
                            url += "C";
                        } else {
                            url += "A";
                        }
                    } else {
                        if (index == 0) {
                            url += "A";
                        } else {
                            url += "C";
                        }
                    }
                }

            }
            return rotateImage(new ImageIcon(urlOfImage(url)), actualBoat.getRotationDegrees());

        } else {
            if (actualPowerUp instanceof Mine) {
                return new ImageIcon(urlOfImage("Mine"));
            } else if (actualPowerUp instanceof Locator) {
                /*
                0 1 2
                3 [4] 5
                6 7 8
                 */
                url = "Locator";
                if (index != 4) {
                    url += "Cell";
                }
                return new ImageIcon(urlOfImage(url));
            } else if (actualPowerUp instanceof Submarine) {
                //Submarine
                url += (index < 3 ? "S" : "");
                if (((Submarine) actualPowerUp).getRotation() >= 2) {
                    switch (index) {
                        case 0 ->
                            url += "C";
                        case 1 ->
                            url += "B";
                        case 2 ->
                            url += "A";
                        default ->
                            url += "Torpedo";
                    }

                } else {
                    switch (index) {
                        case 0 ->
                            url += "A";
                        case 1 ->
                            url += "B";
                        case 2 ->
                            url += "C";
                        default ->
                            url += "Torpedo";
                    }
                }
            }
            if (index == 8) {
                return rotateImage(new ImageIcon(urlOfImage(url)), ((Submarine) actualPowerUp).getRotationDegreesTorpedoes()[0]);
            } else if (index == 9) {
                return rotateImage(new ImageIcon(urlOfImage(url)), ((Submarine) actualPowerUp).getRotationDegreesTorpedoes()[1]);
            }
            //Retorna submarine
            return rotateImage(new ImageIcon(urlOfImage(url)), ((Submarine) actualPowerUp).getRotationDegrees());
        }
    }

    public List<ImageIcon> getListIcons(boolean type, List<String> cells, Boat actualBoat, Power actualPowerUp, boolean inBuild) {
        List<ImageIcon> tempList = new ArrayList<>();
        if (inBuild) {
            if (player.getTypeAttack() && actualBoat != null) {
                //Boats
                switch (actualBoat.getSize()) {
                    case 3:
                        tempList.add(getIcon(type, 0, actualBoat, actualPowerUp, inBuild));
                        tempList.add(getIcon(type, 1, actualBoat, actualPowerUp, inBuild));
                        tempList.add(getIcon(type, 2, actualBoat, actualPowerUp, inBuild));
                        break;
                    case 2:
                        tempList.add(getIcon(type, 0, actualBoat, actualPowerUp, inBuild));
                        tempList.add(getIcon(type, 1, actualBoat, actualPowerUp, inBuild));
                        break;
                    case 1:
                        tempList.add(getIcon(type, 0, actualBoat, actualPowerUp, inBuild));
                        break;
                }
            } else {
                //Mine
                tempList.add(getIcon(type, 0, actualBoat, actualPowerUp, inBuild));
            }
        } else if (player.getTypeAttack() == false) {

            if (actualPowerUp instanceof Locator) {
                /*
                0 1 2
                3 [4] 5
                6 7 8
                 */
                for (int i = 0; i < cells.size(); i++) {
                    tempList.add(getIcon(type, i, actualBoat, actualPowerUp, inBuild));
                }

            } else if (actualPowerUp instanceof Submarine) {
                //Torpedo
                if (cells.size() == 2) {
                    Submarine sub = (Submarine) actualPowerUp;
                    if (!sub.getTorpedoes().isEmpty()) {
                        //Celdas antiguas
                        tempList.add(getIcon(type, 8, actualBoat, actualPowerUp, inBuild));
                        tempList.add(getIcon(type, 8, actualBoat, actualPowerUp, inBuild));
                    }
                    tempList.add(getIcon(type, 8, actualBoat, actualPowerUp, inBuild));
                    tempList.add(getIcon(type, 9, actualBoat, actualPowerUp, inBuild));
                } else {
                    //Submarino
                    tempList.add(getIcon(type, 0, actualBoat, actualPowerUp, inBuild));
                    tempList.add(getIcon(type, 1, actualBoat, actualPowerUp, inBuild));
                    tempList.add(getIcon(type, 2, actualBoat, actualPowerUp, inBuild));
                }
            } else {
                //Celda jugada
                tempList.add(getIcon(type, 10, actualBoat, actualPowerUp, inBuild));
            }
        } else if (!inBuild && player.getTypeAttack()) {
            //Celda a atacar 
            tempList.add(getIcon(type, type ? 10 : 15, actualBoat, actualPowerUp, inBuild));
        }
        return tempList;
    }
}
