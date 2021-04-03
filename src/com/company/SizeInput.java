package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SizeInput extends JFrame {
    private int size;

    public SizeInput(String s){
        super(s);

        setLayout(new GridLayout(3,0,10,10));

        JLabel info;
        JTextField sizeField;
        JButton ok;

        info = new JLabel("Input size:");
        sizeField = new JTextField("size");
        ok = new JButton("Ok");

        ok.setEnabled(false);

        sizeField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sizeField.setText("");
                ok.setEnabled(true);
            }
        });

        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!sizeField.getText().equals("")){
                        size = Integer.parseInt(sizeField.getText());
                        setVisible(false);
                        new TableWindow(s, size);
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Input something, please", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(NumberFormatException err){
                    JOptionPane.showMessageDialog(new JFrame(), "Input number not string!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.add(info);
        this.add(sizeField);
        this.add(ok);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }
}
