package practica1;

import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente extends JFrame {

    private JLabel etiqueta;
    private JProgressBar barra;
    private JPanel panel, panel2;
    private String nombreArchivo;
    private String dirIp;
    private String tamArchivo;

    public Cliente() {
        super("CLIENTE");
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

    public void initComponents(String mensaje) {
        etiqueta = new JLabel();
        etiqueta.setText(mensaje);
        barra = new JProgressBar();
        barra.setValue(0);
        barra.setStringPainted(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(etiqueta);
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
            nombreArchivo = "";
            dirIp = "";
            tamArchivo = "";
            JFileChooser seleccionador = new JFileChooser();
            seleccionador.setMultiSelectionEnabled(true);//Para la selección de múltiples archivos
            seleccionador.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int r = seleccionador.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File[] file = seleccionador.getSelectedFiles();//Se obtienen los archivos seleccionados
                for (int i = 0; i < file.length; i++) {
                    String archivo = file[i].getAbsolutePath();//Se obtiene la ruta del archivo
                    String nombre = file[i].getName();//Se obtiene el nombre del archivo
                    long tam = file[i].length();//Se obtiene el tamaño del archivo.
                    String direccion = JOptionPane.showInputDialog(null, "Ingrese la direccion IP");
                    String sTamanioBuffer = JOptionPane.showInputDialog(null, "Ingrese el tamanio del buffer");
                    int tamanioBuffer = Integer.parseInt(sTamanioBuffer);
                    Socket cliente = new Socket(direccion, 1234);//Se crea el socket con la dirección 127.1.0.255
                    cliente.setTcpNoDelay(true);//Se deshabilita el algoritmo Nagle.
                    DataOutputStream out = new DataOutputStream(cliente.getOutputStream());//Se crea el flujo de salida con el flujo de salida del cliente
                    DataInputStream in = new DataInputStream(new FileInputStream(archivo));//Se crea el flujo de entrada con el flujo de archivo de entrada del archivo a enviar.
                    out.writeUTF(nombre);//Se envía el nombre del archivo
                    out.flush();//Se limpia el flujo de salida
                    out.writeLong(tam);//Se envía el tamaño del archivo
                    out.flush();//Se limpia el flujo de salida
                    out.writeInt(tamanioBuffer);//Se envía el tamaño del buffer a utilizar
                    out.flush();//Se limpia el flujo de salida
                    byte[] b = new byte[tamanioBuffer];//Se crea el buffer con el tamaño especificado
                    long enviados = 0;//Contador de archivos enviados.
                    int porcentaje, n;
                    if (contador == 0)//Condición para no llamar más de una vez al constructor.
                    {
                        initComponents("\n\tArchivo: " + nombre + " Tamaño del archivo " + (i + 1) + " = " + tam + " Estado: Enviando");
                    }
                    etiqueta.setText("\n\tArchivo: " + nombre + " Tamaño del archivo " + (i + 1) + " = " + tam + " Estado: Enviando");//Se muestran los datos del archivo a enviar.
                    nombreArchivo = nombre;
                    tamArchivo = "" + tam;
                    dirIp = cliente.getInetAddress() + ":" + cliente.getPort();
                    MenuCliente.setValoresTabla(this);
                    while (enviados < tam) {
                        n = in.read(b);//Se lee desde el buffer
                        out.write(b, 0, n);//Se guarda desde el buffer
                        out.flush();//Se limpia el flujo de salida.
                        enviados += n;//Se indica el número de paquetes enviados
                        porcentaje = (int) (enviados * 100 / tam);//Operación para mostrar el porcentaje de envío del archivo.
                        if (enviados % 5 == 0) {
                            barra.setValue(porcentaje);
                        }
                    }
                    etiqueta.setText("\n\tArchivo: " + nombre + " Tamaño del archivo " + (i + 1) + " = " + tam + " Estado: Enviado");//Se indica cuando el archivo ha sido enviado completamente.

                    Thread.sleep(5000);//Se espera 5 segundos para darle tiempo al servidor de prepararse
                    out.close();
                    in.close();
                    cliente.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public String getDireccionIP() {
        return this.dirIp;
    }

    public String getTamArchivo() {
        return this.tamArchivo;
    }

}
