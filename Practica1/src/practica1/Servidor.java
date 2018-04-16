package practica1;

import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.*;

public class Servidor extends JFrame {

    private JLabel etiquetaConexion;
    private JLabel etiquetaRemitente;
    private JLabel etiquetaRecibido;
    private JProgressBar barra;
    private JPanel panel;
    private String nombreArchivo;
    private String dirIp;
    private String tamArchivo;
    private String estatus;
    
    

    public Servidor() {
        super("SERVIDOR");
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Substance".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initComponents(String mensaje1, String mensaje2) {
        etiquetaConexion = new JLabel();
        etiquetaRemitente = new JLabel();
        etiquetaConexion.setText(mensaje1);
        etiquetaRemitente.setText(mensaje2);
        etiquetaRecibido = new JLabel("--");
        barra = new JProgressBar();
        barra.setValue(0);
        barra.setStringPainted(true);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(etiquetaConexion);
        panel.add(etiquetaRemitente);
        panel.add(etiquetaRecibido);
        panel.add(barra);
        add(panel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void procesar() {
        int contador = 0;
        try {
            ServerSocket servidor = new ServerSocket(1234);//Se crea el ServerSocket indicando el puerto 1234
            while (true) {
                nombreArchivo = "";
                dirIp = "";
                tamArchivo = "";
                estatus = "";
                
                Socket cliente = servidor.accept();//Se crea el socket cliente y se acepta la conexión cuando se encuentra.
                DataInputStream in = new DataInputStream(cliente.getInputStream());//Se indica el flujo de entrada como el flujo de entrada proveniente del cliente
                String nombre = in.readUTF();//Se lee el nombre del archivo.
                long tam = in.readLong();//Se lee el tamaño del archivo.
                DataOutputStream out = new DataOutputStream(new FileOutputStream(nombre));//Se crea el flujo de salida como flujo de salida de archivos con el nombre indicando anteriormente.
                int buffer = in.readInt();//Se lee el tamaño del buffer enviado desde el cliente.
                long recibidos = 0;
                int n, porcentaje;
                byte[] b = new byte[buffer];//Se crea el buffer con el tamaño especificado.
                if (contador == 0)//Para no mandar el constructor más de una vez
                {
                    initComponents("Conexion establecida desde " + cliente.getInetAddress() + ": " + cliente.getPort(), "Se recibe el archivo con el nombre: " + nombre + " y tamaño: " + tam);
                }
                etiquetaConexion.setText("Conexion establecida desde " + cliente.getInetAddress() + ": " + cliente.getPort());//Se imprime la procedencia de la conexión junto con el puerto.
                etiquetaRemitente.setText("Se recibe el archivo con el nombre: " + nombre + " y tamaño: " + tam);//Se indican los datos del archivo a recibir.
                etiquetaRecibido.setText("--");
                nombreArchivo = nombre;
                tamArchivo = ""+tam;
                dirIp = cliente.getInetAddress() + ":" + cliente.getPort();
                estatus = "OK";
                
                
                while (recibidos < tam) {
                    n = in.read(b);//Se lee el flujo de entrada con el buffer indicado
                    out.write(b, 0, n);//Se van guardando los datos recibidos
                    out.flush();//Se limpia el flujo de salida
                    recibidos += n;//Se indica el número de paquetes recibidos.
                    porcentaje = (int) (recibidos * 100 / tam);//Operación para determinar el porcentaje de envío.
                    if (recibidos % 5 == 0) {
                        barra.setValue(porcentaje);
                    }
                }
                Thread.sleep(1000);//Se espera un segundo para visualizar la información
                //etiquetaConexion.setText("--");
                //etiquetaRemitente.setText("--");
                etiquetaRecibido.setText("Archivo recibido");//Se indica que el archivo ha sido recibido completamente.
                MenuServidor.setValoresTabla(this);
                
                
                out.close();//Se cierran los flujos y los sockets
                in.close();
                cliente.close();
                contador++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getNombreArchivo(){
        return this.nombreArchivo;
    }
    
    public String getDireccionIP(){
        return this.dirIp;
    }
    
    public String getTamArchivo(){
        return this.tamArchivo;
    }
    
   public String getEstatus(){
       return this.estatus;
   }
    
    
    
    
}
