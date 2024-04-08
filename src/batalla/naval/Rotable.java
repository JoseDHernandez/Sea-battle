/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package batalla.naval;

/**
 *
 * @author Jose
 */
public interface Rotable {

    /**
     * Establece el valor de rotación.
     *
     * @param rotation el valor de rotación a establecer
     */
    void setRotation(int rotation);

    /**
     * Obtiene el valor de rotación actual.
     *
     * @return el valor de rotación actual
     */
    int getRotation();

    /**
     * Obtiene el valor de rotación actual en grados.
     *
     * @return el valor de rotación actual en grados
     */
    double getRotationDegrees();

    /**
     * Obtiene la orientación actual del objeto.
     *
     * @return {@code true} si la orientación es horizontal, {@code false} si es
     * vertical
     */
    boolean getOrientation();

    /**
     * Establece la orientación del objeto.
     *
     * @param orientation {@code true} para establecer la orientación como
     * horizontal, {@code false} para vertical
     */
    void setOrientation(boolean orientation);
}
