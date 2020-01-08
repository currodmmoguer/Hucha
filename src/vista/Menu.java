/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author curro
 */
public class Menu extends JMenuBar{
    
    private JMenu menuArchivo, menuOperacion;
    private JMenuItem nuevoUsuario, cambiarUsuario, salir;
    private JMenuItem nuevoMovimiento, actualizar;
    
    public Menu(){
        nuevoUsuario = new JMenuItem("Nuevo usuario");
        cambiarUsuario = new JMenuItem("Cambiar usuario");
        salir = new JMenuItem("Salir");
        menuArchivo = new JMenu("Archivo");
        nuevoMovimiento = new JMenuItem("Nuevo movimiento");
        actualizar = new JMenuItem("Actualizar");
        menuOperacion = new JMenu("Operación");
        
        menuArchivo.setMnemonic('A');
        menuArchivo.add(nuevoUsuario);
        menuArchivo.add(cambiarUsuario);
        menuArchivo.add(new JPopupMenu.Separator());
        menuArchivo.add(salir);
        
        menuOperacion.setMnemonic('O');
        menuOperacion.add(nuevoMovimiento);
        menuOperacion.add(actualizar);
        
        this.add(menuArchivo);
        this.add(menuOperacion);
    }
    
    public void addManejador(ActionListener al){
        nuevoUsuario.addActionListener(al);
        cambiarUsuario.addActionListener(al);
        salir.addActionListener(al);
        nuevoMovimiento.addActionListener(al);
        actualizar.addActionListener(al);
        nuevoUsuario.setActionCommand("NUEVOUSUARIO");
        cambiarUsuario.setActionCommand("CAMBIARUSUARIO");
        salir.setActionCommand("SALIR");
        nuevoMovimiento.setActionCommand("NUEVOMOVIMIENTO");
        actualizar.setActionCommand("ACTUALIZAR");
    }
}
