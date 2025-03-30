/*
 * Licencia C.C.
 */
package simuladoresp8266;

import java.io.*;
import java.net.*;

/**
 * @author daniel_zava
 */
public class Servidor extends Thread {

    public Servidor(Vista pv) {
        v = pv;
        try {
            while (listener == null) {
                listener = new ServerSocket(puerto);
            }
        } catch(IOException e) {
            System.out.println("Error de red, posible bloqueo por firewall");
            System.out.println(e); 
            System.exit(0);
        }
    }

    public String getIpDelServidor() {
        try{
            ip = InetAddress.getLocalHost();
        } catch(Exception e) {System.out.println(e); }
        return "IP = "+ ip.toString();
    }
    
    public String getPuertoDelServidor() {
        return "puerto = " + String.valueOf(puerto);
    }
    
    public void termina() {
        try {
            listener.close();
            System.exit(0);
        } catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                s = listener.accept();
                new ServidorHilo(s, v).start();
            } catch(IOException e){
                System.out.println(e);
            }
        }
    }

    // Declaración de variables
    int puerto = 1905;
    InetAddress ip;
    ServerSocket listener = null;
    Socket s = null;
    Vista v = null;
    
}
