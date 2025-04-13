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
    private VCA va = new ListarAnimalesView();
    
      @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals(mp.OPC_GUARDAR)){
           System.out.println("Opcion guardar animal"); 
       }
       
       if(e.getActionCommand().equals(mp.OPC_LISTAR)){
           vl.cargarAnimales(obtenerLista());
           
           System.out.println("Opciona listar animales");
           
           vl.setControlador(this);
           vl.ejecutar();
       }
       
       if(e.getActionCommand().equals(mp.OPC_CALCULAR)){
           va.ejecutar();
           
           System.out.println("Opcion calcular alimentos");
       }
    }
    
    public void ejecutar(){
     mp.setControlador(this);
     mp.ejecutar();
    }
    
      public ArrayList<String[]> obtenerLista (){
         ArrayList<String[]> datos = new ArrayList<>();
         for(Mamifero a : Persistencia.getAnimales()){
         String[] fila = {
            a.getEspecie().getNombre(),
            String.valueOf(a.getEdad()),
            String.valueOf(a.getPeso()),
            String.valueOf(a.getTipoAlimentacion()),
            String.valueOf(a.getSector().getNumero())
         };
         datos.add(fila);
         }
         return datos;
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
