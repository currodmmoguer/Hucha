/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import vista.VistaGraficas;

/**
 *
 * @author curro
 */
public class ControladorGraficas extends WindowAdapter implements ActionListener{
    private VistaGraficas vista;
    
    public ControladorGraficas(VistaGraficas vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equalsIgnoreCase("PERIODO")){
            vista.mostrarGrafica();
        }
    }


}
