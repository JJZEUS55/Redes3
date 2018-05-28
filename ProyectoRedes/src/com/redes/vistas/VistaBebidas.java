/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redes.vistas;

import com.redes.bebidas.Bebida;
import com.redes.sockets.Servidor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author geoge
 */
public class VistaBebidas extends javax.swing.JFrame implements Runnable {

    private Thread hCoca;
    private Thread hFanta;
    private Thread hSprite;
    private Thread hServidor;
    private List<Bebida> listBebidas;
    private Bebida coca, fanta, sprite;
    private Servidor servidor;
    private int numCocas = 0, numFanta = 0, numSprite = 0;
    private static boolean ACTIVAR_MENU = false;
    private boolean enUsoDispensador = false;

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
        listBebidas = new ArrayList<Bebida>();
        cargarImagen("coca3");
        cargarImagen("fanta");
        cargarImagen("sprite");
        cargarCantidadBebidas();

        hServidor.start();

    }

    /**
     * Uso de Hilos
     */
    @Override
    public void run() {
        Thread hActual = Thread.currentThread();
        while (hActual == hServidor) {
            String pedidoBebida;
            servidor.iniciarServidor();
            pedidoBebida = servidor.getMensajeRecibido();
            tomarBebidaCliente(pedidoBebida);
        }

        while (hActual == hCoca && !(hCoca.isInterrupted())) {
            coca.setCantidadTomada(1);
            if (coca.getCantidadTomada() == 300 || coca.getCantidadTomada() == 600) {
                numCocas += 1;
                coca.aumentarNumBebida();
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
            if (fanta.getCantidadTomada() == 300 || fanta.getCantidadTomada() == 600) {
                numFanta++;
                fanta.aumentarNumBebida();
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
            sprite.setCantidadTomada(1);
            if (sprite.getCantidadTomada() == 300 || sprite.getCantidadTomada() == 600) {
                numSprite++;
                sprite.aumentarNumBebida();
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

    private void tomarBebidaCliente(String pedidoBebida) {
        switch (pedidoBebida) {
            case "Coca":
                hCoca = new Thread(this);
                hCoca.start();
                break;
            case "Fanta":
                hFanta = new Thread(this);
                hFanta.start();
                break;
            case "Sprite":
                hSprite = new Thread(this);
                hSprite.start();
                break;

            default:
                System.out.println("No mame prro");
                break;
        }
    }

    /**
     * Panel Reporte
     */
    public void addValoresTabla(Bebida bebida) {
        DefaultTableModel modelo = (DefaultTableModel) jTablaVentas.getModel();
        Object[] fila = new Object[4];
        fila[0] = bebida.getNombre();
        fila[1] = bebida.getPrecio();
        fila[2] = bebida.getNumBebida();
        fila[3] = bebida.getPrecio() * bebida.getNumBebida();
        modelo.addRow(fila);
        listBebidas.add(bebida);
        jTablaVentas.setModel(modelo);
    }

    public void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTablaVentas.getModel();
        modelo.setRowCount(0);
        jTablaVentas.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jIconoMenu = new javax.swing.JLabel();
        jMenuLateral = new javax.swing.JPanel();
        jIconoCasa = new javax.swing.JLabel();
        jIconoBebida = new javax.swing.JLabel();
        jIconoReporte = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanelTotal = new javax.swing.JPanel();
        jPanelContadorBebidas = new javax.swing.JPanel();
        jPanelCoca = new javax.swing.JPanel();
        jlblImagenCoca = new javax.swing.JLabel();
        jBarraCoca = new javax.swing.JProgressBar();
        jPanelFanta = new javax.swing.JPanel();
        jBarraFanta = new javax.swing.JProgressBar();
        jlblImagenFanta = new javax.swing.JLabel();
        jPanelSprite = new javax.swing.JPanel();
        jBarraSprite = new javax.swing.JProgressBar();
        jlblImagenSprite = new javax.swing.JLabel();
        jPanelReporte = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBebidas = new javax.swing.JComboBox<>();
        jbtnBusqueda = new javax.swing.JButton();
        jbtnMostarTodo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaVentas = new javax.swing.JTable();
        jPanelInicio = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor Bebidas");
        setBackground(new java.awt.Color(255, 51, 153));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelSuperior.setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dispensador de Bebidas");

        jIconoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/redes/vistas/imagenes/menuIcono.png"))); // NOI18N
        jIconoMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jIconoMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jIconoMenu)
                .addGap(287, 287, 287)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jIconoMenu)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1))
        );

        getContentPane().add(jPanelSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, -1));

        jMenuLateral.setBackground(new java.awt.Color(102, 102, 255));
        jMenuLateral.setPreferredSize(new java.awt.Dimension(200, 667));

        jIconoCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/redes/vistas/imagenes/iconoCasa.png"))); // NOI18N
        jIconoCasa.setLabelFor(jIconoBebida);
        jIconoCasa.setToolTipText("");
        jIconoCasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jIconoCasaMouseClicked(evt);
            }
        });

        jIconoBebida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/redes/vistas/imagenes/iconoBebida1.png"))); // NOI18N
        jIconoBebida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jIconoBebidaMouseClicked(evt);
            }
        });

        jIconoReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/redes/vistas/imagenes/IconoReporte.png"))); // NOI18N
        jIconoReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jIconoReporteMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Inicio");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bebidas");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Reporte");

        jMenuLateral.setVisible(false);

        javax.swing.GroupLayout jMenuLateralLayout = new javax.swing.GroupLayout(jMenuLateral);
        jMenuLateral.setLayout(jMenuLateralLayout);
        jMenuLateralLayout.setHorizontalGroup(
            jMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuLateralLayout.createSequentialGroup()
                .addGroup(jMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMenuLateralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jIconoBebida)
                            .addComponent(jIconoReporte)
                            .addComponent(jIconoCasa)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addGroup(jMenuLateralLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)))
                .addGap(33, 33, 33))
        );
        jMenuLateralLayout.setVerticalGroup(
            jMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuLateralLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jIconoCasa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(36, 36, 36)
                .addComponent(jIconoBebida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(56, 56, 56)
                .addComponent(jIconoReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        getContentPane().add(jMenuLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 84, 680));

        jPanelTotal.setPreferredSize(new java.awt.Dimension(1090, 669));

        jPanelContadorBebidas.setPreferredSize(new java.awt.Dimension(1110, 657));

        jPanelCoca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Coca-Cola", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 51, 51))); // NOI18N
        jPanelCoca.setPreferredSize(new java.awt.Dimension(318, 510));

        jlblImagenCoca.setPreferredSize(new java.awt.Dimension(600, 600));

        jBarraCoca.setForeground(new java.awt.Color(255, 51, 51));
        jBarraCoca.setMaximum(600);
        jBarraCoca.setOrientation(1);
        jBarraCoca.setValue(20);
        jBarraCoca.setPreferredSize(new java.awt.Dimension(250, 25));
        jBarraCoca.setStringPainted(true);

        javax.swing.GroupLayout jPanelCocaLayout = new javax.swing.GroupLayout(jPanelCoca);
        jPanelCoca.setLayout(jPanelCocaLayout);
        jPanelCocaLayout.setHorizontalGroup(
            jPanelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCocaLayout.createSequentialGroup()
                .addComponent(jlblImagenCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBarraCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanelCocaLayout.setVerticalGroup(
            jPanelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCocaLayout.createSequentialGroup()
                .addGroup(jPanelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBarraCoca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCocaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblImagenCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanelFanta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fanta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 153, 51))); // NOI18N
        jPanelFanta.setPreferredSize(new java.awt.Dimension(318, 510));
        jPanelFanta.setRequestFocusEnabled(false);

        jBarraFanta.setForeground(new java.awt.Color(255, 153, 51));
        jBarraFanta.setMaximum(600);
        jBarraFanta.setOrientation(1);
        jBarraFanta.setToolTipText("");
        jBarraFanta.setPreferredSize(new java.awt.Dimension(250, 25));
        jBarraFanta.setStringPainted(true);
        jBarraCoca.setForeground(Color.RED);

        jlblImagenFanta.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jPanelFantaLayout = new javax.swing.GroupLayout(jPanelFanta);
        jPanelFanta.setLayout(jPanelFantaLayout);
        jPanelFantaLayout.setHorizontalGroup(
            jPanelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFantaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblImagenFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBarraFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFantaLayout.setVerticalGroup(
            jPanelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFantaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblImagenFanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBarraFanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelSprite.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sprite", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 204, 51))); // NOI18N

        jBarraSprite.setForeground(new java.awt.Color(0, 204, 51));
        jBarraSprite.setMaximum(600);
        jBarraSprite.setOrientation(1);
        jBarraSprite.setPreferredSize(new java.awt.Dimension(250, 25));
        jBarraSprite.setStringPainted(true);
        jBarraCoca.setForeground(Color.RED);

        jlblImagenSprite.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout jPanelSpriteLayout = new javax.swing.GroupLayout(jPanelSprite);
        jPanelSprite.setLayout(jPanelSpriteLayout);
        jPanelSpriteLayout.setHorizontalGroup(
            jPanelSpriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSpriteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblImagenSprite, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBarraSprite, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelSpriteLayout.setVerticalGroup(
            jPanelSpriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSpriteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSpriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblImagenSprite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBarraSprite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelContadorBebidasLayout = new javax.swing.GroupLayout(jPanelContadorBebidas);
        jPanelContadorBebidas.setLayout(jPanelContadorBebidasLayout);
        jPanelContadorBebidasLayout.setHorizontalGroup(
            jPanelContadorBebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContadorBebidasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelCoca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelFanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelSprite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelContadorBebidasLayout.setVerticalGroup(
            jPanelContadorBebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContadorBebidasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelContadorBebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelCoca, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .addComponent(jPanelFanta, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .addComponent(jPanelSprite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanelReporte.setPreferredSize(new java.awt.Dimension(1110, 697));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setText("Bebida");

        jComboBebidas.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jComboBebidas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una bebida", "Coca Cola", "Fanta", "Sprite" }));
        jComboBebidas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBebidasItemStateChanged(evt);
            }
        });

        jbtnBusqueda.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jbtnBusqueda.setText("Busqueda");

        jbtnMostarTodo.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jbtnMostarTodo.setText("Mostrar Todo");
        jbtnMostarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMostarTodoActionPerformed(evt);
            }
        });

        jTablaVentas.getTableHeader().setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
        jTablaVentas.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jTablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre Bebida", "Costo por Bebida", "Numero Bebidas Vendidas", "Ventas Totales"
            }
        ));
        jTablaVentas.setRowMargin(2);
        jScrollPane1.setViewportView(jTablaVentas);

        javax.swing.GroupLayout jPanelReporteLayout = new javax.swing.GroupLayout(jPanelReporte);
        jPanelReporte.setLayout(jPanelReporteLayout);
        jPanelReporteLayout.setHorizontalGroup(
            jPanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReporteLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelReporteLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jbtnMostarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanelReporteLayout.setVerticalGroup(
            jPanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReporteLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBebidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnMostarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jPanelInicio.setPreferredSize(new java.awt.Dimension(1110, 697));
        jPanelInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/redes/vistas/imagenes/imagenInicio1.png"))); // NOI18N
        jPanelInicio.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 6, 695, 650));

        jPanelContadorBebidas.setVisible(false);
        jPanelReporte.setVisible(false);

        javax.swing.GroupLayout jPanelTotalLayout = new javax.swing.GroupLayout(jPanelTotal);
        jPanelTotal.setLayout(jPanelTotalLayout);
        jPanelTotalLayout.setHorizontalGroup(
            jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalLayout.createSequentialGroup()
                .addComponent(jPanelContadorBebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 126, Short.MAX_VALUE))
            .addGroup(jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTotalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanelReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTotalLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelTotalLayout.setVerticalGroup(
            jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalLayout.createSequentialGroup()
                .addComponent(jPanelContadorBebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
            .addGroup(jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTotalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanelReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTotalLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 76, 1000, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jIconoMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jIconoMenuMouseClicked
        // TODO add your handling code here:
        if (ACTIVAR_MENU == false) {
            ACTIVAR_MENU = true;
        } else {
            ACTIVAR_MENU = false;
        }
        jMenuLateral.setVisible(ACTIVAR_MENU);
    }//GEN-LAST:event_jIconoMenuMouseClicked

    private void jIconoBebidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jIconoBebidaMouseClicked
        // TODO add your handling code here:
        jPanelInicio.setVisible(false);
        jPanelReporte.setVisible(false);
        jPanelContadorBebidas.setVisible(true);

    }//GEN-LAST:event_jIconoBebidaMouseClicked

    private void jIconoReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jIconoReporteMouseClicked
        // TODO add your handling code here:
        jPanelInicio.setVisible(false);
        jPanelContadorBebidas.setVisible(false);
        jPanelReporte.setVisible(true);
        limpiarTabla();
        addValoresTabla(coca);
        addValoresTabla(fanta);
        addValoresTabla(sprite);
    }//GEN-LAST:event_jIconoReporteMouseClicked

    private void jComboBebidasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBebidasItemStateChanged
        if (evt.getItem().equals("Coca Cola")) {
            limpiarTabla();
            addValoresTabla(listBebidas.get(0));
        } else if (evt.getItem().equals("Fanta")) {
            limpiarTabla();
            addValoresTabla(listBebidas.get(1));
        } else if (evt.getItem().equals("Sprite")) {
            limpiarTabla();
            addValoresTabla(listBebidas.get(2));
        }
    }//GEN-LAST:event_jComboBebidasItemStateChanged

    private void jbtnMostarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMostarTodoActionPerformed
        limpiarTabla();
        addValoresTabla(listBebidas.get(0));
        addValoresTabla(listBebidas.get(1));
        addValoresTabla(listBebidas.get(2));
    }//GEN-LAST:event_jbtnMostarTodoActionPerformed

    private void jIconoCasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jIconoCasaMouseClicked
        // TODO add your handling code here:
        jPanelContadorBebidas.setVisible(false);
        jPanelReporte.setVisible(false);
        jPanelInicio.setVisible(true);
    }//GEN-LAST:event_jIconoCasaMouseClicked

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
    private javax.swing.JComboBox<String> jComboBebidas;
    private javax.swing.JLabel jIconoBebida;
    private javax.swing.JLabel jIconoCasa;
    private javax.swing.JLabel jIconoMenu;
    private javax.swing.JLabel jIconoReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jMenuLateral;
    private javax.swing.JPanel jPanelCoca;
    private javax.swing.JPanel jPanelContadorBebidas;
    private javax.swing.JPanel jPanelFanta;
    private javax.swing.JPanel jPanelInicio;
    private javax.swing.JPanel jPanelReporte;
    private javax.swing.JPanel jPanelSprite;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JPanel jPanelTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTablaVentas;
    private javax.swing.JButton jbtnBusqueda;
    private javax.swing.JButton jbtnMostarTodo;
    private javax.swing.JLabel jlblImagenCoca;
    private javax.swing.JLabel jlblImagenFanta;
    private javax.swing.JLabel jlblImagenSprite;
    // End of variables declaration//GEN-END:variables

}
