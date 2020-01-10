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
import vista.NuevoMovimiento;

/**
 *
 * @author curro
 */
public class ControladorNuevoMovimiento implements ActionListener {

    NuevoMovimiento vista;
    JDialog dialog;
    String usuario;
    ControladorPrincipal cp;

    public ControladorNuevoMovimiento(NuevoMovimiento vista, JDialog dialog, ControladorPrincipal cp, String usuario) {
        this.vista = vista;
        this.dialog = dialog;
        this.cp = cp;
        this.usuario = usuario;
        vista.setConceptos(GestionDB.getConceptos(usuario));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {

            if (comando.equals("INGRESAR")) {
                GestionDB.nuevoIngreso(vista.getImporte(), usuario);
            } else {

                if (vista.getConcepto() == null || vista.getConcepto().length() == 0) {
                    throw new CuentaException("Debes introducir un concepto");
                }

                GestionDB.nuevaExtraccion(vista.getImporte(), vista.getConcepto(), usuario);
            }
            dialog.dispose();
            cp.mostrarDatos();
        } catch (CuentaException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
