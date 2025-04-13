package views;

import data.Persistencia;
import domain.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Controlador implements ActionListener {
    private MenuPrincipal mp = new MenuPrincipal();
    private VLA vl = new VistaListar(null,true);
    
      @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals(mp.OPC_GUARDAR)){
           System.out.println("Opcion guardar animal"); 
       }
       
       if(e.getActionCommand().equals(mp.OPC_LISTAR)){
           ArrayList<Mamifero> datos = Persistencia.getAnimales();
           
           System.out.println("Opciona listar animales");
           
           vl.setControlador(this);
           vl.ejecutar();
       }
       
       if(e.getActionCommand().equals(mp.OPC_CALCULAR)){
           System.out.println("Opcion calcular alimentos");
       }
    }
    
    public void ejecutar(){
     mp.setControlador(this);
     mp.ejecutar();
    }
    
    
    
    public static TipoAlimentacion[] getTiposAlimentacion(){
        return  TipoAlimentacion.values();
    }
    public static ArrayList<Especie> getEspecies(){
        return Persistencia.getEspecies();
    }
    public static ArrayList<Sector> getSectores(){
        return Persistencia.getSectores();
    }
    
    public static ArrayList<AnimalViewModel> getAnimales(){
        ArrayList<AnimalViewModel> animales = new ArrayList<>();
        for(Mamifero animal : Persistencia.getAnimales()){
            animales.add(new AnimalViewModel(animal));
        }
        return animales;
    }
    
    public static ComidaViewModel  calcularComida(){
        double totalCarnivoros = Persistencia.getTotalComida(TipoAlimentacion.CARNIVORO);
        double totalHerbivoros = Persistencia.getTotalComida(TipoAlimentacion.HERBIVORO);
        return new ComidaViewModel(totalCarnivoros, totalHerbivoros);
    }

  
}
