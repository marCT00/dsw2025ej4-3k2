package views;

import data.Persistencia;
import domain.TipoAlimentacion;

import javax.swing.*;
import java.util.InvalidPropertiesFormatException;
/// para entregar///
public class Program {

    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        Persistencia.inicializar();
        Controlador control = new Controlador();
        control.ejecutar();
        System.out.println("hola");

       // ListarAnimalesView view = new ListarAnimalesView();
       // view.setVisible(true);
    }
}
