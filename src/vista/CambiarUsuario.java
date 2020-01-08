/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorCambioCuenta;
import java.awt.event.ActionListener;

/**
 *
 * @author curro
 */
public class CambiarUsuario extends javax.swing.JPanel {

    /**
     * Creates new form CambiarUsuario
     */
    public CambiarUsuario() {
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 20, 10));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Selecciona usuario");
        jPanel1.add(jLabel1);

        jPanel1.add(jComboBox1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Contraseña");
        jPanel1.add(jLabel2);
        jPanel1.add(jPasswordField1);

        add(jPanel1);

        jButton1.setText("Cambiar");
        add(jButton1);
    }// </editor-fold>//GEN-END:initComponents

    public void addUsuarioComboBox(String user) {
        jComboBox1.addItem(user);
    }
    
    public String getUsuario(){
        return jComboBox1.getSelectedItem().toString();
    }
    
    public String getPass(){
        return String.valueOf(jPasswordField1.getPassword());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables

    public void addControlador(ControladorCambioCuenta al) {
        jButton1.addActionListener(al);
        jButton1.setActionCommand("ACCEDER");
        jPasswordField1.addKeyListener(al);

    }
}
