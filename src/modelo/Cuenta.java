package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author DAM-2
 */
public class Cuenta {

    private double saldo;
    private String user;
    private String pass;
    private ArrayList<Movimiento> movimientos;

    public Cuenta() {
    }

    public Cuenta(double saldo, String user, String pass) throws CuentaException {
        setSaldo(saldo);
        setUser(user);
        setPass(pass);
        movimientos = new ArrayList<Movimiento>();
    }

    public Cuenta(String user, String pass) throws CuentaException {
        setUser(user);
        setPass(pass);
        setSaldo(0);
        movimientos = new ArrayList<Movimiento>();
    }

    public Cuenta(String user) {
        setUser(user);
    }

 

    public double getSaldo() {
        return saldo;
    }

    private void setSaldo(double saldo) {

        if (saldo > 0) {
            this.saldo = saldo;
        } else {
            this.saldo = 0;
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) throws CuentaException {
        if (pass == null || pass.length() == 0) {
            
            throw new CuentaException("Tienes que introducir la contraseña");
        }
        this.pass = pass;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void nuevoMovimiento(double importe, String concepto, boolean ingreso) throws CuentaException {
        if (ingreso) {
            ingresar(importe);
        } else {
            extraer(importe);
            importe = -importe;
        }

        movimientos.add(new Movimiento(importe, concepto));

    }

    public void nuevoMovimiento(double importe, String concepto, boolean ingreso, LocalDateTime fecha) throws CuentaException {

        if (ingreso) {
            ingresar(importe);
        } else {
            extraer(importe);
        }

        if (!ingreso) {
            importe = -importe;
        }

        movimientos.add(new Movimiento(importe, concepto, fecha));
    }

    public void ingresar(double dinero) {
        saldo += dinero;
    }

    public double extraer(double dinero) throws CuentaException {
        double dineroExtraido = dinero;

        if (dinero > this.saldo) {
            throw new CuentaException("No tienes saldo suficiente.");
        } else {
            saldo -= dinero;
        }

        return dineroExtraido;
    }

    
    
    

}
