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
public class InvoiceHeaderTableModel extends AbstractTableModel {

    private ArrayList<InvoiceHeaderModel> invoiceHeaders;
    private String[] columns = {"number", "DAte", "Customer NAme", "Total"};

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeaderModel> invoiceHeaders) {
        this.invoiceHeaders = invoiceHeaders;
    }

    @Override
    public int getRowCount() {
        return invoiceHeaders.size();
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
        InvoiceHeaderModel invoice = invoiceHeaders.get(rowindex);

        if (columnindex == 0) {
            return invoice.getNumCounter();
        } else if (columnindex == 1) {
            return invoice.getDate();
        } else if (columnindex == 2) {
            return invoice.getCustomerName();
        } else if (columnindex == 3) {
            return invoice.getTotal();
        }
        return null;

    }
}
