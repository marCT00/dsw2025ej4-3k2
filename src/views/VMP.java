
package views;


public interface VMP {
    public static String OPC_GUARDAR = "guardar nuevo animal";
    public static String OPC_LISTAR = "listar animales";
    public static String OPC_CALCULAR = "calcular comida";
    
    public void ejecutar();
    public void setControlador(Controlador control);
}
