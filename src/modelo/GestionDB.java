/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author curro
 */
public class GestionDB {

    private static final String DB = "db/hucha.oo";

    public static double getSaldoDeCuenta(String nombreUsuario) {
        ObjectContainer db = abrirDB(1);
        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(nombreUsuario));
        Double saldo = result.next().getSaldo();

        db.close();
        return saldo;
    }

    public static List<Movimiento> getMovimientos(String usuario) {
        ObjectContainer db = abrirDB();
        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));
        List<Movimiento> movimientos = null;
        movimientos = result.next().getMovimientos();
        db.close();
        return movimientos;
    }

    //Obtiene solo los ingresos
    public static List<Movimiento> getMovimientosIngresos(String usuario) {
        ObjectContainer db = abrirDB();
        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));
        List<Movimiento> movimientos = new ArrayList<Movimiento>();

        for (Movimiento movimiento : result.next().getMovimientos()) {
            if (movimiento.getImporte() > 0) {
                movimientos.add(movimiento);
            }
        }

        db.close();
        return movimientos;
    }

    //Obtiene solo los gastos
    public static List<Movimiento> getMovimientosExtracciones(String usuario) {
        ObjectContainer db = abrirDB();
        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));
        List<Movimiento> movimientos = new ArrayList<Movimiento>();

        for (Movimiento movimiento : result.next().getMovimientos()) {
            if (movimiento.getImporte() < 0) {
                movimientos.add(movimiento);
            }
        }

        db.close();
        return movimientos;
    }

//Mostrar ingresos y gastos
    // Ultimo mes
    public static double mostrarIngresosUltimoMes(String nombreUsuario) {
        ObjectContainer db = abrirDB();
        LocalDateTime haceUnMes = LocalDateTime.now().minusMonths(1);
        double total = 0;
        ObjectSet<Cuenta> resultCuenta = db.queryByExample(new Cuenta(nombreUsuario));

        total = resultCuenta.next().getMovimientos().stream()
                .filter(m -> m.getFecha().isAfter(haceUnMes) && m.getImporte() > 0)
                .mapToDouble(Movimiento::getImporte).sum();

        db.close();

        return total;
    }

    public static double mostrarGastosUltimoMes(String nombreUsuario) {
        ObjectContainer db = abrirDB();
        LocalDateTime haceUnMes = LocalDateTime.now().minusMonths(1);
        double total = 0;
        ObjectSet<Cuenta> resultCuenta = db.queryByExample(new Cuenta(nombreUsuario));

        total = resultCuenta.next().getMovimientos().stream()
                .filter(m -> m.getFecha().isAfter(haceUnMes) && m.getImporte() < 0)
                .mapToDouble(Movimiento::getImporte).sum();

        db.close();

        return total;
    }

    // Ultimo trimestre
    public static double mostrarIngresosUltimoTrimestre(String nombreUsuario) {
        ObjectContainer db = abrirDB();
        LocalDateTime haceTresMeses = LocalDateTime.now().minusMonths(3);
        double total = 0;
        ObjectSet<Cuenta> resultCuenta = db.queryByExample(new Cuenta(nombreUsuario));

        total = resultCuenta.next().getMovimientos().stream()
                .filter(m -> m.getFecha().isAfter(haceTresMeses) && m.getImporte() > 0)
                .mapToDouble(Movimiento::getImporte).sum();

        db.close();

        return total;
    }

    public static double mostrarGastosUltimoTrimestre(String nombreUsuario) {
        ObjectContainer db = abrirDB();
        LocalDateTime haceTresMeses = LocalDateTime.now().minusMonths(3);
        double total = 0;
        ObjectSet<Cuenta> resultCuenta = db.queryByExample(new Cuenta(nombreUsuario));

        total = resultCuenta.next().getMovimientos().stream()
                .filter(m -> m.getFecha().isAfter(haceTresMeses) && m.getImporte() < 0)
                .mapToDouble(Movimiento::getImporte).sum();

        db.close();
        return total;
    }

    // Ultimo año
    public static double mostrarIngresosUltimoAnno(String nombreUsuario) {
        ObjectContainer db = abrirDB();
        LocalDateTime haceUnAnno = LocalDateTime.now().minusYears(1);
        double total = 0;
        ObjectSet<Cuenta> resultCuenta = db.queryByExample(new Cuenta(nombreUsuario));

        total = resultCuenta.next().getMovimientos().stream()
                .filter(m -> m.getFecha().isAfter(haceUnAnno) && m.getImporte() > 0)
                .mapToDouble(Movimiento::getImporte).sum();

        db.close();

        return total;
    }

    public static double mostrarGastosUltimoAnno(String nombreUsuario) {
        ObjectContainer db = abrirDB();
        LocalDateTime haceUnAnno = LocalDateTime.now().minusYears(1);
        double total = 0;
        ObjectSet<Cuenta> resultCuenta = db.queryByExample(new Cuenta(nombreUsuario));

        total = resultCuenta.next().getMovimientos().stream()
                .filter(m -> m.getFecha().isAfter(haceUnAnno) && m.getImporte() < 0)
                .mapToDouble(Movimiento::getImporte).sum();

        db.close();
        return total;
    }

//Evolucion gastos e ingresos de distintos meses
    // Ultimo trimestre
    public static double[] evolucionIngresosTrimestre(String usuario) {
        String meses[] = new String[3];
        double ingresos[] = new double[3];
        double totalIngresos;
        ObjectContainer db = abrirDB();
        LocalDateTime fechaIni = null;
        LocalDateTime fechaFin = null;
        Cuenta c;

        for (int i = 0; i < meses.length; i++) {
            meses[i] = obtenerMes(LocalDateTime.now().getMonthValue() - i);
        }

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();
        for (int i = 0; i < 3; i++) {

            totalIngresos = 0;
            if (i == 0) {
                fechaIni = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), 1,
                        0, 0);
                fechaFin = LocalDateTime.now();
            } else {
                fechaIni = fechaIni.minusMonths(1);
                fechaFin = fechaIni.plusMonths(1).minusDays(1);
            }

            for (Movimiento m : c.getMovimientos()) {
                if (m.getFecha().isAfter(fechaIni) && m.getFecha().isBefore(fechaFin)) {
                    if (m.getImporte() > 0) {
                        totalIngresos += m.getImporte();
                    }
                }

            }

            ingresos[i] = totalIngresos;

        }

        db.close();
        return ingresos;

    }

    public static double[] evolucionGastosTrimestre(String usuario) {
        String meses[] = new String[3];
        double gastos[] = new double[3];
        double totalGastos;
        ObjectContainer db = abrirDB();
        LocalDateTime fechaIni = null;
        LocalDateTime fechaFin = null;
        Cuenta c;

        for (int i = 0; i < meses.length; i++) {
            meses[i] = obtenerMes(LocalDateTime.now().getMonthValue() - i);
        }

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();
        for (int i = 0; i < 3; i++) {
            totalGastos = 0;
            if (i == 0) {
                fechaIni = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), 1,
                        0, 0);
                fechaFin = LocalDateTime.now();
            } else {
                fechaIni = fechaIni.minusMonths(1);
                fechaFin = fechaIni.plusMonths(1).minusDays(1);
            }

            for (Movimiento m : c.getMovimientos()) {
                if (m.getFecha().isAfter(fechaIni) && m.getFecha().isBefore(fechaFin)) {
                    if (m.getImporte() < 0) {
                        totalGastos += -(m.getImporte());
                    }
                }

            }

            gastos[i] = totalGastos;

        }

        db.close();
        return gastos;
    }

    public static double[] evolucionIngresosSemestre(String usuario) {
        double ingresos[] = new double[6];
        double totalIngresos;
        ObjectContainer db = abrirDB();
        LocalDateTime fechaIni = null;
        LocalDateTime fechaFin = null;
        Cuenta c;

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();
        for (int i = 0; i < 6; i++) {

            totalIngresos = 0;
            if (i == 0) {
                fechaIni = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), 1,
                        0, 0);
                fechaFin = LocalDateTime.now();
            } else {
                fechaIni = fechaIni.minusMonths(1);
                fechaFin = fechaIni.plusMonths(1).minusDays(1);
            }

            for (Movimiento m : c.getMovimientos()) {
                if (m.getFecha().isAfter(fechaIni) && m.getFecha().isBefore(fechaFin)) {
                    if (m.getImporte() > 0) {
                        totalIngresos += m.getImporte();
                    }
                }

            }

            ingresos[i] = totalIngresos;

        }

        db.close();
        return ingresos;
    }

    public static double[] evolucionGastosSemestre(String usuario) {
        String meses[] = new String[6];
        double gastos[] = new double[6];
        double totalGastos;
        ObjectContainer db = abrirDB();
        LocalDateTime fechaIni = null;
        LocalDateTime fechaFin = null;
        Cuenta c;

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();
        for (int i = 0; i < 6; i++) {
            totalGastos = 0;
            if (i == 0) {
                fechaIni = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), 1,
                        0, 0);
                fechaFin = LocalDateTime.now();
            } else {
                fechaIni = fechaIni.minusMonths(1);
                fechaFin = fechaIni.plusMonths(1).minusDays(1);
            }

            for (Movimiento m : c.getMovimientos()) {
                if (m.getFecha().isAfter(fechaIni) && m.getFecha().isBefore(fechaFin)) {
                    if (m.getImporte() < 0) {
                        totalGastos += -(m.getImporte());
                    }
                }

            }

            gastos[i] = totalGastos;

        }

        db.close();
        return gastos;
    }

    public static double[] evolucionIngresosAnno(String usuario) {
        double ingresos[] = new double[12];
        double totalIngresos;
        ObjectContainer db = abrirDB();
        LocalDateTime fechaIni = null;
        LocalDateTime fechaFin = null;
        Cuenta c;

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();
        for (int i = 0; i < 12; i++) {
            totalIngresos = 0;
            if (i == 0) {
                fechaIni = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), 1,
                        0, 0);
                fechaFin = LocalDateTime.now();
            } else {
                fechaIni = fechaIni.minusMonths(1);
                fechaFin = fechaIni.plusMonths(1).minusDays(1);
            }

            for (Movimiento m : c.getMovimientos()) {
                if (m.getFecha().isAfter(fechaIni) && m.getFecha().isBefore(fechaFin)) {
                    if (m.getImporte() > 0) {
                        totalIngresos += m.getImporte();
                    }
                }

            }

            ingresos[i] = totalIngresos;

        }

        db.close();
        return ingresos;
    }

    public static double[] evolucionGastosAnno(String usuario) {
        double gastos[] = new double[12];
        double totalGastos;
        ObjectContainer db = abrirDB();
        LocalDateTime fechaIni = null;
        LocalDateTime fechaFin = null;
        Cuenta c;

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();
        for (int i = 0; i < 12; i++) {
            totalGastos = 0;
            if (i == 0) {
                fechaIni = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), 1,
                        0, 0);
                fechaFin = LocalDateTime.now();
            } else {
                fechaIni = fechaIni.minusMonths(1);
                fechaFin = fechaIni.plusMonths(1).minusDays(1);
            }

            for (Movimiento m : c.getMovimientos()) {
                if (m.getFecha().isAfter(fechaIni) && m.getFecha().isBefore(fechaFin)) {
                    if (m.getImporte() < 0) {
                        totalGastos += -(m.getImporte());
                    }
                }

            }

            gastos[i] = totalGastos;

        }

        db.close();
        return gastos;
    }

    /**
     * Obtiene el porcentaje de los conceptos utilizados
     *
     * @param usuario
     * @return Array bidimensional con el nombre del concepto y su porcentaje de
     * usabilidad
     */
    public static Object[][] distribucionGasto(String usuario) {
        ObjectContainer db = abrirDB();
        Cuenta c;
        List<String> conceptos;
        int[] totalConceptos;
        int contador = 0, contadorConceptosTotales = 0;
        Object[][] distribucion = null;
        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();
        conceptos = c.getMovimientos().stream().filter(m -> m.getConcepto() != null).map(m -> m.getConcepto())
                .distinct().collect(Collectors.toList());

        totalConceptos = new int[conceptos.size()];

        for (String concepto : conceptos) {
            for (Movimiento m : c.getMovimientos()) {
                if (m.getConcepto() != null && m.getConcepto().equals(concepto)) {
                    totalConceptos[contador] += 1;
                    contadorConceptosTotales++;
                }
            }
            contador++;
        }

        distribucion = new Object[2][totalConceptos.length];
        for (int i = 0; i < totalConceptos.length; i++) {
            distribucion[0][i] = conceptos.get(i);
            distribucion[1][i] = (double) (totalConceptos[i] * 100 / contadorConceptosTotales);

        }

        db.close();
        return distribucion;
    }

    //Obtiene todos los conceptos que tiene en los movimientos y devuelve una lista de ellos
    public static List<String> getConceptos(String usuario) {
        ObjectContainer db = abrirDB();
        List<String> conceptos;
        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        conceptos = result.next().getMovimientos().stream().filter(m -> m.getConcepto() != null).map(m -> m.getConcepto())
                .distinct().collect(Collectors.toList());
        db.close();
        return conceptos;
    }

    public static void nuevoIngreso(double importe, String usuario) {

        ObjectContainer db = abrirDB(true, false);
        Cuenta c;

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));

        c = result.next();

        try {
            c.nuevoMovimiento(importe, null, true);
            db.store(c);
        } catch (CuentaException ex) {

        } finally {
            db.close();
        }

    }

    public static void nuevaExtraccion(double importe, String concepto, String usuario) {

        ObjectContainer db = abrirDB(true, false);
        Cuenta c;

        ObjectSet<Cuenta> result = db.queryByExample(new Cuenta(usuario));
        c = result.next();
        try {

            c.nuevoMovimiento(importe, concepto, false);
            db.store(c);
        } catch (CuentaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            db.close();
        }

    }

//Crear cuenta
    public static void crearCuenta(String user, String pass, double saldo) throws CuentaException {
        if (user == null || user.length() == 0) {
            throw new CuentaException("No puedes dejar el nombre de cuenta vacío.");
        }

        ObjectContainer db = abrirDB();

        if (db.queryByExample(new Cuenta(user)).hasNext()) {
            db.close();
            throw new CuentaException("Ya existe la cuenta con nombre " + user);
        } else {
            db.store(new Cuenta(saldo, user, pass));
        }

        db.close();

    }

    public static boolean buscarCuenta(String user, String pass) {

        ObjectContainer db = abrirDB(1);
        boolean existe;
        try {
            existe = (db.queryByExample(new Cuenta(user, pass)).hasNext()) ? true : false;
        } catch (CuentaException ex) {
            existe = false;
        }
        db.close();

        return existe;

    }

    public static boolean estaVacia() {
        ObjectContainer db = abrirDB(1);
        boolean vacia;

        vacia = (db.queryByExample(new Cuenta()).hasNext()) ? false : true;

        db.close();

        return vacia;
    }

    public static String obtenerMes(int i) {
        String mes = null;
        switch (i) {
            case 1:
                mes = "ENE";
                break;
            case 2:
                mes = "FEB";
                break;
            case 3:
                mes = "MAR";
                break;
            case 4:
                mes = "ABR";
                break;
            case 5:
                mes = "MAY";
                break;
            case 6:
                mes = "JUN";
                break;
            case 7:
                mes = "JUL";
                break;
            case 8:
                mes = "AGO";
                break;
            case 9:
                mes = "SEP";
                break;
            case 10:
                mes = "OCT";
                break;
            case 11:
                mes = "NOV";
                break;
            case 12:
                mes = "DIC";
                break;
        }

        return mes;
    }

    //Abre la base de datos pudiendo indicar el nivel de activación
    public static ObjectContainer abrirDB(int i) {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().activationDepth(i);
        return Db4oEmbedded.openFile(config, DB);
    }

    //Abre la base de datos por defecto
    public static ObjectContainer abrirDB() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        return Db4oEmbedded.openFile(config, DB);
    }

    //Abre la base de datos pudiendo indicar si se actualiza o borra en casacada o no
    public static ObjectContainer abrirDB(boolean updateCascade, boolean deleteCascade) {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Cuenta.class).cascadeOnUpdate(updateCascade);
        config.common().objectClass(Cuenta.class).cascadeOnDelete(deleteCascade);
        return Db4oEmbedded.openFile(config, DB);
    }
}
