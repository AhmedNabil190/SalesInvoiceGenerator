/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ahmed Nabeel
 */
public class InvoiceHeaderFrame extends JDialog {

    private JTextField customerNameDialogField;
    private JTextField dateDialogField;
    private JLabel customerNameDialogLbl;
    private JLabel dateDialogLbl;
    private JButton okNewInvoiceHeader;
    private JButton cancelNewInvoiceHeader;

    public InvoiceHeaderFrame(MainFrame frame) {
        customerNameDialogLbl = new JLabel("Customer Name:");
        customerNameDialogField = new JTextField(20);
        dateDialogLbl = new JLabel("Date:");
        dateDialogField = new JTextField(20);
        okNewInvoiceHeader = new JButton("OK");
        cancelNewInvoiceHeader = new JButton("Cancel");

        okNewInvoiceHeader.setActionCommand("OK1");
        cancelNewInvoiceHeader.setActionCommand("Cancel1");

        okNewInvoiceHeader.addActionListener(frame.getHandler());
        cancelNewInvoiceHeader.addActionListener(frame.getHandler());
        setLayout(new GridLayout(3, 2));

        add(dateDialogLbl);
        add(dateDialogField);
        add(customerNameDialogLbl);
        add(customerNameDialogField);
        add(okNewInvoiceHeader);
        add(cancelNewInvoiceHeader);

        pack();

    }

    public JTextField getCustomerNameDialogField() {
        return customerNameDialogField;
    }

    public JTextField getDateDialogField() {
        return dateDialogField;
    }

   

}
