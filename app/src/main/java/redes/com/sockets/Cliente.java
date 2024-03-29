package redes.com.sockets;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends AsyncTask<String, Void, Void>{

    protected Socket cs;
    private String host;
    private int puerto;
    protected DataInputStream entrada;
    protected DataOutputStream salida;

    public Cliente(){
        host = "192.168.0.4";
        puerto = 3060;
    }

    public Cliente(String ip, int puerto){
        this.host = ip;
        this.puerto = puerto;
    }

    public void mandarMensajeServidor(String bebida){

        try {
            cs = new Socket(host, puerto);
            System.out.println("Cliente Iniciado");
            salida = new DataOutputStream(cs.getOutputStream());
            System.out.println("Escriba una bebida");
            salida.writeUTF(bebida);
            System.out.println("Se ha mandado el mensaje " + bebida);
        } catch (IOException ex) {
            System.out.println("Problema iniciar cliente " + ex.getMessage());
        }
    }

    @Override
    protected Void doInBackground(String... bebida) {
        mandarMensajeServidor(bebida[0]);
        return null;
    }
}
