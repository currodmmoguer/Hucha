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
import java.awt.event.WindowAdapter;
import static java.lang.System.exit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Cuenta;
import modelo.GestionDB;
import modelo.Movimiento;
import vista.CambiarUsuario;
import vista.NuevoMovimiento;
import vista.NuevoUsuario;
import vista.VistaGraficas;
import vista.VistaLogin;
import vista.VistaPrincipal;

/**
 *
 * @author curro
 */
public class ControladorPrincipal extends WindowAdapter implements ActionListener {

    private static final String DB = "db/hucha.oo";
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
    
    private VistaPrincipal vista;
    private JFrame ventana;
    public String usuario;
    private ArrayList<Movimiento> movimientos;

    public ControladorPrincipal(VistaPrincipal v, JFrame ventana) {
        vista = v;
        this.ventana = ventana;
        if (GestionDB.estaVacia())
            nuevoUsuario();
        else
            login();
        
        if (usuario == null)
            exit(0);

        mostrarDatos();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "ACTUALIZAR":
                mostrarDatos();
                break;
            case "NUEVOMOVIMIENTO":
                nuevoMovimiento();
                break;
            case "MOV-TODOS":
                vista.addModelo(getDatosTabla());
                break;
            case "MOV-INGRESOS":
                vista.addModelo(getDatosTablaIngresos());
                break;
            case "MOV-EXTRACCIONES":
                vista.addModelo(getDatosTablaExtracciones());
                break;
            case "PERIODO":
                mostrarIngresosYGastos();
                break;
            case "NUEVOUSUARIO":
                nuevoUsuario();
                break;
            case "CAMBIARUSUARIO":
                cambiarUsuario();
                break;
            case "VERMAS":
                vermas();
                break;
            case "SALIR":
                ventana.dispose();
                break;
        }

    }


    
    private void cambiarUsuario(){
        JDialog dialogo = new JDialog();
        CambiarUsuario panel = new CambiarUsuario();
        panel.addControlador(new ControladorCambioCuenta(panel, this, dialogo));
        dialogo.setModal(true);
        dialogo.add(panel);
        dialogo.setTitle("Cambio de usuario");
        dialogo.pack();
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setModal(true);
        dialogo.setVisible(true);
    }
    

    private void nuevoUsuario(){
        JDialog dialogo = new JDialog();
        NuevoUsuario panel = new NuevoUsuario();
        panel.addControlador(new ControladorNuevoUsuario(panel, this, dialogo));
        dialogo.setModal(true);
        dialogo.add(panel);
        dialogo.setTitle("Nuevo usuario");
        dialogo.pack();
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setModal(true);
        dialogo.setVisible(true);
    }
 



    /**
     * Muestra todos los datos de la cuenta indicada
     */
    protected void mostrarDatos() {
        vista.setUsuario(usuario);
        mostrarIngresosYGastos();
        vista.setSaldo(GestionDB.getSaldoDeCuenta(usuario));
        vista.addModelo(getDatosTabla());
    }

    /**
     * Obtiene los ingresos y gastos de dicho usuario y los muestra
     */
    private void mostrarIngresosYGastos() {
        double ingresos = 0, gastos = 0;
        

        switch (vista.getPeriodo()) {
            case "Anual":
                ingresos = GestionDB.mostrarIngresosUltimoAnno(usuario);
                gastos = GestionDB.mostrarGastosUltimoAnno(usuario);
                vista.setLabelPeriodo("Hace 1 año");
                break;
            case "Trimestral":
                ingresos = GestionDB.mostrarIngresosUltimoTrimestre(usuario);
                gastos = GestionDB.mostrarGastosUltimoTrimestre(usuario);
                vista.setLabelPeriodo("Hace 3 meses");
                break;
            case "Mensual":
                ingresos = GestionDB.mostrarIngresosUltimoMes(usuario);
                gastos = GestionDB.mostrarGastosUltimoMes(usuario);
                vista.setLabelPeriodo("Hace 1 mes");
                break;
        }
        
        vista.setDiferencia(ingresos - (-gastos));
        vista.setGastos(-(gastos));
        vista.setIngresos(ingresos);
    }

    /**
     * Obtiene los todos los movimientos de una cuenta
     * @return array bidimensional con los datos
     */
    private Object[][] getDatosTabla() {
        List<Movimiento> movimientos = GestionDB.getMovimientos(usuario).stream().sorted((m1, m2) -> m1.compareTo(m2)).collect(Collectors.toList());
        Object[][] datosTablas = new Object[movimientos.size()][3];
        int contador = 0;

        for (Movimiento movimiento : movimientos) {
            datosTablas[contador][0] = movimiento.getFecha().format(FORMATO_HORA);
            datosTablas[contador][1] = movimiento.getConcepto();
            datosTablas[contador][2] = movimiento.getImporte();
            contador++;
        }

        return datosTablas;
    }

    /**
     * Obtiene los ingresos de una cuenta
     * @return array bidimensional con los datos
     */
    private Object[][] getDatosTablaIngresos() {
        List<Movimiento> movimientos = GestionDB.getMovimientosIngresos(usuario).stream().sorted((m1, m2) -> m1.compareTo(m2)).collect(Collectors.toList());
        Object[][] datosTablas = new Object[movimientos.size()][3];
        int contador = 0;

        for (Movimiento movimiento : movimientos) {
            datosTablas[contador][0] = movimiento.getFecha().format(FORMATO_HORA);
            datosTablas[contador][1] = movimiento.getConcepto();
            datosTablas[contador][2] = movimiento.getImporte();
            contador++;
        }

        return datosTablas;
    }
    
    
    /**
     * Obtiene los gastos de una cuenta
     * @return array bidimensional con los datos
     */
    private Object[][] getDatosTablaExtracciones() {
        List<Movimiento> movimientos = GestionDB.getMovimientosExtracciones(usuario).stream().sorted((m1, m2) -> m1.compareTo(m2)).collect(Collectors.toList());
        Object[][] datosTablas = new Object[movimientos.size()][3];
        int contador = 0;

        for (Movimiento movimiento : movimientos) {
            datosTablas[contador][0] = movimiento.getFecha().format(FORMATO_HORA);
            datosTablas[contador][1] = movimiento.getConcepto();
            datosTablas[contador][2] = movimiento.getImporte();
            contador++;
        }

        return datosTablas;
    }

    /**
     * Crea un nuevo movimiento en la cuenta
     */
    public void nuevoMovimiento() {
        JDialog dialogo = new JDialog();
        NuevoMovimiento panel = new NuevoMovimiento();
        panel.addManejador(new ControladorNuevoMovimiento(panel, dialogo, this, usuario));
        dialogo.setModal(true);
        dialogo.add(panel);
        dialogo.setTitle("Nuevo movimiento");
        dialogo.pack();
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setLocationRelativeTo(null);
        dialogo.setResizable(false);
        dialogo.setModal(true);
        dialogo.setVisible(true);

    }

    //Abre un JDialog con detalles gráficos de la cuenta
    private void vermas() {
        JDialog dialogo = new JDialog();
        VistaGraficas vista = new VistaGraficas(usuario);
        vista.addControlador(new ControladorGraficas(vista));
        dialogo.add(vista);
        dialogo.setTitle("Detalles");
        dialogo.pack();
        dialogo.setVisible(true);
    }

    private void login() {
        JDialog dialogo = new JDialog();
        VistaLogin vista = new VistaLogin();
        vista.addControlador(new ControladorLogin(vista, this, dialogo));
        dialogo.add(vista);
        dialogo.setTitle("Acceso");
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.pack();
        dialogo.setLocationRelativeTo(null);
        dialogo.setModal(true);
        dialogo.setVisible(true);
    }



}
