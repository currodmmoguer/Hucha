/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author curro
 */
public class ModeloTabla extends DefaultTableModel{

    private Class[] tipoColumna = {String.class, String.class, double.class};
    
    private String[] campos = {"Fecha", "Concepto", "Importe"};
    
    

    public String[] getCampos(){
        return campos;
    }

    @Override
    public String getColumnName(int column) {
        return campos[column];
    }
    
    
    @Override
    public Class getColumnClass(int i) {
        return tipoColumna[i];
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


    
    
    
}
