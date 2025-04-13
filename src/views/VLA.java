/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public interface VLA {
    public void ejecutar();
    public void setControlador(Controlador control);
    public void cargarAnimales(ArrayList<String[]> datos);
}
