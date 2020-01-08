package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author curro
 */
public class Movimiento {

    private double importe;
    private String concepto;
    private LocalDateTime fecha;

    public Movimiento(double importe, String concepto) {
        setImporte(importe);
        setConcepto(concepto);
        fecha = LocalDateTime.now();
    }

    public Movimiento(double importe, String concepto, LocalDateTime fecha) {
        setImporte(importe);
        setConcepto(concepto);
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    private void setImporte(double importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    private void setConcepto(String concepto) {

        this.concepto = concepto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int compareTo(Movimiento otro) {
        int i;

        if (this.getFecha().isBefore(otro.getFecha())) {
            i = 1;
        } else {
            if (this.getFecha().isAfter(otro.getFecha())) {
                i = -1;
            } else {
                i = 0;
            }
        }

        return i;
    }

}
