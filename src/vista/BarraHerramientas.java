/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author curro
 */
public class BarraHerramientas extends JToolBar{
    private JButton todos, ingresos, extacciones, actualizar;
    
    public BarraHerramientas(){
        todos = new JButton();
        todos.setIcon(new ImageIcon(getClass().getResource("/img/todos.png")));
        todos.setToolTipText("Mostrar todos los movimientos");
        ingresos = new JButton();
        ingresos.setIcon(new ImageIcon(getClass().getResource("/img/ingresar.png")));
        ingresos.setToolTipText("Mostrar sólo ingresos");
        extacciones = new JButton();
        extacciones.setIcon(new ImageIcon(getClass().getResource("/img/extraer.png")));
        extacciones.setToolTipText("Mostrar sólo extraciones");
        actualizar = new JButton();
        actualizar.setIcon(new ImageIcon(getClass().getResource("/img/actualizar.png")));
        actualizar.setToolTipText("Actualizar");
        this.add(actualizar);
        this.add(todos);
        this.add(ingresos);
        this.add(extacciones);
        
    }
    
    public void addManejador(ActionListener al){
        todos.addActionListener(al);
        ingresos.addActionListener(al);
        extacciones.addActionListener(al);
        actualizar.addActionListener(al);
        todos.setActionCommand("MOV-TODOS");
        ingresos.setActionCommand("MOV-INGRESOS");
        extacciones.setActionCommand("MOV-EXTRACCIONES");
        actualizar.setActionCommand("ACTUALIZAR");
    }
    
}
