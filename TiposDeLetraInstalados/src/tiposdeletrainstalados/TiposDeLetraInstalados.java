/*
 *  CC
 */
package tiposdeletrainstalados;

/**
 *
 * @author daniel_zava
 */
import java.awt.*;

public class TiposDeLetraInstalados {
    public static void main(String[] args) { 
        String[] fuentes = GraphicsEnvironment.
                                getLocalGraphicsEnvironment().
                                        getAvailableFontFamilyNames(); 
        for (String fuente : fuentes) {
            System.out.println(fuente); 
        }
    }
}