/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InvoiceHeaderModel;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLineModel;
import Model.InvoiceLineTableModel;
import View.InvoiceHeaderFrame;
import View.InvoiceLineFrame;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ahmed Nabeel
 */
public class ActionHandler implements ActionListener, ListSelectionListener {

    private MainFrame framee;
    private InvoiceHeaderFrame invFrame;
    private InvoiceLineFrame lineFrame;

    public ActionHandler(MainFrame framee) {
        this.framee = framee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Load")) {
            Load();

        } else if (e.getActionCommand().equals("Save")) {

            Save();

        } else if (e.getActionCommand().equals("New Invoice")) {

            CreateNewInvoice();

        } else if (e.getActionCommand().equals("Delete Invoice")) {
            DeleteInvoice();

        } else if (e.getActionCommand().equals("New Item")) {
            CreateNewItem();

        } else if (e.getActionCommand().equals("Delete Item")) {
            DeleteItem();

        } else if (e.getActionCommand().equals("OK1")) {
            CreatedInvoice();
        } else if (e.getActionCommand().equals("Cancel1")) {
            cancelCreatedInvoice();
        } else if (e.getActionCommand().equals("OK2")) {
            CreatedLineItem();
        } else if (e.getActionCommand().equals("Cancel2")) {
            cancelCreatedLineItem();
        }
    }

    // to show invoice lines if you select a n invoice
    @Override
    public void valueChanged(ListSelectionEvent e
    ) {
        // fill lables
        int selectedInvoiceIndex = framee.getInvoiceHeaderTable().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            InvoiceHeaderModel selectedInvoice = framee.getInvoiceHeaders().get(selectedInvoiceIndex);
            framee.getInvoiceNumLbl().setText("" + selectedInvoice.getNumCounter());
            framee.getInvoiceDateLbl().setText(selectedInvoice.getDate());
            framee.getCustomerNameLbl().setText(selectedInvoice.getCustomerName());
            framee.getInvoiceTotalLbl().setText("" + selectedInvoice.getTotal());
            // fill line Table
            InvoiceLineTableModel linesModel = new InvoiceLineTableModel(selectedInvoice.getLines());
            framee.getInvoiceLineTable().setModel(linesModel);
            linesModel.fireTableDataChanged();
        }
    }

    private void Load() {

        // open dialog to browse file and load header file
        JFileChooser fileChooser = new JFileChooser();
        try {
            int restultType = fileChooser.showOpenDialog(framee);
            if (restultType == JFileChooser.APPROVE_OPTION) {
                //get the path of selected CSV file
                File invoiceHeaderFile = fileChooser.getSelectedFile();
                Path invoiceHeaderPath = Paths.get(invoiceHeaderFile.getAbsolutePath());
                // Store the Lines of the file CSv in array list
                List<String> invoiceHesderLines = Files.readAllLines(invoiceHeaderPath);
                // store loaded lines to next array list.
                ArrayList<InvoiceHeaderModel> loadedinvoices = new ArrayList<>();
                // iterate on each line at file to use ...
                for (String invoiceHeaderLine : invoiceHesderLines) {

                    try {
                        // Seperate each line ath the CSV file and store it into anew variable.
                        String[] elementsOfLine = invoiceHeaderLine.split(",");
                        int invoiceNumber = Integer.parseInt(elementsOfLine[0]);
                        String invoiceDate = elementsOfLine[1];
                        String customerName = elementsOfLine[2];
                        //store loaded elment to invoice header model class.
                        InvoiceHeaderModel invoice = new InvoiceHeaderModel(invoiceNumber, invoiceDate, customerName);
                        loadedinvoices.add(invoice);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(framee, "Wrong Formar !", "Erroe", JOptionPane.ERROR_MESSAGE);

                    }
                }
                // now we try to load a line file.
                restultType = fileChooser.showOpenDialog(framee);
                if (restultType == JFileChooser.APPROVE_OPTION) {
                    File invoiceLineFile = fileChooser.getSelectedFile();
                    Path invoiceLinePath = Paths.get(invoiceLineFile.getAbsolutePath());
                    List<String> invoiceLinerLines = Files.readAllLines(invoiceLinePath);
                    for (String invoiceLineLine : invoiceLinerLines) {
                        try {
                            String[] elementsOfLineLine = invoiceLineLine.split(",");
                            int invoiceNumber = Integer.parseInt(elementsOfLineLine[0]);
                            String itemName = elementsOfLineLine[1];
                            double itemprice = Double.parseDouble(elementsOfLineLine[2]);
                            int counter = Integer.parseInt(elementsOfLineLine[3]);
                            // to get the invoice header number .
                            InvoiceHeaderModel inv = null;
                            for (InvoiceHeaderModel invoice : loadedinvoices) {
                                if (invoice.getNumCounter() == invoiceNumber) {
                                    inv = invoice;
                                    break;
                                }
                            }

                            InvoiceLineModel invoiceLine = new InvoiceLineModel(itemName, itemprice, counter, inv);
                            inv.getLines().add(invoiceLine);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(framee, "Wrong Formar !", "Erroe", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
                framee.setInvoiceHeaders(loadedinvoices);
                InvoiceHeaderTableModel tableHeaderModel = new InvoiceHeaderTableModel(loadedinvoices);
                framee.setHeaderTableModel(tableHeaderModel);
                framee.getInvoiceHeaderTable().setModel(tableHeaderModel);
                framee.getHeaderTableModel().fireTableDataChanged();
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(framee, "Wrong Formar !", "Erroe", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void Save() {
        ArrayList<InvoiceHeaderModel> invs = framee.getInvoiceHeaders();
        String invoiceHeaders = "";
        String invoiceLines = "";
        for (InvoiceHeaderModel invoice : invs) {
            String headcsv = invoice.getCSV();
            invoiceHeaders += headcsv;
            invoiceHeaders += "\n";

            for (InvoiceLineModel line : invoice.getLines()) {
                String lincsv = line.getCSV();
                invoiceLines += lincsv;
                invoiceLines += "\n";

            }

        }
        try {
            JFileChooser filech = new JFileChooser();
            int resfile = filech.showSaveDialog(framee);
            if (resfile == JFileChooser.APPROVE_OPTION) {
                File headerFlile = filech.getSelectedFile();
                FileWriter fWriterHeader = new FileWriter(headerFlile);
                fWriterHeader.write(invoiceHeaders);
                fWriterHeader.flush();
                fWriterHeader.close();
                resfile = filech.showSaveDialog(framee);
                if (resfile == JFileChooser.APPROVE_OPTION) {
                    File lineFile = filech.getSelectedFile();
                    FileWriter fWriterLine = new FileWriter(lineFile);
                    fWriterLine.write(invoiceLines);
                    fWriterLine.flush();
                    fWriterLine.close();
                }

            }
        } catch (IOException e) {

        }
    }

    private void CreateNewInvoice() {

        invFrame = new InvoiceHeaderFrame(framee);
        invFrame.setVisible(true);

    }

    private void DeleteInvoice() {
        int selectedInvoiceRow = framee.getInvoiceHeaderTable().getSelectedRow();
        if (selectedInvoiceRow != -1) {
            framee.getInvoiceHeaders().remove(selectedInvoiceRow);
            framee.getHeaderTableModel().fireTableDataChanged();
        }
    }

    private void CreateNewItem() {
        lineFrame = new InvoiceLineFrame(framee);
        lineFrame.setVisible(true);
    }

    private void DeleteItem() {
        int selectedInvoice = framee.getInvoiceHeaderTable().getSelectedRow();
        int selectedItemRow = framee.getInvoiceLineTable().getSelectedRow();
        if (selectedInvoice != -1 && selectedItemRow != -1) {
            InvoiceHeaderModel inv = framee.getInvoiceHeaders().get(selectedInvoice);
            inv.getLines().remove(selectedItemRow);
            InvoiceLineTableModel linesOFTableModel = new InvoiceLineTableModel(inv.getLines());
            framee.getInvoiceLineTable().setModel(linesOFTableModel);
            linesOFTableModel.fireTableDataChanged();
            framee.getHeaderTableModel().fireTableDataChanged();
        }

    }

    private void CreatedInvoice() {

        DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
        String date = invFrame.getDateDialogField().getText();
        String customerName = invFrame.getCustomerNameDialogField().getText();
        int number = framee.getCreatedInvoiceNumber();

        try {
            String[] datesplit = date.split("-");
            if (datesplit.length > 3) {

                JOptionPane.showMessageDialog(framee, "insert Valid Date! ", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                int day = Integer.parseInt(datesplit[0]);
                int month = Integer.parseInt(datesplit[1]);
                int year = Integer.parseInt(datesplit[2]);

                if (day > 31 || day < 1 || month > 12 || month < 1) {
                    JOptionPane.showMessageDialog(framee, "insert Valid Date! ", "Error", JOptionPane.ERROR_MESSAGE);

                } else {

                    InvoiceHeaderModel inv = new InvoiceHeaderModel(number, date, customerName);
                    framee.getInvoiceHeaders().add(inv);
                    framee.getHeaderTableModel().fireTableDataChanged();
                    invFrame.setVisible(false);
                    invFrame.dispose();
                    invFrame = null;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(framee, "insert Valid Date! ", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cancelCreatedInvoice() {
        invFrame.setVisible(false);
        invFrame.dispose();
        invFrame = null;
    }

    private void CreatedLineItem() {

        String itemName = lineFrame.getItemNameDialogField().getText();
        String countitemstr = lineFrame.getItemCountDialogField().getText();
        String priceitemstr = lineFrame.getItemPriceDialogField().getText();
        int countItem = Integer.parseInt(countitemstr);
        double priceItem = Double.parseDouble(priceitemstr);
        int selectedInv = framee.getInvoiceHeaderTable().getSelectedRow();

        if (selectedInv != -1) {
            InvoiceHeaderModel inv = framee.getInvoiceHeaders().get(selectedInv);
            InvoiceLineModel line = new InvoiceLineModel(itemName, priceItem, countItem, inv);
            inv.getLines().add(line);
            InvoiceLineTableModel lineModell = (InvoiceLineTableModel) framee.getInvoiceLineTable().getModel();
            lineModell.fireTableDataChanged();
            framee.getHeaderTableModel().fireTableDataChanged();

        }
        lineFrame.setVisible(false);
        lineFrame.dispose();
        lineFrame = null;
    }

    private void cancelCreatedLineItem() {
        lineFrame.setVisible(false);
        lineFrame.dispose();
        lineFrame = null;

    }

}
