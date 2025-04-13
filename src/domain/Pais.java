/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author maria
 */
public class Pais {
    
    private final String nombre;
    private String codigo;

    public Pais(String nombre,String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
      
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return nombre;
    }

    
}
