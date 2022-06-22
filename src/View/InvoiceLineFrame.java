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
public class InvoiceLineFrame extends JDialog{

private JTextField itemNameDialogField;
    private JTextField itemCountDialogField;
    private JTextField itemPriceDialogField;
    private JLabel itemNameDialogLbl;
    private JLabel itemCountDialogLbl;
    private JLabel itemPriceDialogLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public InvoiceLineFrame(MainFrame frame) {
        itemNameDialogField = new JTextField(20);
        itemNameDialogLbl = new JLabel("Item Name");
        
        itemCountDialogField = new JTextField(20);
        itemCountDialogLbl = new JLabel("Item Count");
        
        itemPriceDialogField = new JTextField(20);
        itemPriceDialogLbl = new JLabel("Item Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("OK2");
        cancelBtn.setActionCommand("Cancel2");
        
        okBtn.addActionListener(frame.getHandler());
        cancelBtn.addActionListener(frame.getHandler());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameDialogLbl);
        add(itemNameDialogField);
        add(itemCountDialogLbl);
        add(itemCountDialogField);
        add(itemPriceDialogLbl);
        add(itemPriceDialogField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameDialogField() {
        return itemNameDialogField;
    }

    public JTextField getItemCountDialogField() {
        return itemCountDialogField;
    }

    public JTextField getItemPriceDialogField() {
        return itemPriceDialogField;
    }

    
    
}
