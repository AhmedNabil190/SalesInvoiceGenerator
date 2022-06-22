/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ahmed Nabeel
 */
public class InvoiceLineTableModel extends AbstractTableModel {

    private ArrayList<InvoiceLineModel> invoiceLines;
    private String[] columns = {"Invoice Number", "Item", "Price", "Count", "Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLineModel> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @Override
    public int getRowCount() {
        return invoiceLines.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowindex, int columnindex) {
        InvoiceLineModel line = invoiceLines.get(rowindex);
        if (columnindex == 0) {
            return line.getInvoice().getNumCounter();
        } else if (columnindex == 1) {
            return line.getItemName();
        } else if (columnindex == 2) {
            return line.getPrice();
        } else if (columnindex == 3) {
            return line.getCounter();
        } else if (columnindex == 4) {
            return line.getLineTotal();
        }
        return null;

    }

    public ArrayList<InvoiceLineModel> getInvoiceLines() {
        return invoiceLines;
    }


}
