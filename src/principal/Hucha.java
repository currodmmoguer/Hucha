/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import controlador.ControladorPrincipal;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import modelo.Cuenta;
import modelo.CuentaException;
import modelo.GestionDB;
import static modelo.GestionDB.abrirDB;
import vista.BarraHerramientas;
import vista.Menu;
import vista.VistaPrincipal;

/**
 *
 * @author DAM-2
 */
public class Hucha {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CuentaException {
        //insertar();
        
//        ObjectContainer db = GestionDB.abrirDB();
//        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta());
//        
//        for (Cuenta c: result){
//            System.out.println(c);
//        }
        
        
        
        JFrame ventana = new JFrame();
        VistaPrincipal vista = new VistaPrincipal();
        BarraHerramientas herramientas = new BarraHerramientas();
        Menu menu = new Menu();
        ControladorPrincipal controlador = new ControladorPrincipal(vista, ventana);
        
        vista.addManejador(controlador);
        herramientas.addManejador(controlador);
        menu.addManejador(controlador);
        
        ventana.setJMenuBar(menu);
        ventana.add(herramientas, BorderLayout.NORTH);
        ventana.add(vista);
        ventana.setTitle("Hucha");
        ventana.setSize(800, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

    }
    
    	public static void insertar() throws CuentaException {
		ObjectContainer db = abrirDB();
		Cuenta c1 = new Cuenta(10, "Curro", "pass");
		c1.nuevoMovimiento(5, null, true, LocalDateTime.of(2019, 1, 3, 14, 0));
		c1.nuevoMovimiento(42, null, true, LocalDateTime.of(2019, 1, 9, 14, 0));
		c1.nuevoMovimiento(65, "Ocio", false, LocalDateTime.of(2019, 2, 21, 14, 0));
		c1.nuevoMovimiento(1000, null, true, LocalDateTime.of(2019, 2, 6, 14, 0));
		c1.nuevoMovimiento(60, "Comida", false, LocalDateTime.of(2019, 3, 4, 14, 0));
		c1.nuevoMovimiento(54, null, true, LocalDateTime.of(2019, 3, 3, 14, 0));
		c1.nuevoMovimiento(78, "Ocio", false, LocalDateTime.of(2019, 4, 8, 14, 0));
		c1.nuevoMovimiento(6, null, true, LocalDateTime.of(2019, 4, 6, 14, 0));
		c1.nuevoMovimiento(7, null, true, LocalDateTime.of(2019, 5, 30, 14, 0));
		c1.nuevoMovimiento(25, "Fiesta", false, LocalDateTime.of(2019, 6, 12, 14, 0));
		c1.nuevoMovimiento(30, "Otros", false, LocalDateTime.of(2019, 6, 6, 14, 0));
		c1.nuevoMovimiento(54, null, true, LocalDateTime.of(2019, 7, 9, 14, 0));
		c1.nuevoMovimiento(50, null, true, LocalDateTime.of(2019, 7, 3, 14, 0));
		c1.nuevoMovimiento(25, null, true, LocalDateTime.of(2019, 7, 6, 14, 0));
		c1.nuevoMovimiento(4, "Tabaco", false, LocalDateTime.of(2019, 8, 3, 14, 0));
		c1.nuevoMovimiento(69, null, true, LocalDateTime.of(2019, 8, 4, 14, 0));
		c1.nuevoMovimiento(50, null, true, LocalDateTime.of(2019, 9, 3, 14, 0));
		c1.nuevoMovimiento(12, null, true, LocalDateTime.of(2019, 9, 4, 14, 0));
		c1.nuevoMovimiento(65, "Fiesta", false, LocalDateTime.of(2019, 10, 8, 14, 0));
		c1.nuevoMovimiento(35, null, true, LocalDateTime.of(2019, 10, 11, 14, 0));
		c1.nuevoMovimiento(47, null, true, LocalDateTime.of(2019, 11, 6, 14, 0));
		c1.nuevoMovimiento(95, null, true, LocalDateTime.of(2019, 11, 20, 14, 0));
		c1.nuevoMovimiento(78, null, true, LocalDateTime.of(2019, 12, 1, 14, 0));
		c1.nuevoMovimiento(36, "Comida", false, LocalDateTime.of(2019, 12, 30, 14, 0));
		c1.nuevoMovimiento(78, null, true, LocalDateTime.of(2019, 1, 6, 14, 0));
		c1.nuevoMovimiento(98, null, true, LocalDateTime.of(2019, 2, 8, 14, 0));
		c1.nuevoMovimiento(78, null, true, LocalDateTime.of(2019, 3, 7, 14, 0));
		c1.nuevoMovimiento(36, "Otros", false, LocalDateTime.of(2019, 4, 6, 14, 0));
		c1.nuevoMovimiento(78, null, true, LocalDateTime.of(2019, 5, 5, 14, 0));
		c1.nuevoMovimiento(69, null, true, LocalDateTime.of(2019, 6, 9, 14, 0));
		c1.nuevoMovimiento(20, null, true, LocalDateTime.of(2019, 7, 7, 14, 0));
		c1.nuevoMovimiento(40, "Gasolina", false, LocalDateTime.of(2019, 8, 4, 14, 0));
		c1.nuevoMovimiento(69, null, true, LocalDateTime.of(2019, 9, 20, 14, 0));
		c1.nuevoMovimiento(80, null, true, LocalDateTime.of(2019, 10, 25, 14, 0));
		c1.nuevoMovimiento(45, null, true, LocalDateTime.of(2019, 11, 6, 14, 0));
		c1.nuevoMovimiento(10, null, true, LocalDateTime.of(2019, 12, 4, 14, 0));

		System.out.println(c1);
		db.store(c1);

		db.close();
	}
    
    

}
