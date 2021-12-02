
import java.rmi.RemoteException;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author diana
 */
public class VChat extends javax.swing.JDialog {

    /**
     * Creates new form VChat
     */
    private Usuario usuario;
    private VAutentificacion padre;
    private ServerInterface serv;
    private Usuario amigoChat;

    public VChat(java.awt.Frame padre, ServerInterface serv) {
        super(padre);

        initComponents();
        this.amigoChat = null;
        this.padre = (VAutentificacion) padre;
        this.serv = serv;
        enviar.setEnabled(false);

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        saludoCliente.setText("¡Hola " + usuario.getNombre().toUpperCase() + "!");
        ModeloTablaUsuarios chats;
        chats = (ModeloTablaUsuarios) jTablaAmigos.getModel();

        for (String nombre : usuario.getAmigos().keySet()) {
            chats.anadirFila(nombre);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablaAmigos = new javax.swing.JTable();
        saludoCliente = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jMensajeEnviar = new javax.swing.JTextPane();
        solicitudes = new javax.swing.JButton();
        enviar = new javax.swing.JButton();
        jchatear = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jChat = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(637, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(244, 249, 255));
        jPanel1.setFont(new java.awt.Font("OCR A Extended", 0, 16)); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 0));

        jScrollPane4.setBackground(new java.awt.Color(244, 249, 255));
        jScrollPane4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane4.setForeground(new java.awt.Color(244, 249, 255));

        jTablaAmigos.setOpaque(true);
        jTablaAmigos.setBackground(new java.awt.Color(252, 246, 220));
        jTablaAmigos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTablaAmigos.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jTablaAmigos.setForeground(new java.awt.Color(0, 0, 0));
        jTablaAmigos.setModel(new ModeloTablaUsuarios());
        jTablaAmigos.setGridColor(new java.awt.Color(244, 249, 255));
        jTablaAmigos.setSelectionBackground(new java.awt.Color(223, 212, 171));
        jTablaAmigos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTablaAmigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaAmigosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTablaAmigos);

        saludoCliente.setFont(new java.awt.Font("Segoe UI Black", 0, 30)); // NOI18N
        saludoCliente.setText("¡Hola Usuario!");

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 30)); // NOI18N
        jLabel10.setText("CONECTADOS:");

        jPanel2.setBackground(new java.awt.Color(244, 242, 255));

        jMensajeEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMensajeEnviarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMensajeEnviarKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jMensajeEnviar);

        solicitudes.setBackground(new java.awt.Color(252, 246, 220));
        solicitudes.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        solicitudes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-invitación-30.png"))); // NOI18N
        solicitudes.setText("SOLICITUDES");
        solicitudes.setToolTipText("");
        solicitudes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitudesActionPerformed(evt);
            }
        });

        enviar.setBackground(new java.awt.Color(244, 242, 255));
        enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-enviar-mensaje-48.png"))); // NOI18N
        enviar.setBorder(null);
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        jchatear.setFont(new java.awt.Font("Segoe UI Black", 0, 30)); // NOI18N
        jchatear.setText("CHAT:");

        jChat.setBackground(new java.awt.Color(252, 246, 220));
        jChat.setColumns(20);
        jChat.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jChat.setForeground(new java.awt.Color(0, 0, 0));
        jChat.setRows(5);
        jScrollPane1.setViewportView(jChat);

        jButton1.setBackground(new java.awt.Color(244, 242, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-estoy-aquí-48.png"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(244, 242, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-me-gusta-48.png"))); // NOI18N
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(244, 242, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-globos-de-fiesta-48.png"))); // NOI18N
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(244, 242, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-estrellas-fugaces-48.png"))); // NOI18N
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jchatear)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchatear)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviar))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saludoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saludoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        // TODO add your handling code here:
        jChat.append(usuario.getNombre() + ":  " + jMensajeEnviar.getText() + "\n");
        try {
            amigoChat.getInterfaz().recibirMensaje(usuario.getNombre() + ":  " + jMensajeEnviar.getText() + "\n", usuario.getNombre());
            amigoChat.setConversacion(usuario.getNombre() + ":  " + jMensajeEnviar.getText() + "\n");
        } catch (RemoteException ex) {
            Logger.getLogger(VChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_enviarActionPerformed
    public void recibirMensaje(String mensaje, String nombreU) {
        usuario.getAmigos().get(nombreU).setConversacion(mensaje);

        this.mostrarMensaje(mensaje, nombreU);
    }

    public void mostrarMensaje(String mensaje, String nombreU) {
        if (amigoChat != null) {
            if (amigoChat.getNombre().equals(nombreU)) {
                jChat.append(mensaje);
            }
        }
    }

    private void solicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitudesActionPerformed
        // TODO add your handling code here:
        VSolicitudes sol;
        try {
            sol = new VSolicitudes(this, usuario, serv);
            sol.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(VChat.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_solicitudesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            this.serv.unregisterForCallback(usuario.getInterfaz(), usuario);
        } catch (RemoteException ex) {
            Logger.getLogger(VChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTablaAmigosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaAmigosMouseClicked
        // TODO add your handling code here:

        ModeloTablaUsuarios amigosUsuario;
        amigosUsuario = (ModeloTablaUsuarios) jTablaAmigos.getModel();
        this.amigoChat = usuario.getAmigos().get((String) amigosUsuario.getValueAt(jTablaAmigos.getSelectedRow(), 0));
        jchatear.setText("Chatea con " + amigoChat.getNombre().toUpperCase());
        jChat.setText("");
        this.mostrarMensaje(amigoChat.getConversacion(), amigoChat.getNombre());

    }//GEN-LAST:event_jTablaAmigosMouseClicked

    private void jMensajeEnviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMensajeEnviarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMensajeEnviarKeyPressed

    private void jMensajeEnviarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMensajeEnviarKeyReleased
        // TODO add your handling code here:
        if (jMensajeEnviar.getText().isEmpty() || this.amigoChat == null) {
            enviar.setEnabled(false);
        } else {
            enviar.setEnabled(true);
        }
    }//GEN-LAST:event_jMensajeEnviarKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jChat.append(usuario.getNombre() + ":  " + "❤" + "\n");

        try {
            amigoChat.getInterfaz().recibirMensaje(usuario.getNombre() + ":  " + "❤" + "\n", usuario.getNombre());
            amigoChat.setConversacion(usuario.getNombre() + ":  " + "❤" + "\n");
        } catch (RemoteException ex) {
            Logger.getLogger(VChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        jChat.append(usuario.getNombre() + ":  " + "👋" + "\n");

        try {
            amigoChat.getInterfaz().recibirMensaje(usuario.getNombre() + ":  " + "👋" + "\n", usuario.getNombre());
            amigoChat.setConversacion(usuario.getNombre() + ":  " + "👋" + "\n");
        } catch (RemoteException ex) {
            Logger.getLogger(VChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        jChat.append(usuario.getNombre() + ":  " + "✨" + "\n");

        try {
            amigoChat.getInterfaz().recibirMensaje(usuario.getNombre() + ":  " + "✨" + "\n", usuario.getNombre());
            amigoChat.setConversacion(usuario.getNombre() + ":  " + "✨" + "\n");
        } catch (RemoteException ex) {
            Logger.getLogger(VChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jChat.append(usuario.getNombre() + ":  " + "🎉" + "\n");

        try {
            amigoChat.getInterfaz().recibirMensaje(usuario.getNombre() + ":  " + "🎉" + "\n", usuario.getNombre());
            amigoChat.setConversacion(usuario.getNombre() + ":  " + "🎉" + "\n");
        } catch (RemoteException ex) {
            Logger.getLogger(VChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void actualizarNuevosChats(Usuario u) throws RemoteException {

        usuario.setAmigos(u);
        ModeloTablaUsuarios chats;
        chats = (ModeloTablaUsuarios) jTablaAmigos.getModel();
        chats.anadirFila(u.getNombre());

    }

    public void borrarChats(Usuario u) throws RemoteException {

        usuario.borrarAmigos(u);
        ModeloTablaUsuarios chats;

        chats = (ModeloTablaUsuarios) jTablaAmigos.getModel();
        chats.borrarTabla();
        chats.setFilas(usuario.getAmigos().keySet());

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JTextArea jChat;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JTextPane jMensajeEnviar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTablaAmigos;
    private javax.swing.JLabel jchatear;
    private javax.swing.JLabel saludoCliente;
    private javax.swing.JButton solicitudes;
    // End of variables declaration//GEN-END:variables
}
