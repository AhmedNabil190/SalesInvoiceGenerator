/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Ahmed Nabeel
 */
public class InvoiceHeaderModel {
// contents of headers of invoices.

    private int numCounter;
    private String date;
    private String customerName;
    private double totalPrice;

    private ArrayList<InvoiceLineModel> lines; // to refer to mulitible items at lines table.

    public InvoiceHeaderModel(int NumCounter, String date, String CustomerName) {
        this.numCounter = NumCounter;
        this.date = date;
        this.customerName = CustomerName;
    }

    public double getTotal() {
        double total = 0.0;
        for (InvoiceLineModel line : getLines()) {

            total += line.getLineTotal();
        }
        return total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String CustomerName) {
        this.customerName = CustomerName;
    }

    public int getNumCounter() {
        return numCounter;
    }

    public void setNumCounter(int NumCounter) {
        this.numCounter = NumCounter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<InvoiceLineModel> getLines() {

        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLineModel> lines) {
        this.lines = lines;
    }

    public String getCSV() {
        return numCounter + "," + date + "," + customerName ;

    }

}
