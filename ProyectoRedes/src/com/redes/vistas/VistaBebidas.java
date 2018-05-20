/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.vistas;

import com.redes.bebidas.Bebida;
import com.redes.bebidas.ProgresoBarra;
import com.redes.sockets.Servidor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.V;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.Painter;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author geoge
 */
public class VistaBebidas extends javax.swing.JFrame implements Runnable {

    private Thread hCoca;
    private Thread hFanta;
    private Thread hSprite;
    private Thread hServidor;
    private Bebida coca, fanta, sprite;
    private Servidor servidor;

    String prueba;

    /**
     * Creates new form VistaBebidas
     */
    public VistaBebidas() {
        initComponents();
        servidor = new Servidor();
        hServidor = new Thread(this);        
        coca = new Bebida("Coca Cola", 16, 600);
        fanta = new Bebida("Fanta", 13, 600);
        sprite = new Bebida("Sprite", 11, 600);
        cargarImagen("coca3");
        cargarImagen("fanta");
        cargarImagen("sprite");
        cargarCantidadBebidas();
        
        hServidor.start();

    }

    @Override
    public void run() {
        Thread hActual = Thread.currentThread();
        while(hActual == hServidor){
            String pedidoBebida;
            servidor.iniciarServidor();
            pedidoBebida = servidor.getMensajeRecibido();
            tomarBebidaCliente(pedidoBebida);
        }
        
        while (hActual == hCoca && !(hCoca.isInterrupted())) {
            coca.setCantidadTomada(1);
            if (coca.getCantidadTomada() == 300) {
                hCoca.interrupt();
                break;
            } else {
                jBarraCoca.setValue(coca.getCantidadActual());
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(VistaBebidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (hActual == hFanta && !(hFanta.isInterrupted())) {
            fanta.setCantidadTomada(1);
            if (fanta.getCantidadTomada() == 300) {
                hFanta.interrupt();
                break;
            } else {
                jBarraFanta.setValue(fanta.getCantidadActual());
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(VistaBebidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (hActual == hSprite) {
            System.out.println("Estoy en hilo sprite");
            sprite.setCantidadTomada(1);
            if (sprite.getCantidadTomada() == 300) {
                hSprite.interrupt();
                break;
            } else {
                jBarraSprite.setValue(sprite.getCantidadActual());
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(VistaBebidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cargarCantidadBebidas() {
        jBarraCoca.setValue(coca.getCantidadActual());
        jBarraFanta.setValue(fanta.getCantidadActual());
        jBarraSprite.setValue(sprite.getCantidadActual());
    }

    public void cargarImagen(String bebida) {
        ImageIcon icon = new ImageIcon(getClass().getResource("imagenes/" + bebida + ".png"));
        Image redimensionado;
        if (bebida.equals("coca3")) {
            redimensionado = icon.getImage().getScaledInstance(209, 600, Image.SCALE_SMOOTH);
            jlblImagenCoca.setIcon(new ImageIcon(redimensionado));
        } else if (bebida.equals("fanta")) {
            redimensionado = icon.getImage().getScaledInstance(254, 600, Image.SCALE_SMOOTH);
            jlblImagenFanta.setIcon(new ImageIcon(redimensionado));
        } else {
            redimensionado = icon.getImage().getScaledInstance(228, 600, Image.SCALE_SMOOTH);
            jlblImagenSprite.setIcon(new ImageIcon(redimensionado));
        }

    }

    private void tomarBebidaCliente(String pedidoBebida){
        switch (pedidoBebida) {
            case "coca":
                hCoca = new Thread(this);
                hCoca.start();
                break;
            case "fanta":
                hFanta = new Thread(this);
                hFanta.start();
                break;
            case "sprite":
                hSprite = new Thread(this);
                hSprite.start();
                break;

            default:
                System.out.println("No mame prro");
                break;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlblImagenCoca = new javax.swing.JLabel();
        jBarraCoca = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jBarraFanta = new javax.swing.JProgressBar();
        jlblImagenFanta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jBarraSprite = new javax.swing.JProgressBar();
        jlblImagenSprite = new javax.swing.JLabel();
        jbtnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor Bebidas");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Servidor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Coca-Cola", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 51, 51))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(318, 510));

        jlblImagenCoca.setPreferredSize(new java.awt.Dimension(600, 600));

        jBarraCoca.setForeground(new java.awt.Color(255, 51, 51));
        jBarraCoca.setMaximum(600);
        jBarraCoca.setOrientation(1);
        jBarraCoca.setValue(20);
        jBarraCoca.setPreferredSize(new java.awt.Dimension(250, 25));
        jBarraCoca.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jlblImagenCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBarraCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBarraCoca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblImagenCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fanta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 153, 51))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(318, 510));
        jPanel2.setRequestFocusEnabled(false);

        jBarraFanta.setForeground(new java.awt.Color(255, 153, 51));
        jBarraFanta.setMaximum(600);
        jBarraFanta.setOrientation(1);
        jBarraFanta.setToolTipText("");
        jBarraFanta.setPreferredSize(new java.awt.Dimension(250, 25));
        jBarraFanta.setStringPainted(true);
        jBarraCoca.setForeground(Color.RED);

        jlblImagenFanta.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblImagenFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBarraFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblImagenFanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBarraFanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sprite", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 204, 51))); // NOI18N

        jBarraSprite.setForeground(new java.awt.Color(0, 204, 51));
        jBarraSprite.setMaximum(600);
        jBarraSprite.setOrientation(1);
        jBarraSprite.setPreferredSize(new java.awt.Dimension(250, 25));
        jBarraSprite.setStringPainted(true);
        jBarraCoca.setForeground(Color.RED);

        jlblImagenSprite.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblImagenSprite, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBarraSprite, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblImagenSprite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBarraSprite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jbtnReporte.setText("Reporte");
        jbtnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(306, 306, 306)
                        .addComponent(jbtnReporte)
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbtnReporte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnReporteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaBebidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaBebidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaBebidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaBebidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaBebidas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jBarraCoca;
    private javax.swing.JProgressBar jBarraFanta;
    private javax.swing.JProgressBar jBarraSprite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbtnReporte;
    private javax.swing.JLabel jlblImagenCoca;
    private javax.swing.JLabel jlblImagenFanta;
    private javax.swing.JLabel jlblImagenSprite;
    // End of variables declaration//GEN-END:variables

}
