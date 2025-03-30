/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladoresp8266;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author daniel_zavas
 */
public class ServidorHilo extends Thread {
    
    public ServidorHilo(Socket s, Vista v) {
        this.s = s;
        this.v = v;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
                salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                salida.println("Comandos que acepta esta versión:  "
                                + "prendeGPIO16  apagaGPIO16  prendeGPIO12  leeGPIO4  leeAnaA0");
                while(true) {
                    String str = entrada.readLine();

                    if(str.equals("prendeGPIO16")) {
                        v.prendeSalidaDigital();
                    }
                    if(str.equals("apagaGPIO16")) {
                        v.apagaSalidaDigital();
                    }
                    if(str.equals("leeGPIO4")) {
                        salida.println(v.leeEntradaDigital());
                    }
                    if(str.equals("leeAnaA0")) {
                        salida.println( (v.leeEntradaAnalogica()*1023)/1000 );
                    }
                    if(str.equals("prendeGPIO12")) {
                        salida.println("Ingrese valor entre 0 y 255");
                        str = entrada.readLine();
                        v.grafica(Integer.parseInt(str));
                    }
                }
            } catch(IOException e){System.out.println(e); }
        }
    }
    
    Socket s;
    BufferedReader entrada;
    PrintWriter salida;
    Vista v = null;

}
