/*
 * Licencia C.C.
 */
package simuladoresp8266;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;

/**
 * @author daniel_zavas
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista2
     */
    public Vista() {
        initComponents();
        iniciaImagenes();
    }

    /**
     * initComponents   <-No se toca ;)
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Simulador parcial de ESP8266");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(690, 500));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciaImagenes() {
        
        imgESP8266 = new javax.swing.ImageIcon(getClass().getResource("imagenes/ESP8266.png")).getImage(); 
        imgESP8266redi = imgESP8266; //.getScaledInstance( 350,450,Image.SCALE_AREA_AVERAGING );
        /*
        tracker = new MediaTracker(this);
        tracker.addImage(imgESP8266, 1);
        try {
            tracker.waitForID(1);
        } catch (Exception e) {System.out.println(e);}
        */
        rojo = new javax.swing.ImageIcon(getClass().getResource("imagenes/rojito.png")).getImage(); 
        rojoredi = rojo.getScaledInstance( 40,40,Image.SCALE_AREA_AVERAGING );

        verde = new javax.swing.ImageIcon(getClass().getResource("imagenes/verde.png")).getImage(); 
        verderedi = verde.getScaledInstance( 40,40,Image.SCALE_AREA_AVERAGING );

        interruptorOn = new javax.swing.ImageIcon(getClass().getResource("imagenes/interruptorOn.png")).getImage(); 
        interruptorOnredi = interruptorOn.getScaledInstance( 50,20,Image.SCALE_AREA_AVERAGING );

        interruptorOff = new javax.swing.ImageIcon(getClass().getResource("imagenes/interruptorOff.png")).getImage(); 
        interruptorOffredi = interruptorOff.getScaledInstance( 50,20,Image.SCALE_AREA_AVERAGING );
        
        blanco = new javax.swing.ImageIcon(getClass().getResource("imagenes/blanco.png")).getImage(); 
        blancoredi = blanco.getScaledInstance( 50,20,Image.SCALE_AREA_AVERAGING );

        flecha = new javax.swing.ImageIcon(getClass().getResource("imagenes/flechaDerecha.png")).getImage(); 
        flecharedi = flecha.getScaledInstance( 50,20,Image.SCALE_AREA_AVERAGING );

        ejes = new javax.swing.ImageIcon(getClass().getResource("imagenes/ejesCoordenados.png")).getImage(); 
        ejesredi = ejes.getScaledInstance( 200,150,Image.SCALE_AREA_AVERAGING );

        bolita = verderedi;
        interruptor = interruptorOffredi;
        
        this.repaint();
        
    }

    @Override
    public void paint( Graphics g ) {
        if (primeraVez) {
            g.drawImage( imgESP8266redi,75,40,this );
            g.setFont(new Font("Monospaced", Font.BOLD, 12));
            g.drawString("Entrada", 6, 60);
            g.drawString("Analógica", 6, 78);
            g.drawString("Salida Digital", 472, 106);
            g.drawString("Entrada Digital", 482, 150);
            g.drawString("Salida P.W.M.", 527, 205);
            g.drawString("v", 470, 210);
            g.drawString("t", 675, 370);
            g.drawString("1ms", 553, 370);
            g.drawString("2ms", 636, 370);
            primeraVez = false;
          }

        g.drawImage( bolita,425,80,this );
        g.drawImage(interruptor, 425, 135, this);
        g.drawImage(ejesredi, 475, 210, this);
        g.drawImage(flecharedi, 430, 273, this);
        g.drawImage(blanco, 8, 85, this);
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.drawString(String.valueOf((float)entradaAnalogica/1000)+"v", 8, 105);

        Graphics2D g2d = (Graphics2D) g;  // Graphics2D permite cambiar el trazo
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor (Color.red);
        g2d.drawLine(485, 248, 485+tOn, 248); // origen = 485, 388
        g2d.drawLine(485+tOn, 348, 485+tOn+tOff, 348);  // tOff
        g2d.drawLine(485+tOn+tOff, 248, 485+tOn+tOff+tOn, 248);
        g2d.drawLine(485+tOn+tOff+tOn, 348, 485+tOn+tOff+tOn+tOff, 348);

    }
    
    public void grafica(int prop) {
        if (prop > 255) prop = 255;
        if (prop < 0) prop = 0;
        tOn = prop * 80 / 255;
        tOff = 80 - tOn;
        System.out.println(tOn);
        this.repaint();
    }
 
    public void prendeSalidaDigital() {
        bolita = rojoredi;
        this.repaint();
    }
    
    public void apagaSalidaDigital() {
        bolita = verderedi;
        this.repaint();
    }
    
    public int leeEntradaDigital() {
        return interruptor == interruptorOnredi ? 1 : 0;
    }
    
    public void conmutaEntradaDigital() {
        if(interruptor == interruptorOnredi) interruptor = interruptorOffredi;
        else interruptor = interruptorOnredi;
        this.repaint();
    }
    
    public void cambiaEntradaAnalogica(int i) {
        entradaAnalogica = i;
        this.repaint();
    }
    
    public int leeEntradaAnalogica() {
        return entradaAnalogica;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    Image imgESP8266, imgESP8266redi;
    MediaTracker tracker;
    Image rojo, rojoredi, verde, verderedi, bolita;
    Image interruptorOn, interruptorOnredi, interruptorOff, interruptorOffredi, interruptor;
    Image blanco, blancoredi;
    Image flecha, flecharedi;
    Image ejes, ejesredi;
    int entradaAnalogica = 500, salidaAnalogica;
    int tOn, tOff;
    boolean primeraVez = true;
}
