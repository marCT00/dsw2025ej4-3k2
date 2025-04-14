/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author Genaro
 */
public interface IVP {
    public static String OP_AGREGAR = "agregar";
    public static String OP_CALCULO = "calcular";
    public static String OP_LISTADO = "Listar";
    public void setControlador(Controlador control);
     public void ejecutar();
     
}
