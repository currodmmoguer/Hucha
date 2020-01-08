/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import modelo.CuentaException;
import modelo.GestionDB;
import vista.NuevoUsuario;

/**
 *
 * @author curro
 */
public class ControladorNuevoUsuario implements ActionListener{
    
    
    private NuevoUsuario vista;
    private JDialog dialog;
    private ControladorPrincipal cp;
    
    
    public ControladorNuevoUsuario(NuevoUsuario v, ControladorPrincipal cp, JDialog dialog){
        vista = v;
        this.cp = cp;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        
        if (comando.equalsIgnoreCase("CREAR")){
            if (vista.passIguales()){
            try {
                GestionDB.crearCuenta(vista.getUser(), vista.getPass(), vista.getSaldo());
                cp.usuario = vista.getUser();
                cp.mostrarDatos();
                dialog.dispose();
            } catch (CuentaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al crear usuario", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña debe coincidir", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (comando.equalsIgnoreCase("CERRAR")){
            dialog.dispose();
        }
        
        
    }
    
    
}
