/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorPrincipal;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author DAM-2
 */
public class VistaPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form Principal1
     */
    public VistaPrincipal() {
        initComponents();
        setBarra();
        setColorRows();

    }

    public void addModelo(Object[][] valores) {
        modelo = new ModeloTabla();
        modelo.setDataVector(valores, modelo.getCampos());
        jTable1.setModel(modelo);
    }

    public ModeloTabla getModel() {
        return modelo;
    }

    public Object getCampoTabla(int fila, int columna) {
        return modelo.getValueAt(fila, columna);
    }

    public int getCantidadColumas() {
        return jTable1.getColumnCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        beanHora1 = new beanhora.BeanHora();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        labelMovimientos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelPaginacion = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        labelSaldo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        labelDatos = new javax.swing.JLabel();
        comboBoxPeriodo = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelIngresos = new javax.swing.JLabel();
        labelGastos = new javax.swing.JLabel();
        labelCantidadIngresos = new javax.swing.JLabel();
        labelCantidadGastos = new javax.swing.JLabel();
        labelTiempoIngreso = new javax.swing.JLabel();
        labelTiempoGasto = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        barra = new javax.swing.JProgressBar();
        labelDiferencia = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        botonVerMas = new javax.swing.JButton();
        botonNuevoMovimiento = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setForeground(new java.awt.Color(254, 254, 254));
        setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        labelUsuario.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setText("Nombre usuario");
        jPanel3.add(labelUsuario);

        beanHora1.setFuente(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jPanel3.add(beanHora1);

        add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        labelMovimientos.setText("Movimientos");
        jPanel5.add(labelMovimientos);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1);
        jPanel5.add(panelPaginacion);

        jPanel4.add(jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel10.setMinimumSize(new java.awt.Dimension(55, 17));
        jPanel10.setPreferredSize(new java.awt.Dimension(55, 50));

        labelSaldo.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        labelSaldo.setText("Saldo: ");
        jPanel10.add(labelSaldo);

        jPanel6.add(jPanel10, java.awt.BorderLayout.NORTH);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        labelDatos.setText("Periodo: ");
        jPanel9.add(labelDatos);

        comboBoxPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Anual", "Trimestral", "Mensual" }));
        jPanel9.add(comboBoxPeriodo);

        jPanel1.add(jPanel9);

        jPanel11.setBorder(null);
        jPanel11.setLayout(new java.awt.GridLayout(2, 1, 0, 20));

        jPanel2.setBorder(null);
        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 10, 0));

        labelIngresos.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        labelIngresos.setForeground(java.awt.Color.black);
        labelIngresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIngresos.setText("INGRESOS");
        jPanel2.add(labelIngresos);

        labelGastos.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        labelGastos.setForeground(java.awt.Color.black);
        labelGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGastos.setText("GASTOS");
        jPanel2.add(labelGastos);

        labelCantidadIngresos.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        labelCantidadIngresos.setForeground(new java.awt.Color(0, 185, 0));
        labelCantidadIngresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCantidadIngresos.setText("1");
        jPanel2.add(labelCantidadIngresos);

        labelCantidadGastos.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        labelCantidadGastos.setForeground(java.awt.Color.red);
        labelCantidadGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCantidadGastos.setText("1");
        jPanel2.add(labelCantidadGastos);

        labelTiempoIngreso.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        labelTiempoIngreso.setText("Hace x meses");
        jPanel2.add(labelTiempoIngreso);

        labelTiempoGasto.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        labelTiempoGasto.setText("Hace x meses");
        jPanel2.add(labelTiempoGasto);

        jPanel11.add(jPanel2);

        jPanel7.setBorder(null);
        jPanel7.setMaximumSize(new java.awt.Dimension(300, 20));
        jPanel7.setPreferredSize(new java.awt.Dimension(140, 34));
        jPanel7.setLayout(new java.awt.GridLayout(2, 1));

        barra.setBackground(java.awt.Color.red);
        barra.setForeground(new java.awt.Color(1, 213, 30));
        barra.setMaximumSize(new java.awt.Dimension(200, 20));
        barra.setMinimumSize(new java.awt.Dimension(200, 20));
        barra.setPreferredSize(new java.awt.Dimension(200, 20));
        jPanel7.add(barra);

        labelDiferencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDiferencia.setText("0");
        labelDiferencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(labelDiferencia);

        jPanel11.add(jPanel7);

        jPanel1.add(jPanel11);

        jPanel6.add(jPanel1, java.awt.BorderLayout.CENTER);

        botonVerMas.setText("Ver más");
        jPanel8.add(botonVerMas);

        botonNuevoMovimiento.setText("Añadir movimiento");
        jPanel8.add(botonNuevoMovimiento);

        jPanel6.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel4.add(jPanel6);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public void setUsuario(String usuario) {
        labelUsuario.setText(usuario);
    }

    /**
     * hace una barra donde indica el porcentaje de gastos e ingresos de la
     * cuenta
     */
    private void setBarra() {
        double gastos = Double.parseDouble(labelCantidadGastos.getText());
        double ingresos = Double.parseDouble(labelCantidadIngresos.getText());
        if (gastos == ingresos){
            barra.setValue(50);
        } else {
            barra.setValue((int) (ingresos * 100 / (ingresos + gastos)));
        }
        
        
    }

    public String getPeriodo() {
        return comboBoxPeriodo.getSelectedItem().toString();
    }

    public void setGastos(double cantidad) {
        labelCantidadGastos.setText(String.valueOf(cantidad));
        setBarra();
    }

    public void setIngresos(double cantidad) {
        labelCantidadIngresos.setText(String.valueOf(cantidad));
        setBarra();
    }

    public void setSaldo(double cantidad) {
        labelSaldo.setText("Saldo: " + String.valueOf(cantidad));
    }

    /**
     * Obtiene la diferencia entre los ingresos y gastos.
     * Si los gastos son mayores, se muestra en negativo y rojo y al contrario en positivo y verde
     * @param cantidad 
     */
    public void setDiferencia(double cantidad) {
        if (cantidad < 0) {
            labelDiferencia.setForeground(Color.red);
        } else {
            labelDiferencia.setForeground(new java.awt.Color(0, 185, 0));
        }

        labelDiferencia.setText(String.valueOf(cantidad));
    }

    public void setLabelPeriodo(String str) {
        labelTiempoGasto.setText(str);
        labelTiempoIngreso.setText(str);
    }

    public void addManejador(ControladorPrincipal c) {
        botonNuevoMovimiento.addActionListener(c);
        comboBoxPeriodo.addActionListener(c);
        botonVerMas.addActionListener(c);
        botonVerMas.setActionCommand("VERMAS");
        comboBoxPeriodo.setActionCommand("PERIODO");
        botonNuevoMovimiento.setActionCommand("NUEVOMOVIMIENTO");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barra;
    private beanhora.BeanHora beanHora1;
    private javax.swing.JButton botonNuevoMovimiento;
    private javax.swing.JButton botonVerMas;
    private javax.swing.JComboBox comboBoxPeriodo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelCantidadGastos;
    private javax.swing.JLabel labelCantidadIngresos;
    private javax.swing.JLabel labelDatos;
    private javax.swing.JLabel labelDiferencia;
    private javax.swing.JLabel labelGastos;
    private javax.swing.JLabel labelIngresos;
    private javax.swing.JLabel labelMovimientos;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelTiempoGasto;
    private javax.swing.JLabel labelTiempoIngreso;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelPaginacion;
    // End of variables declaration//GEN-END:variables
    ModeloTabla modelo;

    //Hace que la tabla tenga cada fila un color, las pares blanco y las impares gris.
    private void setColorRows() {
        jTable1.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                Component c = renderer.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);

                // Apply zebra style on table rows
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(Color.decode("#F0F0F0"));
                }

                return c;
            }

        }
        );
    }

}
