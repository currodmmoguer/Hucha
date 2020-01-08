/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import modelo.Cuenta;
import modelo.CuentaException;
import modelo.GestionDB;
import vista.VistaLogin;

/**
 *
 * @author curro
 */
public class ControladorLogin extends KeyAdapter implements ActionListener {

    private VistaLogin vista;
    private ControladorPrincipal cp;
    private JDialog dialog;
    private ObjectContainer db;

    public ControladorLogin(VistaLogin vista, ControladorPrincipal cp, JDialog dialog) {
        this.vista = vista;
        this.cp = cp;
        this.dialog = dialog;
        db = GestionDB.abrirDB(1);
        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta());

        while (result.hasNext()) {
            vista.addUsuarioComboBox(result.next().getUser());
        }
        db.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("ACCEDER")) {
            acceso();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            acceso();
        }
    }


    
    

    private void acceso() {

        if (GestionDB.buscarCuenta(vista.getUsuario(), vista.getPass())) {

            cp.usuario = vista.getUsuario();
            System.out.println(vista.getUsuario());
            cp.mostrarDatos();
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Has introducido una contraseña errónea", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
