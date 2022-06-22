/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Ahmed Nabeel
 */
public class InvoiceLineModel {

// content of single invoice
    private String itemName;
    private double price;
    private int counter;
    private InvoiceHeaderModel invoice;

    public InvoiceLineModel(String ItemName, double Price, int Counter, InvoiceHeaderModel invoice) {

        this.itemName = ItemName;
        this.price = Price;
        this.counter = Counter;
        this.invoice = invoice;
    }

    public double getLineTotal() {

        return price * counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int Counter) {
        this.counter = Counter;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String ItemName) {
        this.itemName = ItemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double Price) {
        this.price = Price;
    }

    public InvoiceHeaderModel getInvoice() {
        return invoice;
    }

    public String getCSV() {
        return invoice.getNumCounter() + "," + itemName + "," + price + "," + counter;

    }

}
