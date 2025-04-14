package views;

import data.Persistencia;
import domain.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador implements ActionListener {
     VistaPrincipal vp = new VistaPrincipal();
     IVL vl = new VistaListar(null,true);
     IVA  va = new VistaAnimales(null,true);
     IVC vc = new ListarAnimalesView();
     private boolean controladorAsignado = false;
     private boolean itemSeleccionado = true;
     
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

      public void ejecutar(){
       vp.setControlador(this);    
       vp.ejecutar();
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
      
      
      
      
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(vp.OP_CALCULO)){
         vc.cargarTabla(obtenerLista());
         vc.setControlador(this);
         vc.ejecutar();
        }
        
        if(e.getActionCommand().equals(vp.OP_AGREGAR)){
            System.out.println("Abiendo menu...");
            
            
            va.llenarComboBoxPais(Persistencia.getPaises());
            va.llenarComboBoxEspecie(Persistencia.getEspecies());
            String especieSeleccionada = va.getEspecie();
            System.out.println(especieSeleccionada);
            actualizarEspecie(especieSeleccionada);
           // va.llenarComboBoxSector(Persistencia.getSectores());
            
            
            
          if(!controladorAsignado){
           va.setControlador(this);
           controladorAsignado = true;
           
           
         }
            va.ejecutar(); 
        }
        
        if(e.getActionCommand().equals(vp.OP_LISTADO)){
          vl.cargarAnimales(obtenerLista());
          vl.ejecutar();
        
        }
        
        if(e.getActionCommand().equals(va.OPC_GUARDAR)){
            double peso = va.getPeso();
            int edad = va.getEdad();
            String nombreEspecie = va.getEspecie();
            Especie especie = Persistencia.buscarEspeciePorNombre(nombreEspecie);
            String nombrePais = va.getPais();
            Pais pais = Persistencia.buscarPais(nombrePais);
            int numSector = Integer.parseInt(va.getSector());
            Sector sector= Persistencia.buscarSector(numSector);
            String tipoAlimentacion = va.getAlimentacion();
            int valorFijo = va.getValorFijo();
            
            System.out.println(especie.toString());
            if (tipoAlimentacion.equals("CARNIVORO")) {
                try {
                    valorFijo=0;
                    Mamifero nuevo = new Carnivoro(edad, peso, especie, sector, pais);  
                    Persistencia.agregarAnimal(nuevo);
                    System.out.println("agregado correctamente");
                
                } catch (InvalidPropertiesFormatException ex) {
                    System.out.println("error");
                }
            }  
           if(tipoAlimentacion.equals("HERBIVORO")){
             try{
               Mamifero nuevo = new Herbivoro(edad,peso,especie,sector,valorFijo,pais);
               Persistencia.agregarAnimal(nuevo);
                 System.out.println("agregado");
             }catch(InvalidPropertiesFormatException ex){System.out.println("error");}
            }
        }
        
    }
    
    
  public void actualizarEspecie(String especie){
    
   Especie esp= Persistencia.buscarEspeciePorNombre(especie);
   
   if(esp != null){
    ArrayList<Sector> sectores = Persistencia.obtenerSectoresDisponibles(esp);
    va.llenarComboBoxSector(sectores);
   
   }else{
       System.out.println("Especie no encontrada");
   }
  }
    
    
  
    
}