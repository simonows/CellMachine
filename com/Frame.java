package com;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class Frame extends JFrame {
    private Space space;
    private Panel panel;
    JScrollPane sp;

    public Frame(String name) {
        super(name);
        space = new Space();
        panel = new Panel(space);
        sp = new JScrollPane(space);
        sp.setPreferredSize(new Dimension(200, 200));
        new Thread(space).start();

        setLayout(new BorderLayout());
        add(panel, "West");
        add(sp, "Center");

        setSize(Style.width,Style.height);
        setExtendedState(MAXIMIZED_BOTH);
        setLocation(0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Style.width = getSize().width;
                Style.height = getSize().height;
                Style.panel_height = Style.height;
                panel.setPreferredSize(new Dimension(Style.panel_width, Style.panel_width));
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        sp.repaint();
        space.repaint();
        panel.repaint();
    }

}

