/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import controller.ServerController;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import thread.ServerThread;

/**
 *
 * @author Stefan
 */
public class ServerForm extends javax.swing.JFrame {

    private  ServerThread serverThread;
      
    
    /**
     * Creates new form ServerForm
     */
    public ServerForm() {
        initComponents();
        setLocationRelativeTo(null);
        lblStatus.setVisible(false);
        lblStatus.setText("Server je ugasen!");
        btnPokreniServer.setVisible(false);
        btnZaustaviServer.setVisible(false);
        setTitle("Serverska forma");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPokreniServer = new javax.swing.JButton();
        btnZaustaviServer = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtBrojKlijenata = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        meniIzmeniKonf = new javax.swing.JMenu();
        meniIzmeni = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPokreniServer.setText("Pokreni server");
        btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniServerActionPerformed(evt);
            }
        });

        btnZaustaviServer.setText("Zaustavi server");
        btnZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZaustaviServerActionPerformed(evt);
            }
        });

        lblStatus.setText("Serverski status");

        jLabel1.setText("Unesite max broj klijenata:");

        txtBrojKlijenata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBrojKlijenataKeyPressed(evt);
            }
        });

        meniIzmeniKonf.setText("Konfiguracija baze");

        meniIzmeni.setText("Izmeni konfiguraciju");
        meniIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meniIzmeniActionPerformed(evt);
            }
        });
        meniIzmeniKonf.add(meniIzmeni);

        jMenuBar1.add(meniIzmeniKonf);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPokreniServer)
                            .addComponent(jLabel1))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBrojKlijenata, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnZaustaviServer)
                                .addGap(18, 18, 18))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(lblStatus)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtBrojKlijenata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(lblStatus)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreniServer)
                    .addComponent(btnZaustaviServer))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meniIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meniIzmeniActionPerformed

        BaseConfiguration bc = new BaseConfiguration(this, true);
        bc.setVisible(true);

    }//GEN-LAST:event_meniIzmeniActionPerformed

    private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokreniServerActionPerformed

        try{
            
       
        //if (serverThread == null) {
            int maxKlijenata;
           
            
            
            
            if (txtBrojKlijenata.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Polje za max broj klijenata ne sme biti prazno");
                return;
            }
            if (!txtBrojKlijenata.getText().matches("[\\d]+")) {
                JOptionPane.showMessageDialog(this, "Broj klijenata mora biti broj!!!");
                return;
            }
            
           
            maxKlijenata = Integer.parseInt(txtBrojKlijenata.getText());
            serverThread= new ServerThread(maxKlijenata);
            serverThread.start();
            
           
            btnPokreniServer.setEnabled(false);
            btnZaustaviServer.setEnabled(true);
            lblStatus.setText("Server je pokrenut");
            ServerController.getInstance().setTrenutnoUlogovani(new ArrayList<>());
             }
        catch(Exception exception){
            JOptionPane.showMessageDialog(this, exception.getMessage());
           
        }
        //}

    }//GEN-LAST:event_btnPokreniServerActionPerformed

    private void btnZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZaustaviServerActionPerformed

        if (serverThread != null) {

            try {
                serverThread.getServerskiSoket().close();
                if(serverThread.getServerskiSoket().isClosed())
                    System.out.println("da");
                JOptionPane.showMessageDialog(this, "Server je ugasen");
                lblStatus.setText("Server je ugasen!");
                btnPokreniServer.setEnabled(true);
                btnZaustaviServer.setEnabled(false);
            } catch (IOException ex) {
                Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_btnZaustaviServerActionPerformed

    private void txtBrojKlijenataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrojKlijenataKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            lblStatus.setVisible(true);
            btnPokreniServer.setVisible(true);
            btnZaustaviServer.setVisible(true);
            btnZaustaviServer.setEnabled(false);
        }


    }//GEN-LAST:event_txtBrojKlijenataKeyPressed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerForm().setVisible(true);
            }
        });
    }
    
    
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPokreniServer;
    private javax.swing.JButton btnZaustaviServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenuItem meniIzmeni;
    private javax.swing.JMenu meniIzmeniKonf;
    private javax.swing.JTextField txtBrojKlijenata;
    // End of variables declaration//GEN-END:variables
}
