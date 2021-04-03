package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableWindow extends JFrame {

    private final int size;

    public TableWindow(String s, int size) {
        super(s);
        this.size = size;

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        setLayout(new GridLayout(0, 2, 10, 10));

        JPanel table = new JPanel();

        table.setLayout(new GridLayout(size, size, 15, 15));
        //table.setSize(400, 400);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                JLabel temp = new JLabel(Integer.toString(x) + ", " + y);
                temp.setSize(20, 20);
                temp.setOpaque(true);
                temp.setBackground(Color.WHITE);
                int radius = (x - size / 2) * (x - size / 2) + (y - size / 2) * (y - size / 2);
                temp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (temp.getBackground() == Color.GREEN)
                            temp.setBackground(Color.WHITE);
                        else
                            temp.setBackground(Color.GREEN);
                    }
                });
                table.add(temp);
            }
        }

        JPanel rightPane = new JPanel();
        BorderLayout rightPaneLayout = new BorderLayout();
        rightPane.setLayout(rightPaneLayout);

        JPanel controlPane = new JPanel();
        controlPane.setOpaque(true);
        controlPane.setSize(300, 300);
        controlPane.setLayout(new GridLayout(3, 2,5,5));

        rightPane.add(controlPane, BorderLayout.CENTER);//correct because of maximum set size

        JButton setBegin = new JButton("Set begin");
        JButton setEnd = new JButton("Set end");
        JButton switchWall = new JButton("Turn on/off wall paint");
        JButton startAlgorithm = new JButton("Start algorithm");
        JButton clearCanvas = new JButton("Clear");

        controlPane.add(setBegin);
        controlPane.add(setEnd);
        controlPane.add(switchWall);
        controlPane.add(startAlgorithm);
        controlPane.add(clearCanvas);

        Table canvas = new Table(this.size);

        add(table);
        add(rightPane);
    }

}
