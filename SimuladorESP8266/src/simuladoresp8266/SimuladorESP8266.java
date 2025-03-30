/*
 * CC
*/
package simuladoresp8266;

/**
 * @author daniel_zavas
 */
public class SimuladorESP8266 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vista vista = new Vista();
        vista.setVisible(true);
                
        Servidor servidor = new Servidor(vista);
        servidor.start();
        
        Controles controles = new Controles(vista, servidor);
        controles.setVisible(true);
        
    }
    
}
